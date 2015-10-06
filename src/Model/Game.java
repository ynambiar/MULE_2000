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

    public Store getStore() {
        return store;
    }

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

    public void doStoreTransaction(String item) {
        if (item.equals("Food")) {
            if (currentPlayer.getMoney() >= 30 && store.getFoodStock() >= 1) {
                System.out.println("You bought a Food unit for $30");
                currentPlayer.addMoney(-30);
                currentPlayer.addFood(1);
                store.addFoodStock(-1);
                System.out.println("Your wallet has $" + currentPlayer.getMoney() + " in it, and you have " + currentPlayer.getFood() + " food units.");
            } else if (store.getFoodStock() < 1) {
                System.out.println("Bazaar says: Sorry, but we're all out of Food!");
            } else {
                System.out.println("You only have $" + currentPlayer.getMoney() + ". That's not enough!");
            }
        } else if (item.equals("Energy")) {
            if (currentPlayer.getMoney() >= 25 && store.getEnergyStock() >= 1) {
                System.out.println("You bought a Energy unit for $25");
                currentPlayer.addMoney(-25);
                currentPlayer.addEnergy(1);
                store.addEnergyStock(-1);
                System.out.println("Your wallet has $" + currentPlayer.getMoney() + " in it, and you have " + currentPlayer.getEnergy() + " energy units.");
            } else if (store.getEnergyStock() < 1) {
                System.out.println("Bazaar says: Sorry, but we're all out of Energy!");
            } else {
                System.out.println("You only have $" + currentPlayer.getMoney() + ". That's not enough!");
            }
        } else if (item.equals("Smithore")) {
            if (currentPlayer.getMoney() >= 50 && store.getSmithoreStock() >= 1) {
                System.out.println("You bought a Smithore unit for $50");
                currentPlayer.addMoney(-50);
                currentPlayer.addSmithore(1);
                store.addEnergyStock(-1);
                System.out.println("Your wallet has $" + currentPlayer.getMoney() + " in it, and you have " + currentPlayer.getSmithore() + " energy units.");
            } else if (store.getSmithoreStock() < 1) {
                System.out.println("Bazaar says: Sorry, but we're all out of Energy!");
            } else {
                System.out.println("You only have $" + currentPlayer.getMoney() + ". That's not enough!");
            }
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
    public void setMapType(MapType mapType) { this.mapType = mapType;}
    public Map getMap() { return map;}
    public int getRoundNumber() { return roundNumber;}
    public Player getCurrentPlayer() { return currentPlayer;}

    /* Timer methods */
    public void decrementTimeLeft() { timeLeft--; }
    public void setTimeLeft(int time) { timeLeft = time; }
    public int getTimeLeft() { return timeLeft; }

}