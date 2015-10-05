package Model;

import java.util.ArrayList;


/**
 * Created by tuckerlocicero on 10/2/15.
 *
 * This class should hold game logic.
 */
public class Game {

    String currentPhase; //lets everyone know what the current phase of the game is (i.e. land selection, mule emplacing, etc)
    ArrayList<Player> players;
    Player currentPlayer;
    String difficulty;
    String mapType;
    Map map;
    int roundNumber;
    int turnNumber;



    public Game() {
        map = new Map();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(String name, String human, String race) {
        Player p = new Player(name, human, race);
        players.add(p);
        System.out.println(players);
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

    public String getDifficulty() { return difficulty;}
    public void setDifficulty(String difficulty) { this.difficulty = difficulty;}
    public String getMapType() { return mapType;}
    public void setMapType(String mapType) { this.mapType = mapType;}
    public Map getMap() { return map;}
    public int getRoundNumber() { return roundNumber;}
    public int getTurnNumber() { return turnNumber;}




}
