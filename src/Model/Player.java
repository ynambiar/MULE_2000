package Model;

import Controller.MasterController;
import com.sun.org.apache.xerces.internal.xs.StringList;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by tuckerlocicero on 10/2/15.
 */
public class Player {

    //These three fields DO NOT require setters, because they should not change
    //after being instantiated with the constructor
    private String name;
    private String race;
    private String color;
    private String human;
    private boolean[][] tilesOwned;
    private Mule[][] muleEmplaced;
    private int roundNumber;
    private int playerTime;



    //These fields DO require setters
    private int money, food, smithore, energy;

    public void addMoney(int m) {
        money = money + m;
    }

    public void addFood(int f) {
        food = food + f;
    }

    public void addSmithore(int s) {
        smithore = smithore + s;
    }

    public void addEnergy(int e) {
        energy = energy + e;
    }

    public Player(String human, String race, String name, String color) {
        this.name = name;
        this.human = human;
        this.race = race;
        this.color = color;
        tilesOwned = new boolean[5][9];
        muleEmplaced = new Mule[5][9];
        if (race.equals("Flapper")) {
            this.money = 1600;
        } else if (race.equals("Human")) {
            this.money = 600;
        } else {
            this.money = 1000;
        }
    }

    public int getScore() {
        Store store = Main.myGame.getStore();
        int foodCost = store.getFoodPrice();
        int energyCost = store.getEnergyPrice();
        int smithoreCost = store.getSmithorePrice();
        int propertyCost = getNumTilesOwned() * 500;
        return money + foodCost + energyCost + smithoreCost + propertyCost;
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

    public int getNumMules() {
        int emplaced = 0;
        for (int i = 0; i < muleEmplaced.length; i++) {
            for (int j = 0; j < muleEmplaced[i].length; j++) {
                if (muleEmplaced[i][j] != null) { emplaced++;}
            }
        }
        return emplaced;
    }

    public void doProduction() {
        Map map = Main.myGame.getMap();
        for (int i = 0; i < muleEmplaced.length; i++) {
            for (int j = 0; j < muleEmplaced[i].length; j++) {
                Mule m = muleEmplaced[i][j];
                if (m != null) {
                    if (m == Mule.FOOD) {
                        food += map.getTile(i, j).getFoodProduction();
                    } else if (m == Mule.ENERGY) {
                        energy += map.getTile(i, j).getEnergyProduction();
                    } else if (m == Mule.SMITHORE) {
                        smithore += map.getTile(i, j).getSmithoreProduction();
                    }
                }
            }
        }
    }

    public String getName() { return name;}
    public String getHuman() { return human;}
    public String getRace() { return race;}
    public int getMoney() { return money;}
    public String getColor() { return color;}
    public int getFood() {return food;}
    public int getSmithore() {return smithore;}
    public int getEnergy() {return energy;}
    public void setTileOwned(int row, int col, boolean b) {tilesOwned[row][col] = b;}
    public boolean getTileOwned(int row, int col) {return tilesOwned[row][col];}
    public void setMuleEmplaced(int row, int col, Mule m) {muleEmplaced[row][col] = m;}
    public Mule getMuleEmplaced(int row, int col) {return muleEmplaced[row][col];}
    public String toString() { return  human + " player " + name + " is a " + race;}
    public void setPlayerTime(int i) {
        playerTime = i;
    }

    public int getPlayerTime() {
        return playerTime;
    }

    public void setRoundNumber(int r) {
        roundNumber = r;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

        public int calculatePlayerTime(Player p) {
        if(roundNumber == 1) /**&& p.getFood() < 3 && p.getFood() > 0)**/ {
            p.setPlayerTime(50);
            return 50;
        } else if(roundNumber == 2 && p.getFood() < 3 && p.getFood() > 0) {
            p.setPlayerTime(30);
            return 30;
        } else if(roundNumber == 3 && p.getFood() < 3 && p.getFood() > 0) {
            p.setPlayerTime(30);
            return 30;
        } else if(roundNumber == 4 && p.getFood() < 3 && p.getFood() > 0) {
            p.setPlayerTime(30);
            return 30;
        } else if(roundNumber == 5 && p.getFood() < 4 && p.getFood() > 0) {
            p.setPlayerTime(30);
            return 30;
        } else if(roundNumber == 6 && p.getFood() < 4 && p.getFood() > 0) {
            p.setPlayerTime(30);
            return 30;
        } else if(roundNumber == 7 && p.getFood() < 4 && p.getFood() > 0) {
            p.setPlayerTime(30);
            return 30;
        } else if(roundNumber == 8 && p.getFood() < 4 && p.getFood() > 0) {
            p.setPlayerTime(30);
            return 30;
        } else if(roundNumber == 9 && p.getFood() < 5 && p.getFood() > 0) {
            p.setPlayerTime(30);
            return 30;
        } else if(roundNumber == 10 && p.getFood() < 5 && p.getFood() > 0) {
            p.setPlayerTime(30);
            return 30;
        } else if(roundNumber == 11 && p.getFood() < 5 && p.getFood() > 0) {
            p.setPlayerTime(30);
            return 30;
        } else if(roundNumber == 12 && p.getFood() < 5 && p.getFood() > 0) {
            p.setPlayerTime(30);
            return 30;
        } else {
            p.setPlayerTime(5);
            return 5;
        }
    }
}
