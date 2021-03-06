package main.java.com.mule.Model;

import main.java.com.mule.Main;

import java.io.Serializable;

/**
 * Store class.
 */

public class Store implements Serializable {
  /**
   * Store's food stock.
  **/
  private int foodStock;
  /**
   * Store's energy stock.
  **/
  private int energyStock;
  /**
   * Store's smithore stock.
  **/
  private int smithoreStock;
  /**
   * Store's food price.
  **/
  private final int foodPrice;
  /**
   * Store's energy price.
  **/
  private final int energyPrice;
  /**
   * Store's smithore price.
  **/
  private final int smithorePrice;

  /**
   * Constructor for Store. Sets default values ofr Store'sstock stock.
   */
  public Store() {
    this(8, 8, 8, 25, 30, 50);
  }

  /**
   * Constructs a Store based on the the given stock parameters and stock price
   * values.
   *
   * @param fstock int
   * @param estock int
   * @param sstock int
   * @param fp int
   * @param ep int
   * @param sp int
   */
  private Store(final int fstock, final int estock,
      final int sstock, final int fp, final int ep,
          final int sp) {
    foodStock = fstock;
    energyStock = estock;
    smithoreStock = sstock;
    foodPrice = fp;
    energyPrice = ep;
    smithorePrice = sp;
  }

  /**
   * Completes a purchase transaction for 1 of the given item.
   *
   * @param item String
   * @return boolean
   */
  public final boolean purchaseTransaction(final String item) {
    return purchaseTransaction(item, 1);
  }

