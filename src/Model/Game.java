package Model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;

import Controller.MapController;
import Controller.MasterController;
import Model.Map.*;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.Random;


/**
 * Created by tuckerlocicero on 10/2/15.
 *
 * This class should hold game logic.
 */
public class Game {

    String currentPhase; //lets everyone know what the current phase of the game is (i.e. land selection, mule emplacing, etc)
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
    int landCost;


    public Game() {
        map = new Map();
        players = new ArrayList<Player>();
        store = new Store();
        //Code for the music
        final URL resource = getClass().getResource("/View/Resources/music.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }

    public void startGame() {
        if (difficulty == Difficulty.MEDIUM) {
            for (Player p : players) {
                p.addMoney( (int) (p.getMoney() * (-.25)));
            }
        } else if (difficulty == Difficulty.HARD) {
            for (Player p : players) {
                System.out.println(p.getMoney());
                p.addMoney((int) (p.getMoney() * (-.5)));
            }
        }
        currentPlayer = players.get(0);
        roundNumber = -1; //round -1 and 0 are land selection
        setPhase("Land Selection");
        MasterController.getInstance().loadStartTurnScene();
        refreshLabels();
    }

    public void startTurn() {
        refreshLabels();
        if (roundNumber > 0) {
            timeLeft = getTimeAfterFoodCheck();
        }
        if (roundNumber == 1 && currentPlayer == players.get(0)) {
            MasterController.getInstance().getMapController().startTimer();
        }
    }

    public void endTurn() {
        if (currentPlayer == players.get(players.size() - 1)) {
            endRound();
        } else {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
            MasterController.getInstance().loadStartTurnScene();
        }
    }

    public void endRound() {
        roundNumber++;
        if (roundNumber >= 1) {
            players.sort(new PlayerComparator<>());
        }
        currentPlayer = players.get(0);
        MasterController.getInstance().loadStartTurnScene();

    }

    public void refreshLabels() {
        MapController m = MasterController.getInstance().getMapController();
        m.setCurrentPhaseLabel(phase);
        m.setCurrentPlayerLabel(currentPlayer.getName());
        m.setFoodLabel("Food: " + currentPlayer.getFood());
        m.setEnergyLabel("Energy: " + currentPlayer.getEnergy());
        m.setSmithoreLabel("Smithore: " + currentPlayer.getSmithore());
        m.setMoneyLabel("Money: " + currentPlayer.getMoney());
    }

    public static class PlayerComparator<Object> implements Comparator<Player> {
        @Override
        public int compare(Player a, Player b) {
            return  a.getScore() - b.getScore();
        }
    }

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
            } else if (phase.equals("Selling Land")){
                if (currentPlayer.getTileOwned(row, col)) {
                    currentPlayer.addMoney(landCost);
                    currentPlayer.setTileOwned(row, col, false);
                    map.setTileOwned(row, col, false);
                    refreshLabels();
                    return true;
                }
            } else if (phase.equals("Emplacing Mule")) {
                int cost;
                if (muleType == Mule.FOOD) {
                    cost = 125;
                } else if (muleType == Mule.ENERGY) {
                    cost = 150;
                } else {
                    cost = 175;
                }
                if (currentPlayer.getTileOwned(row, col)) {
                    if (currentPlayer.getMoney() >= cost) {
                        currentPlayer.setMuleEmplaced(row, col, true);
                        currentPlayer.addMoney(cost * -1);
                        refreshLabels();
                        return true;
                    }
                } else {
                    return false;
                }
            } else if (phase.equals("Selling Mules")) {
                if (currentPlayer.getTileOwned(row, col) && currentPlayer.getMuleEmplaced(row, col)) {
                    int cost = 100;
                    currentPlayer.addMoney(cost);
                    currentPlayer.setMuleEmplaced(row, col, false);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean doStoreTransaction(String item, boolean buying) {
        return doStoreTransaction(item, buying, 1);
    }

    public boolean doStoreTransaction(String item, boolean buying, int amnt) {
        if (buying) {
            return store.purchaseTransaction(item, amnt);
        } else {
            return store.sellTransaction(item, amnt);
        }
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String s) {
        phase = s;
    }

    public int getTimeAfterFoodCheck() {
        int[] foodRq = {0, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
        int food = currentPlayer.getFood();
        if (roundNumber == 1) {
            return 50;
        }
        if (food >= foodRq[roundNumber]) {
            currentPlayer.addFood(foodRq[roundNumber - 1]);
            return 50;
        } else if (food == 0) {
            return 5;
        } else {
            currentPlayer.addFood(food * -1);
            return 30;
        }
    }

    public void addPlayer(Player player) { players.add(player);}
    public ArrayList<Player> getPlayers() { return players;}
    public Difficulty getDifficulty() { return difficulty;}
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty;}
    public MapType getMapType() { return mapType;}
    public Store getStore() { return store;}
    public void setMapType(MapType mapType) { this.mapType = mapType;}
    public Map getMap() { return map;}
    public int getRoundNumber() { return roundNumber;}
    public Player getCurrentPlayer() { return currentPlayer;}
    public void setMuleType(Mule t) { muleType = t;}
    public Mule getMuleType() {return muleType;}
    public void setLandCost(int c) { landCost = c;}

    /* Timer methods */
    public void decrementTimeLeft() {
        if (timeLeft == 0) {
            endTurn();
        }
        timeLeft--;
    }
    public void setTimeLeft(int time) { timeLeft = time; }
    public int getTimeLeft() { return timeLeft; }
    public void setGamble(int n) { gamble = n;}
    public int getGamble() { return gamble;}

}