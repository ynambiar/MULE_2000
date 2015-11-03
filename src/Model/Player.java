package Model;

import java.io.Serializable;

public class Player implements Serializable {


	final private String name;
	final private String race;
	final private String color;
	final private String human;
	private boolean[][] tilesOwned;

	private Mule[][] muleEmplaced;
	private int roundNumber;
	private int playerTime;

	private int money, food, smithore, energy;

	/**
	 * Adds m number of dollars to player's money.
	 * @param m
	 */
	public void addMoney(int m) {
		money = money + m;
	}

	/**
	 * Adds f number of food to player's food.
	 * @param f
	 */
	public void addFood(int f) {
		food = food + f;
	}

	/**
	 * Adds s number of smithore to player's smithore.
	 * @param s
	 */
	public void addSmithore(int s) {
		smithore = smithore + s;
	}

	/**
	 * Adds e number of energy to player's energy.
	 * @param e
	 */
	public void addEnergy(int e) {
		energy = energy + e;
	}

	/**
	 * Construtor for Player that sets the human (human/AI), race,
	 * name, and color properties.
	 *
	 * Based on race, money is allocated to the player.
	 * @param human
	 * @param race
	 * @param name
	 * @param color
	 */
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
		} else if (race.equals("Pegasite")) {
			this.money = 550;
			this.food = 10;
		} else if (race.equals("Techoid")) {
			this.money = 2000;
			this.energy = 15;
		} else if (race.equals("Strandoid")) {
			this.money = 200;
			this.smithore = 10;
		} else {
			this.money = 1000;
		}
	}

	/**
	 * Returns score based on player's
	 * money, food, energy, smithore, and property owned.
	 * @return
	 */
	public int getScore() {
		Store store = Main.myGame.getStore();
		int foodCost = store.getFoodPrice();
		int energyCost = store.getEnergyPrice();
		int smithoreCost = store.getSmithorePrice();
		int propertyCost = getNumTilesOwned() * 500;
		return money + (food * foodCost) + (energy * energyCost)
				+ (smithore * smithoreCost) + propertyCost;
	}

	/**
	 * Returns number of tiles the player owns.
	 * @return
	 */
	public int getNumTilesOwned() {
		int owned = 0;
		for (int i = 0; i < tilesOwned.length; i++) {
			for (int j = 0; j < tilesOwned[i].length; j++) {
				if (tilesOwned[i][j]) {
					owned++;
				}
			}
		}
		return owned;
	}

	/**
	 * Returns the number of mules a player has emplaced.
	 * @return
	 */
	public int getNumMules() {
		int emplaced = 0;
		for (int i = 0; i < muleEmplaced.length; i++) {
			for (int j = 0; j < muleEmplaced[i].length; j++) {
				if (muleEmplaced[i][j] != null) {
					emplaced++;
				}
			}
		}
		return emplaced;
	}

	/**
	 * Produces food or energy or smithore based on the mule
	 * emplaced by the Player.
	 */
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

	/**
	 * Returns name of player.
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns how much money a player has.
	 * @return
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * Returns player's color.
	 * @return
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Returns how many units of food player has.
	 * @return
	 */
	public int getFood() {
		return food;
	}

	/**
	 * Returns how many units of smithore player has.
	 * @return
	 */
	public int getSmithore() {
		return smithore;
	}

	/**
	 * Returns how many units of energy player has.
	 * @return
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * Sets tile at [row][col] to owned.
	 * @param row
	 * @param col
	 * @param b
	 */
	public void setTileOwned(int row, int col, boolean b) {
		tilesOwned[row][col] = b;
	}

	/**
	 * Returns if tile at [row][col] is owned.
	 * @param row
	 * @param col
	 * @return
	 */

	public boolean getTileOwned(int row, int col) {
		return tilesOwned[row][col];
	}

	/**
	 * Sets tile at [row][col] to have mule.
	 * @param row
	 * @param col
	 * @param m
	 */
	public void setMuleEmplaced(int row, int col, Mule m) {
		muleEmplaced[row][col] = m;
	}

	/**
	 * Returns if tile at [row][col] has mule.
	 * @param row
	 * @param col
	 * @return
	 */
	public Mule getMuleEmplaced(int row, int col) {
		return muleEmplaced[row][col];
	}

	/**
	 * toString() for Player.
	 * @return
	 */
	public String toString() {
		return human + " player " + name + " is a " + race;
	}

	/**
	 * Sets amount of time player has left in turn.
	 * @param i
	 */
	public void setPlayerTime(int i) {
		playerTime = i;
	}
}
