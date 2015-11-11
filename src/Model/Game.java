package Model;

import Controller.MapController;
import Controller.MasterController;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import Model.Map.Difficulty;
import Model.Map.MapType;


import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
* Game class.
*/

public class Game implements Serializable {
  /**
   * List of players.
  **/
  ArrayList<Player> players;
  /**
   * Current player.
  **/
  Player currentPlayer;
  /**
   * Difficulty of the game.
  **/
  Difficulty difficulty;
  /**
   * Map type.
  **/
  MapType mapType;
  /**
   * Map.
  **/
  Map map;
  /**
   * Store.
  **/
  Store store;
  /**
   * Save object.
  **/
  Save save;
  /**
   * Round Number.
  **/
  int roundNumber;
  /**
   * Time left in the turn.
  **/
  int timeLeft;
  /**
   *  Money own from gambling.
  **/
  int gamble;
  /**
   * Phase of the game.
  **/
  String phase;
  /**
   * Type of mule.
  **/
  Mule muleType;
  /**
   * Array of events.
  **/
  Event[] events = new Event[] {Event.ONE, Event.TWO, Event.THREE,
      Event.FOUR, Event.FIVE, Event.SIX, Event.SEVEN, Event.EIGHT,
      Event.NINE, Event.TEN, Event.ELEVEN, Event.TWELVE};
  /**
   * The current event.
  **/
  Event currentEvent;
  /**
   * Cost of land.
  **/
  int landCost;
  /**
   * Single instance of Main.
  **/
  Main main;

  /**
   * Constructor for the Game object. Creates Map, Players list, and Store.
   * Initializes media and plays music.
   */
  public Game() {
    map = new Map();
    players = new ArrayList<Player>();
    store = new Store();
    save = new Save();
    // Code for the music
    final URL resource = getClass()
        .getResource("/View/Resources/music.mp3");
    final Media media = new Media(resource.toString());
    final MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();

  }

  /**
   * Starts the game. Sets difficulty, current player, round number, and loads
   * Start Turn scene.
   */
  public final void startGame() {
    if (difficulty == Difficulty.MEDIUM) {
      for (Player playcur : players) {
        playcur.addMoney((int) (playcur.getMoney() * (-.25)));
      }
    } else if (difficulty == Difficulty.HARD) {
      for (Player playcur : players) {
        System.out.println(playcur.getMoney());
        playcur.addMoney((int) (playcur.getMoney() * (-.5)));
      }
    }
    currentPlayer = players.get(0);
    roundNumber = -1; // round -1 and 0 are land selection
    setPhase("Land Selection");
    MasterController.getInstance().loadStartTurnScene();
    refreshLabels();
  }