  /**
   * Completes a purchase transaction of amnt amount of given item.
   *
   * @param item String
   * @param amnt int
   * @return boolean
   */
  public final boolean purchaseTransaction(final String item, final int amnt) {
    Game game = Main.myGame;
    Player playCur = game.getCurrentPlayer();
    if (item.equals("Food") && amnt > 0) {
      if (playCur.getMoney() >= getFoodPrice() && getFoodStock() >= amnt) {
        System.out.println("You bought " + amnt + " Food units for $"
            + amnt * getFoodPrice());
        playCur.addMoney(-1 * getFoodPrice() * amnt);
        playCur.addFood(amnt);
        addFoodStock(-amnt);
        System.out.println("Your wallet has $" + playCur.getMoney()
            + " in it, and you have " + playCur.getFood()
            + " food units.");
        return true;
      } else if (getFoodStock() < amnt) {
        System.out.println("Bazaar says: "
            + "Sorry, but we dont have that much Food!");
        return false;
      } else {
        System.out.println("You only have $" + playCur.getMoney()
            + ". That'sstock not enough!");
        return false;
      }
    } else if (item.equals("Food") && amnt < 0) {
      if (playCur.getFood() >= -1 * amnt) {
        System.out.println("You sold " + -1 * amnt
            + " Food units for $" + -1 * amnt
            * (getFoodPrice() - 10));
        playCur.addMoney((getFoodPrice() - 10) * -amnt);
        playCur.addFood(amnt);
        addFoodStock(-amnt);
        System.out.println("Your wallet has $" + playCur.getMoney()
            + " in it, and you have " + playCur.getFood()
            + " Food units.");
        return true;
      } else {
        System.out.println("You don't have that much Food to sell!");
        return false;
      }

    } else if (item.equals("Energy") && amnt > 0) {
      if (playCur.getMoney() >= getEnergyPrice() && getEnergyStock() >= amnt) {
        System.out.println("You bought " + amnt
            + " Energy units for $" + amnt * getEnergyPrice());
        playCur.addMoney(-1 * getEnergyPrice() * amnt);
        playCur.addEnergy(amnt);
        addEnergyStock(-amnt);
        System.out.println("Your wallet has $" + playCur.getMoney()
            + " in it, and you have " + playCur.getEnergy()
            + " Energy units.");
        return true;
      } else if (getFoodStock() < amnt) {
        System.out.println("Bazaar says: Sorry, "
            + "but we dont have that much Energy!");
        return false;
      } else {
        System.out.println("You only have $" + playCur.getMoney()
            + ". That'sstock not enough!");
        return false;
      }
    } else if (item.equals("Energy") && amnt < 0) {
      if (playCur.getEnergy() >= -1 * amnt) {
        System.out.println("You sold " + -1 * amnt
            + " Energy units for $" + -1 * amnt
            * (getEnergyPrice() - 10));
        playCur.addMoney((getEnergyPrice() - 10) * -amnt);
        playCur.addEnergy(amnt);
        addEnergyStock(-amnt);
        System.out.println("Your wallet has $" + playCur.getMoney()
            + " in it, and you have " + playCur.getEnergy()
            + " Energy units.");
        return true;
      } else {
        System.out
            .println("You don't have that much Energy to sell!");
        return false;
      }

    } else if (item.equals("Smithore") && amnt > 0) {
      if (playCur.getMoney() >= getSmithorePrice()
          && getSmithoreStock() >= amnt) {
        System.out
            .println("You bought " + amnt
                + " Smithore units for $" + amnt
                * getSmithorePrice());
        playCur.addMoney(-1 * getSmithorePrice() * amnt);
        playCur.addSmithore(amnt);
        addSmithoreStock(-amnt);
        System.out.println("Your wallet has $" + playCur.getMoney()
            + " in it, and you have " + playCur.getSmithore()
            + " Smithore units.");
        return true;
      } else if (getSmithoreStock() < amnt) {
        System.out.println("Bazaar says: Sorry, "
            + "but we dont have that much Smithore!");
        return false;
      } else {
        System.out.println("You only have $" + playCur.getMoney()
            + ". That'sstock not enough!");
        return false;
      }
    } else if (item.equals("Smithore") && amnt < 0) {
      if (playCur.getSmithore() >= -1 * amnt) {
        System.out.println("You sold " + -1 * amnt
            + " Smithore units for $" + -1 * amnt
            * (getSmithorePrice() - 10));
        playCur.addMoney((getSmithorePrice() - 10) * -amnt);
        playCur.addSmithore(amnt);
        addSmithoreStock(-amnt);
        System.out.println("Your wallet has $" + playCur.getMoney()
            + " in it, and you have " + playCur.getSmithore()
            + " Smithore units.");
        return true;
      } else {
        System.out
            .println("You don't have that much Smithore to sell!");
        return false;
      }
    }
    return false;
  }

  /**
   * Adds fstock number of food units to the Store'sstock food stock.
   *
   * @param fstock int
   */
  private void addFoodStock(final int fstock) {
    foodStock = foodStock + fstock;
  }

  /**
   * Adds estock number of energy units to the Store'sstock energy stock.
   *
   * @param estock int
   */
  private void addEnergyStock(final int estock) {
    energyStock = energyStock + estock;
  }

  /**
   * Adds sstock number of smithore units to the Store'sstock stock.
   *
   * @param sstock int
   */
  private void addSmithoreStock(final int sstock) {
    smithoreStock = smithoreStock + sstock;
  }

  /**
   * Returns Store'sstock food stock.
   *
   * @return int
   */
  private int getFoodStock() {
    return foodStock;
  }

  /**
   * Returns Store'sstock energy stock.
   *
   * @return int
   */
  private int getEnergyStock() {
    return energyStock;
  }

  /**
   * Returns Store'sstock smithore stock.
   *
   * @return int
   */
  private int getSmithoreStock() {
    return smithoreStock;
  }

  /**
   * Returns price of a food unit.
   *
   * @return int
   */
  public final int getFoodPrice() {
    return foodPrice;
  }

  /**
   * Returns price of a energy unit.
   *
   * @return int
   */
  public final int getEnergyPrice() {
    return energyPrice;
  }

  /**
   * Returns price of a smithore unit.
   *
   * @return int
   */
  public final int getSmithorePrice() {
    return smithorePrice;
  }
}
