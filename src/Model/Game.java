package Model;

import java.util.ArrayList;
import java.util.Comparator;

import Controller.MapController;
import Controller.MasterController;
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
        roundNumber = -1; //round -1 and 0 are land selection
        MasterController.getInstance().loadStartTurnScene();

    }

    public void startTurn() {
        MapController m = MasterController.getInstance().getMapController();
        m.setCurrentPhaseLabel("CURRENT PHASE: " + getPhase());
        m.setCurrentPlayerLabel("CURRENT PLAYER: " + currentPlayer.getName());
        if (roundNumber > 0) {
            timeLeft = 50;
            m.startTimer();
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
        players.sort(new PlayerComparator<>());
        currentPlayer = players.get(0);
        MasterController.getInstance().loadStartTurnScene();

    }

    public static class PlayerComparator<Object> implements Comparator<Player> {
        @Override
        public int compare(Player a, Player b) {
            return  a.getScore() - b.getScore();
        }
    }

    public boolean addProperty(int row, int col) {
        if (roundNumber < 1) {
            currentPlayer.setTileOwned(row, col);
            return true;
        } else if (roundNumber >= 1){
            //TODO check if player can afford the spot.
            return false;
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
        if (roundNumber < 1) {
            return "Land Selection";
        } else {
            return "Regular Turn";
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

    /* Timer methods */
    public void decrementTimeLeft() { timeLeft--; }
    public void setTimeLeft(int time) { timeLeft = time; }
    public int getTimeLeft() { return timeLeft; }


}