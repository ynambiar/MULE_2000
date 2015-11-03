package Model;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import Controller.MapController;
import Controller.MasterController;
import Model.Map.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Game implements Serializable {

    ArrayList<Player> players;
    Player currentPlayer;
    Difficulty difficulty;
    MapType mapType;
    Map map;
    Store store;
    int roundNumber;
    int timeLeft;
    int gamble;
    String phase;
    Mule muleType;
    Event[] events = new Event[] { Event.ONE, Event.TWO, Event.THREE,
            Event.FOUR, Event.FIVE, Event.SIX, Event.SEVEN, Event.EIGHT,
            Event.NINE, Event.TEN };
    Event currentEvent;
    int landCost;
    private File f1 = new File("Player1.ser");
    private File f2 = new File("Player2.ser");
    private File f3 = new File("Player3.ser");
    private File f4 = new File("Player4.ser");
    private File m1 = new File("MapSave.ser");

    /**
     * Constructor for the Game object.
     * Creates Map, Players list, and Store.
     * Initializes media and plays music.
     */
    public Game() {
        map = new Map();
        players = new ArrayList<Player>();
        store = new Store();
        // Code for the music
        final URL resource = getClass()
                .getResource("/View/Resources/music.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }

    /**
     * Starts the game. Sets difficulty, current player, round number,
     * and loads Start Turn scene.
     */
    public void startGame() {
        if (difficulty == Difficulty.MEDIUM) {
            for (Player p : players) {
                p.addMoney((int) (p.getMoney() * (-.25)));
            }
        } else if (difficulty == Difficulty.HARD) {
            for (Player p : players) {
                System.out.println(p.getMoney());
                p.addMoney((int) (p.getMoney() * (-.5)));
            }
        }
        currentPlayer = players.get(0);
        roundNumber = -1; // round -1 and 0 are land selection
        setPhase("Land Selection");
        MasterController.getInstance().loadStartTurnScene();
        refreshLabels();
    }

    /**
     * Starts turn for a player.
     * Randomly generates an event that will happen for this turn.
     */
    public void startTurn() {
        Random random = new Random();
        int r = random.nextInt(100);
        if (r < 27 && currentPlayer != players.get(0) && roundNumber > 0) {
            r = random.nextInt(12);
            if (r > 10) { // event affects all players
                for (Player p : players) {
                    currentEvent = events[r];
                    int[] modifier = currentEvent.getEffects();
                    p.addMoney(modifier[0]);
                    p.addFood(modifier[1]);
                    p.addEnergy(modifier[2]);
                    p.addSmithore(modifier[3]);
                }
            } else { // event only affects the current player
                currentEvent = events[r];
                int[] modifier = currentEvent.getEffects();
                currentPlayer.addMoney(modifier[0]);
                currentPlayer.addFood(modifier[1]);
                currentPlayer.addEnergy(modifier[2]);
                currentPlayer.addSmithore(modifier[3]);
            }
            MasterController.getInstance().loadEventScene();
        } else {
            finishEvent();
        }
    }

    /**
     * Finishes event by resetting the time left for the player.
     * Loads Map scene.
     */
    public void finishEvent() {
        checkProduction();
        if (roundNumber > 0) {
            timeLeft = getTimeAfterFoodCheck();
        }
        if (roundNumber == 1 && currentPlayer == players.get(0)) {
            MasterController.getInstance().getMapController().startTimer();
        }
        MasterController.getInstance().loadMapScene();
        refreshLabels();
    }

    /**
     * Ends the turn and sets current player to the next player.
     * If all players have had their turn, ends round.
     */
    public void endTurn() {
        setPhase("Regular Turn");
        if (currentPlayer == players.get(players.size() - 1)) {
            endRound();
        } else {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
            MasterController.getInstance().loadStartTurnScene();
        }
    }

    /**
     * Ends round and sets up for the next round.
     * Sorts the players in terms of score, sets current player,
     * and loads Start Turn scene.
     */

    public void endRound() {
        roundNumber++;
        if (roundNumber >= 1) {
            players.sort(new PlayerComparator<>());
        }
        currentPlayer = players.get(0);
        MasterController.getInstance().loadStartTurnScene();

    }

    /**
     * Implementation of the Comparator class.
     * Compares Players based on their scores.
     * @param <Object>
     */
    public static class PlayerComparator<Object> implements Comparator<Player> {
        @Override
        public int compare(Player a, Player b) {
            return a.getScore() - b.getScore();
        }
    }

    /**
     * Refreshes Labels to current Player's stats and properties.
     */
    public void refreshLabels() {
        MapController m = MasterController.getInstance().getMapController();
        m.setCurrentPhaseLabel(phase);
        m.setCurrentPlayerLabel(currentPlayer.getName());
        m.setFoodLabel("Food: " + currentPlayer.getFood());
        m.setEnergyLabel("Energy: " + currentPlayer.getEnergy());
        m.setSmithoreLabel("Smithore: " + currentPlayer.getSmithore());
        m.setMoneyLabel("Money: " + currentPlayer.getMoney());
    }

    /**
     * Checks if the operation at tile at location [row][col] is valid
     * and sets properties for that tile.
     * If the current player's operation on tile is valid, returns true.
     * @param row
     * @param col
     * @return
     */
    public boolean tileClicked(int row, int col) {
        if (roundNumber < 1) {
            if (map.tileUnowned(row, col)) {
                currentPlayer.setTileOwned(row, col, true);
                map.setTileOwned(row, col, true);
                return true;
            } else {
                return false;
            }
        } else {
            if (phase.equals("Purchasing Land")) {
                if (map.tileUnowned(row, col)) {
                    if (currentPlayer.getMoney() >= landCost) {
                        currentPlayer.setTileOwned(row, col, true);
                        map.setTileOwned(row, col, true);
                        currentPlayer.addMoney(landCost * -1);
                        refreshLabels();
                        return true;
                    }
                }
            } else if (phase.equals("Selling Land")) {
                if (currentPlayer.getTileOwned(row, col)) {
                    currentPlayer.addMoney(landCost);
                    currentPlayer.setTileOwned(row, col, false);
                    map.setTileOwned(row, col, false);
                    refreshLabels();
                    return true;
                }
            } else if (phase.equals("Emplacing Mule")) {
                if (currentPlayer.getTileOwned(row, col)) {
                    currentPlayer.setMuleEmplaced(row, col, muleType);
                    MasterController.getInstance().loadStoreScene();
                    return true;
                } else {
                    MasterController.getInstance().loadStoreScene();
                    return false;
                }
            } else if (phase.equals("Selling Mules")) {
                if (currentPlayer.getTileOwned(row, col)
                        && currentPlayer.getMuleEmplaced(row, col) != null) {
                    int cost = 100;
                    currentPlayer.addMoney(cost);
                    currentPlayer.setMuleEmplaced(row, col, null);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * Does Store transaction for 1 of given item.
     * Buys or sells based on second boolean parameter, buying.
     * @param item
     * @param buying
     * @return
     */
    public boolean doStoreTransaction(String item, boolean buying) {
        return doStoreTransaction(item, buying, 1);
    }

    /**
     * Does Store transaction for given amnt of given item.
     * Buys or sells based on second boolean parameter, buying.
     * @param item
     * @param buying
     * @param amnt
     * @return
     */
    public boolean doStoreTransaction(String item, boolean buying, int amnt) {
        if (buying) {
            return store.purchaseTransaction(item, amnt);
        } else {
            return store.sellTransaction(item, amnt);
        }
    }

    /**
     * Getter that returns the current phase.
     * @return
     */
    public String getPhase() {
        return phase;
    }

    /**
     * Setter that sets the current phase to the given String.
     * @param s
     */
    public void setPhase(String s) {
        phase = s;
    }

    /**
     * Returns number of seconds left based on the
     * current player's food stock.
     * @return
     */
    public int getTimeAfterFoodCheck() {
        int[] foodRq = { 0, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5 };
        int food = currentPlayer.getFood();
        if (roundNumber == 1) {
            return 50;
        }
        if (food >= foodRq[roundNumber]) {
            currentPlayer.addFood(foodRq[roundNumber - 1] * -1);
            return 50;
        } else if (food == 0) {
            return 5;
        } else {
            currentPlayer.addFood(food * -1);
            return 30;
        }
    }

    /**
     * Based on a player's stock of mules,
     * updates player's energy and continues production.
     */
    public void checkProduction() {
        int numMules = currentPlayer.getNumMules();
        System.out.println("numMules: " + numMules);
        if (currentPlayer.getEnergy() >= numMules) {
            currentPlayer.addEnergy(numMules * -1);
            currentPlayer.doProduction();
        }
    }

    /**
     * Adds player to list of Player.
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Returns list of Players.
     * @return
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Returns Difficulty enum of the game.
     * @return
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets Difficult enum of the game.
     * @param difficulty
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Return the MapType enum of the game.
     * @return
     */
    public MapType getMapType() {
        return mapType;
    }

    /**
     * Returns the Store of the game.
     * @return
     */
    public Store getStore() {
        return store;
    }

    /**
     * Sets MapType enum of the game.
     * @param mapType
     */
    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }


    /**
     * Returns Map.
     * @return
     */
    public Map getMap() {
        return map;
    }

    /**
     * Returns round number.
     * @return
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * Returns current player.
     * @return
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the mule type for given Mule t.
     * @param t
     */
    public void setMuleType(Mule t) {
        muleType = t;
    }

    /**
     * Gets the mule type for given Mule t.
     * @return
     */
    public Mule getMuleType() {
        return muleType;
    }

    /**
     * Gets the current event going on.
     * @return
     */
    public Event getCurrentEvent() {
        return currentEvent;
    }

    /**
     * Sets the cost of land to c.
     * @param c
     */
    public void setLandCost(int c) {
        landCost = c;
    }

    /* Timer methods */

    /**
     * Decrements the time left by one.
     * If time is 0, ends turn.
     */
    public void decrementTimeLeft() {
        if (timeLeft == 0) {
            endTurn();
        }
        timeLeft--;
    }

    /**
     * Gets time left for the current player's turn.
     * @return
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Sets gamble amount to n.
     * @param n
     */
    public void setGamble(int n) {
        gamble = n;
    }

    /**
     * Gets gamble amount.
     * @return
     */
    public int getGamble() {
        return gamble;
    }

    /**
     * Saves data.
     *
     * @param p
     * @throws IOException
     */
    public void saveData(ArrayList<Player> p, Map map) throws IOException {

        try {
            FileOutputStream fileOutMap = new FileOutputStream(m1);
            ObjectOutputStream outMap = new ObjectOutputStream(fileOutMap);
            outMap.writeObject(map);
            outMap.close();
        } catch (IOException i) {
            System.out.println("saving map doesn't work");
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(f1);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(p.get(0));
            out.writeObject("\n");
            out.close();
        } catch (IOException i) {
            System.out.println("save doesn't work");
        }

        if (p.size() > 1) {
            try {
                FileOutputStream fileOut = new FileOutputStream(f2);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(p.get(1));
                out.writeObject("\n");
                out.close();
            } catch (IOException i) {
                System.out.println("save doesn't work");
            }
        } else {
            FileOutputStream fileOut = new FileOutputStream(f2);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(null);
            out.close();
        }

        if (p.size() > 2) {
            try {
                FileOutputStream fileOut = new FileOutputStream(f3);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(p.get(2));
                out.writeObject("\n");
                out.close();
            } catch (IOException i) {
                System.out.println("save doesn't work");
            }
        } else {
            FileOutputStream fileOut = new FileOutputStream(f3);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(null);
            out.close();
        }

        if (p.size() > 3) {
            try {
                FileOutputStream fileOut = new FileOutputStream(f4);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(p.get(3));
                out.writeObject("\n");
                out.close();
            } catch (IOException i) {
                System.out.println("save doesn't work");
            }
        } else {
            FileOutputStream fileOut = new FileOutputStream(f4);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(null);
            out.close();
        }
    }

    /**
     * Loads data.
     *
     * @throws IOException
     */
    public void loadData() throws IOException {

        Player g = null;
        Player h = null;
        Player i = null;
        Player j = null;

        try {
            FileInputStream fileIn = new FileInputStream(f1);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            g = (Player) in.readObject();
            in.read();
            in.close();
            fileIn.close();
        } catch (IOException x) {
            System.out.print("load doesn't work");
        } catch (ClassNotFoundException c) {
            System.out.print("load doesn't work");
        }

        System.out.println(g.getName());
        System.out.println(g.getNumTilesOwned());
        System.out.println(g.getColor());

        try {
            FileInputStream fileIn2 = new FileInputStream(f2);
            ObjectInputStream in2 = new ObjectInputStream(fileIn2);
            h = (Player) in2.readObject();
            in2.read();
            in2.close();
            fileIn2.close();
        } catch (IOException x) {
            System.out.print("load doesn't work");
        } catch (ClassNotFoundException c) {
            System.out.print("load doesn't work");
        }

        System.out.println(h.getName());
        System.out.println(h.getNumTilesOwned());
        System.out.println(h.getColor());

        try {
            FileInputStream fileIn3 = new FileInputStream(f3);
            ObjectInputStream in3 = new ObjectInputStream(fileIn3);
            i = (Player) in3.readObject();
            if (i == null) {
                return;
            }
            in3.read();
            in3.close();
            fileIn3.close();
        } catch (IOException x) {
            System.out.print("load doesn't work");
        } catch (ClassNotFoundException c) {
            System.out.print("load doesn't work");
        }

        System.out.println(i.getName());
        System.out.println(i.getNumTilesOwned());
        System.out.println(i.getColor());

        try {
            FileInputStream fileIn4 = new FileInputStream(f4);
            ObjectInputStream in4 = new ObjectInputStream(fileIn4);
            j = (Player) in4.readObject();
            if (j == null) {
                return;
            } else {
                in4.read();
                in4.close();
                fileIn4.close();
                System.out.println(j.getName());
                System.out.println(j.getNumTilesOwned());
                System.out.println(j.getColor());
            }
        } catch (IOException x) {
            System.out.print("load doesn't work");
        } catch (ClassNotFoundException c) {
            System.out.print("load doesn't work");
        }

    }
}