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
    int roundNumber;
    int turnNumber;



    public Game() {
        map = new Map();
        players = new ArrayList<Player>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean addProperty(int row, int col) {
        if (roundNumber == 0) {
            //currentPlayer.setTileOwned(row, col);
            return true;
        } else {
            //TODO check if player can afford the spot.
            return true;
        }
    }

    public Difficulty getDifficulty() { return difficulty;}
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty;}
    public MapType getMapType() { return mapType;}
    public void setMapType(MapType mapType) { this.mapType = mapType;}
    public Map getMap() { return map;}
    public int getRoundNumber() { return roundNumber;}
    public int getTurnNumber() { return turnNumber;}




}
