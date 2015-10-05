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
    String difficulty;
    String mapType;




    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(String name, String human, String race) {
            Player p = new Player(name, human, race);
            players.add(p);
            System.out.println(players);
    }

    public String getDifficulty() { return difficulty;}
    public void setDifficulty(String difficulty) { this.difficulty = difficulty;}
    public String getMapType() { return mapType;}
    public void setMapType(String mapType) { this.mapType = mapType; }




}
