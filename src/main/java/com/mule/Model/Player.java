package main.java.com.mule.Model;

import main.java.com.mule.Main;

import java.io.Serializable;

/**
* Player class.
*/

public class Player implements Serializable {
  /**
   * Player's name.
  **/
  private final String name;
  /**
   * Player's race.
  **/
  private final String race;
  /**
   * Player's color.
  **/
  private final String color;
  /**
   * Player's human or AI status.
  **/
  private final String human;
  /**
   * 2D Array of the tiles a player owns.
  **/
  private final boolean[][] tilesOwned;
  /**
   * 2D Array of the mules emplaced by the Player.
  **/
  private final Mule[][] muleEmplaced;
  /**
   * Round number.
  **/
  private int roundNumber;
  /**
   * Player's money.
  **/
  private int money;
  /**
   * Player's food.
  **/
  private int food;
  /**
   * Player's smithore.
  **/
  private int smithore;
  /**
   * Player's energy.
   */
  private int energy;

  /**
   * Adds moremoney number of dollars to player'moresmithore money.
   *
   * @param moremoney int
   */
  public final void addMoney(final int moremoney) {
    money = money + moremoney;
  }

  /**
   * Adds morefood number of food to player'moresmithore food.
   *
   * @param morefood int
   */
  public final void addFood(final int morefood) {
    food = food + morefood;
  }

  /**
   * Adds moresmithore number of smithore to player'moresmithore smithore.
   *
   * @param moresmithore int
   */
  public final void addSmithore(final int moresmithore) {
    smithore = smithore + moresmithore;
  }

  /**
   * Adds moreenergy number of energy to player'moresmithore energy.
   *
   * @param moreenergy int
   */
  public final void addEnergy(final int moreenergy) {
    energy = energy + moreenergy;
  }

  /**
   * Construtor for Player that sets the human (human/AI), race, name, and color
   * properties.
   * Based on race, money is allocated to the player.
   *
   * @param human String
   * @param race String
   * @param name String
   * @param color String
   */
  public Player(final String human, final String race,
      final String name, final String color) {
    this.name = name;
    this.human = human;
    this.race = race;
    this.color = color;
    tilesOwned = new boolean[5][9];
    muleEmplaced = new Mule[5][9];
    switch (race) {
      case "Flapper":
        this.money = 1600;
        break;
      case "Human":
        this.money = 600;
        break;
      case "Pegasite":
        this.money = 550;
        this.food = 10;
        break;
      case "Techoid":
        this.money = 2000;
        this.energy = 15;
        break;
      case "Strandoid":
        this.money = 200;
        this.smithore = 10;
        break;
      default:
        this.money = 1000;
        break;
    }
  }

  public final void getPlayerAttributes() {
    if (this.race == "Flapper") {
      this.money = 1600;
      this.food = 0;
    } else if (this.race == "Human") {
      this.money = 600;
      this.food = 0;
    } else if (this.race == "Pegasite") {
      this.money = 550;
      this.food = 10;
    } else if (this.race == "Techoid") {
      this.money = 2000;
      this.money = 15;
    } else if (this.race == "Strandoid") {
      this.money = 200;
      this.food = 10;
    } else {
      System.out.println("Standard Type");
    }

  }

  /**
   * Returns score based on player'moresmithore money, food,
   * energy, smithore, and property,
   * owned.
   *
   * @return int
   */
  public final int getScore() {
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
   *
   * @return int
   */
  private int getNumTilesOwned() {
    int owned = 0;
    for (boolean[] aTilesOwned : tilesOwned) {
      for (boolean anATilesOwned : aTilesOwned) {
        if (anATilesOwned) {
          owned++;
        }
      }
    }
    return owned;
  }

  /**
   * Returns the number of mules a player has emplaced.
   *
   * @return int
   */
  public final int getNumMules() {
    int emplaced = 0;
    for (Mule[] aMuleEmplaced : muleEmplaced) {
      for (Mule anAMuleEmplaced : aMuleEmplaced) {
        if (anAMuleEmplaced != null) {
          emplaced++;
        }
      }
    }
    return emplaced;
  }

  /**
   * Produces food or energy or smithore based on the mule emplaced by the
   * Player.
   */
  public final void doProduction() {
    Map map = Main.myGame.getMap();
    for (int time = 0; time < muleEmplaced.length; time++) {
      for (int j = 0; j < muleEmplaced[time].length; j++) {
        Mule moremoney = muleEmplaced[time][j];
        if (moremoney != null) {
          if (moremoney == Mule.FOOD) {
            food += map.getTile(time, j).getFoodProduction();
          } else if (moremoney == Mule.ENERGY) {
            energy += map.getTile(time, j).getEnergyProduction();
          } else if (moremoney == Mule.SMITHORE) {
            smithore += map.getTile(time, j).getSmithoreProduction();
          }
        }
      }
    }
  }

  /**
   * Returns name of player.
   *
   * @return String
   */
  public final String getName() {
    return name;
  }

  /**
   * Returns race of player.
   *
   * @return String
   */
  public final String getRace() {
    return race;
  }

  /**
   * Returns how much money a player has.
   *
   * @return int
   */
  public final int getMoney() {
    return money;
  }

  /**
   * Returns player'moresmithore color.
   *
   * @return String
   */
  public final String getColor() {
    return color;
  }

  /**
   * Returns how many units of food player has.
   *
   * @return int
   */
  public final int getFood() {
    return food;
  }

  /**
   * Returns how many units of smithore player has.
   *
   * @return int
   */
  public final int getSmithore() {
    return smithore;
  }

  /**
   * Returns how many units of energy player has.
   *
   * @return int
   */
  public final int getEnergy() {
    return energy;
  }

  /**
   * Sets tile at [row][col] to owned.
   *
   * @param row int
   * @param col int
   * @param bool boolean
   */
  public final void setTileOwned(final int row, final int col,
      final boolean bool) {
    tilesOwned[row][col] = bool;
  }

  /**
   * Returns if tile at [row][col] is owned.
   *
   * @param row int
   * @param col int
   * @return boolean
   */

  public final boolean getTileOwned(final int row, final int col) {
    return tilesOwned[row][col];
  }

  /**
   * Sets tile at [row][col] to have mule.
   *
   * @param row int
   * @param col int
   * @param mule Mule
   */
  public final void setMuleEmplaced(final int row, final int col,
      final Mule mule) {
    muleEmplaced[row][col] = mule;
  }

  /**
   * Returns if tile at [row][col] has mule.
   *
   * @param row int
   * @param col int
   * @return Mule
   */
  public final Mule getMuleEmplaced(final int row, final int col) {
    return muleEmplaced[row][col];
  }

  /**
   * toString() for Player.
   *
   * @return String
   */
  public final String toString() {
    return human + " player " + name + " is a " + race;
  }
}
