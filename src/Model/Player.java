package Model;

/**
 * Created by tuckerlocicero on 10/2/15.
 */
public class Player {

    //These three fields DO NOT require setters, because they should not change
    //after being instantiated with the constructor
    private String name;
    private String human;
    private String race;
    private String color;
    private boolean[][] tilesOwned;
    private int numTilesOwned;


    //These fields DO require setters
    private int money;

    public Player(String human, String race, String name, String color) {
        this.name = name;
        this.human = human;
        this.race = race;
        this.color = color;
        tilesOwned = new boolean[Main.myGame.getMap().getHeight()][Main.myGame.getMap().getWidth()];
        if (race.equals("Flapper")) {
            this.money = 1600;
        } else if (race.equals("Human")) {
            this.money = 600;
        } else {
            this.money = 1000;
        }
    }

    public int getNumTilesOwned() {
        int owned = 0;
        for (int i = 0; i < tilesOwned.length; i++) {
            for (int j = 0; j < tilesOwned[i].length; j++) {
                if (tilesOwned[i][j]) { owned++;}
            }
        }
        return owned;
    }
    public String getName() { return name;}
    public String getHuman() { return human;}
    public String getRace() { return race;}
    public int getMoney() { return money;}
    public String getColor() { return color; }
    public void setTileOwned(int row, int col) {tilesOwned[row][col] = true;}
    public String toString() { return  human + " player " + name + " is a " + race;}
}