  /**
   * Starts turn for firstplayer player. Randomly generates an
   * event that will happen for
   * this turn.
   */
  public final void startTurn() {
    Random random = new Random();
    int rand = random.nextInt(100);
    if (rand < 27 && currentPlayer != players.get(0) && roundNumber > 0) {
      rand = random.nextInt(12);
      if (rand > 10) { // event affects all players
        for (Player playcur : players) {
          currentEvent = events[rand];
          int[] modifier = currentEvent.getEffects();
          playcur.addMoney(modifier[0]);
          playcur.addFood(modifier[1]);
          playcur.addEnergy(modifier[2]);
          playcur.addSmithore(modifier[3]);
        }
      } else { // event only affects the current player
        currentEvent = events[rand];
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
   * Finishes event by resetting the time left for the player. Loads Map scene.
   */
  public final void finishEvent() {
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
   * Ends the turn and sets current player to the next player. If all players
   * have had their turn, ends round.
   */
  public final void endTurn() {
    setPhase("Regular Turn");
    if (currentPlayer == players.get(players.size() - 1)) {
      endRound();
    } else {
      currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
      MasterController.getInstance().loadStartTurnScene();
    }
  }

  /**
   * Ends round and sets up for the next round. Sorts the players in terms of
   * score, sets current player, and loads Start Turn scene.
   */

  public final void endRound() {
    roundNumber++;
    if (roundNumber >= 1) {
      players.sort(new PlayerComparator<>());
    }
    currentPlayer = players.get(0);
    MasterController.getInstance().loadStartTurnScene();

  }

  /**
   * Implementation of the Comparator class. Compares Players based on their
   * scores.
   *
   * @param <Object> Player
   */
  public static class PlayerComparator<Object> implements Comparator<Player> {
    /**
    * Compare two players.
    * @param firstplayer player
    * @param secondplayer player
    */
    @Override
    public final int compare(final Player firstplayer,
        final Player secondplayer) {
      return firstplayer.getScore() - secondplayer.getScore();
    }
  }

  /**
   * Refreshes Labels to current Player'newPhase stats and properties.
   */
  public final void refreshLabels() {
    MapController map = MasterController.getInstance().getMapController();
    map.setCurrentPhaseLabel(phase);
    map.setCurrentPlayerLabel(currentPlayer.getName());
    map.setFoodLabel("Food: " + currentPlayer.getFood());
    map.setEnergyLabel("Energy: " + currentPlayer.getEnergy());
    map.setSmithoreLabel("Smithore: " + currentPlayer.getSmithore());
    map.setMoneyLabel("Money: " + currentPlayer.getMoney());
  }

  /**
   * Checks if the operation at tile at location [row][col] is valid and sets
   * properties for that tile. If the current player'newPhase operation on
   * tile is valid, returns true.
   *
   * @param row int
   * @param col int
   * @return boolean
   */
  public final boolean tileClicked(final int row, final int col) {
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
   * Does Store transaction for 1 of given item. Buys or sells based on second
   * boolean parameter, buying.
   *
   * @param item String
   * @param buying boolean
   * @return boolean
   */
  public final boolean doStoreTransaction(final String item,
      final boolean buying) {
    return doStoreTransaction(item, buying, 1);
  }

  /**
   * Does Store transaction for given amnt of given item. Buys or sells based on
   * second boolean parameter, buying.
   *
   * @param item String
   * @param buying boolean
   * @param amnt int
   * @return boolean
   */
  public final boolean doStoreTransaction(final String item,
      final boolean buying, final int amnt) {
    if (buying) {
      return store.purchaseTransaction(item, amnt);
    } else {
      return store.sellTransaction(item, amnt);
    }
  }

  /**
   * Save data.
   * @throws IOException e
   */
  public final void saveData() throws IOException {
    save.saveData(players, map, store, this);
  }

  /**
   * Load data.
   * @throws IOException e
   */
  public final void loadData() throws IOException {
    save.loadData();
  }

  /**
   * Getter that returns the current phase.
   *
   * @return String
   */
  public final String getPhase() {
    return phase;
  }

  /**
   * Setter that sets the current phase to the given String.
   *
   * @param newPhase String
   */
  public final void setPhase(final String newPhase) {
    phase = newPhase;
  }

  /**
   * Returns number of seconds left based on the current player
   * newPhase food stock.
   *
   * @return int
   */
  public final int getTimeAfterFoodCheck() {
    int[] foodRq = {0, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
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
   * Based on firstplayer player'newPhase stock of mules, updates,
   * player'newPhase energy and continues,
   * production.
   */
  public final void checkProduction() {
    int numMules = currentPlayer.getNumMules();
    System.out.println("numMules: " + numMules);
    if (currentPlayer.getEnergy() >= numMules) {
      currentPlayer.addEnergy(numMules * -1);
      currentPlayer.doProduction();
    }
  }

  /**
   * Adds player to list of Player.
   *
   * @param player Player
   */
  public final void addPlayer(final Player player) {
    players.add(player);
  }

  /**
   * Returns list of Players.
   *
   * @return ArrayList
   */
  public final ArrayList<Player> getPlayers() {
    return players;
  }

  /**
   * Returns Difficulty enum of the game.
   *
   * @return Difficulty
   */
  public final Difficulty getDifficulty() {
    return difficulty;
  }

  /**
   * Sets Difficult enum of the game.
   *
   * @param difficulty Diff levels
   */
  public final void setDifficulty(final Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * Return the MapType enum of the game.
   *
   * @return MapType
   */
  public final MapType getMapType() {
    return mapType;
  }

  /**
   * Returns the Store of the game.
   *
   * @return Store
   */
  public final Store getStore() {
    return store;
  }

  /**
   * Sets the Store of the game.
   * @param store Store
   */
  public final void setStore(final Store store) {
    this.store = store;
  }

  /**
   * Sets MapType enum of the game.
   *
   * @param mapType map
   */
  public final void setMapType(final MapType mapType) {
    this.mapType = mapType;
  }

  /**
   * Sets the Map.
   * @param map Map
   */
  public final void setMap(final Map map) {
    this.map = map;
  }

  /**
   * Returns Map.
   *
   * @return Map
   */
  public final Map getMap() {
    return map;
  }

  /**
   * Returns round number.
   *
   * @return int
   */
  public final int getRoundNumber() {
    return roundNumber;
  }

  /**
   * Returns current player.
   *
   * @return Player
   */
  public final Player getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * Sets the mule type for given Mule newmuletype.
   *
   * @param newmuletype Mule
   */
  public final void setMuleType(final Mule newmuletype) {
    muleType = newmuletype;
  }

  /**
   * Gets the mule type for given Mule newmuletype.
   *
   * @return Mule
   */
  public final Mule getMuleType() {
    return muleType;
  }

  /**
   * Gets the current event going on.
   *
   * @return Event
   */
  public final Event getCurrentEvent() {
    return currentEvent;
  }

  /**
   * Sets the cost of land to lcost.
   *
   * @param lcost int
   */
  public final void setLandCost(final int lcost) {
    landCost = lcost;
  }

  /* Timer methods */

  /**
   * Decrements the time left by one. If time is 0, ends turn.
   */
  public final void decrementTimeLeft() {
    if (timeLeft == 0) {
      endTurn();
    }
    timeLeft--;
  }

  /**
   * Gets time left for the current player'newPhase turn.
   *
   * @return int
   */
  public final int getTimeLeft() {
    return timeLeft;
  }

  /**
   * Sets gamble amount to newgamble.
   *
   * @param newgamble int
   */
  public final void setGamble(final int newgamble) {
    gamble = newgamble;
  }

  /**
   * Gets gamble amount.
   *
   * @return int
   */
  public final int getGamble() {
    return gamble;
  }

}
