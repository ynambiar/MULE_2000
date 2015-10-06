package Model;

import java.util.ArrayList;
import Model.Map.*;


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



    public Game() {
        map = new Map();
        players = new ArrayList<Player>();
        store = new Store();
    }

    public void startGame() {
        currentPlayer = players.get(0);
        roundNumber = 0; //round 0 is land selection
    }

    public boolean addProperty(int row, int col) {
        if (roundNumber == 0) {
            currentPlayer.setTileOwned(row, col);
            currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size()); //gets next player
            if (currentPlayer == players.get(0) && currentPlayer.getNumTilesOwned() == 2) {
                roundNumber = 1;
                System.out.println("Real game starts boi");
            }
            return true;
        } else {
            //TODO check if player can afford the spot.
            return true;
        }
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

    public void endTurn() {
        if (currentPlayer == players.get(players.size() - 1)) {
            endRound();
        } else {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
            //TODO Deal with timer stuff
        }
    }

    public void startRound() {

    }

    public void endRound() {

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

    /* Timer methods */
    public void decrementTimeLeft() { timeLeft--; }
    public void setTimeLeft(int time) { timeLeft = time; }
    public int getTimeLeft() { return timeLeft; }

}