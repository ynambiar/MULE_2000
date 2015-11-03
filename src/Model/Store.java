package Model;

import java.io.Serializable;

/**
 * Created by tuckerlocicero on 10/6/15.
 */
public class Store implements Serializable {
	private int muleStock, foodStock, energyStock, smithoreStock;
	private int mulePrice, foodPrice, energyPrice, smithorePrice;

	public Store() {
		this(14, 8, 8, 8, 100, 25, 30, 50);
	}

	public Store(int m, int f, int e, int s, int mp, int fp, int ep, int sp) {
		muleStock = m;
		foodStock = f;
		energyStock = e;
		smithoreStock = s;
		mulePrice = mp;
		foodPrice = fp;
		energyPrice = ep;
		smithorePrice = sp;
	}

	public boolean purchaseTransaction(String item) {
		return purchaseTransaction(item, 1);
	}

	public boolean purchaseTransaction(String item, int amnt) {
		Game game = Main.myGame;
		Player p = game.getCurrentPlayer();
		if (item.equals("Food") && amnt > 0) {
			if (p.getMoney() >= getFoodPrice() && getFoodStock() >= amnt) {
				System.out.println("You bought " + amnt + " Food units for $"
						+ amnt * getFoodPrice());
				p.addMoney(-1 * getFoodPrice() * amnt);
				p.addFood(amnt);
				addFoodStock(-amnt);
				System.out.println("Your wallet has $" + p.getMoney()
						+ " in it, and you have " + p.getFood()
						+ " food units.");
				return true;
			} else if (getFoodStock() < amnt) {
				System.out
						.println("Bazaar says: Sorry, but we dont have that much Food!");
				return false;
			} else {
				System.out.println("You only have $" + p.getMoney()
						+ ". That's not enough!");
				return false;
			}
		} else if (item.equals("Food") && amnt < 0) {
			if (p.getFood() >= -1 * amnt) {
				System.out.println("You sold " + -1 * amnt
						+ " Food units for $" + -1 * amnt
						* (getFoodPrice() - 10));
				p.addMoney((getFoodPrice() - 10) * -amnt);
				p.addFood(amnt);
				addFoodStock(-amnt);
				System.out.println("Your wallet has $" + p.getMoney()
						+ " in it, and you have " + p.getFood()
						+ " Food units.");
				return true;
			} else {
				System.out.println("You don't have that much Food to sell!");
				return false;
			}

		} else if (item.equals("Energy") && amnt > 0) {
			if (p.getMoney() >= getEnergyPrice() && getEnergyStock() >= amnt) {
				System.out.println("You bought " + amnt + " Energy units for $"
						+ amnt * getEnergyPrice());
				p.addMoney(-1 * getEnergyPrice() * amnt);
				p.addEnergy(amnt);
				addEnergyStock(-amnt);
				System.out.println("Your wallet has $" + p.getMoney()
						+ " in it, and you have " + p.getEnergy()
						+ " Energy units.");
				return true;
			} else if (getFoodStock() < amnt) {
				System.out
						.println("Bazaar says: Sorry, but we dont have that much Energy!");
				return false;
			} else {
				System.out.println("You only have $" + p.getMoney()
						+ ". That's not enough!");
				return false;
			}
		} else if (item.equals("Energy") && amnt < 0) {
			if (p.getEnergy() >= -1 * amnt) {
				System.out.println("You sold " + -1 * amnt
						+ " Energy units for $" + -1 * amnt
						* (getEnergyPrice() - 10));
				p.addMoney((getEnergyPrice() - 10) * -amnt);
				p.addEnergy(amnt);
				addEnergyStock(-amnt);
				System.out.println("Your wallet has $" + p.getMoney()
						+ " in it, and you have " + p.getEnergy()
						+ " Energy units.");
				return true;
			} else {
				System.out.println("You don't have that much Energy to sell!");
				return false;
			}

		} else if (item.equals("Smithore") && amnt > 0) {
			if (p.getMoney() >= getSmithorePrice()
					&& getSmithoreStock() >= amnt) {
				System.out.println("You bought " + amnt
						+ " Smithore units for $" + amnt * getSmithorePrice());
				p.addMoney(-1 * getSmithorePrice() * amnt);
				p.addSmithore(amnt);
				addSmithoreStock(-amnt);
				System.out.println("Your wallet has $" + p.getMoney()
						+ " in it, and you have " + p.getSmithore()
						+ " Smithore units.");
				return true;
			} else if (getSmithoreStock() < amnt) {
				System.out
						.println("Bazaar says: Sorry, but we dont have that much Smithore!");
				return false;
			} else {
				System.out.println("You only have $" + p.getMoney()
						+ ". That's not enough!");
				return false;
			}
		} else if (item.equals("Smithore") && amnt < 0) {
			if (p.getSmithore() >= -1 * amnt) {
				System.out.println("You sold " + -1 * amnt
						+ " Smithore units for $" + -1 * amnt
						* (getSmithorePrice() - 10));
				p.addMoney((getSmithorePrice() - 10) * -amnt);
				p.addSmithore(amnt);
				addSmithoreStock(-amnt);
				System.out.println("Your wallet has $" + p.getMoney()
						+ " in it, and you have " + p.getSmithore()
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

	public boolean sellTransaction(String item) {
		return sellTransaction(item, 1);
	}

	public boolean sellTransaction(String item, int amnt) {
		return false;
	}

	public void addMuleStock(int m) {
		muleStock = muleStock + m;
	}

	public void addFoodStock(int f) {
		foodStock = foodStock + f;
	}

	public void addEnergyStock(int e) {
		energyStock = energyStock + e;
	}

	public void addSmithoreStock(int s) {
		smithoreStock = smithoreStock + s;
	}

	public void setMuleStock(int muleStock) {
		this.muleStock = muleStock;
	}

	public void setFoodStock(int foodStock) {
		this.foodStock = foodStock;
	}

	public void setEnergyStock(int energyStock) {
		this.energyStock = energyStock;
	}

	public void setSmithoreStock(int smithoreStock) {
		this.smithoreStock = smithoreStock;
	}

	public int getMuleStock() {
		return muleStock;
	}

	public int getFoodStock() {
		return foodStock;
	}

	public int getEnergyStock() {
		return energyStock;
	}

	public int getSmithoreStock() {
		return smithoreStock;
	}

	public int getMulePrice() {
		return mulePrice;
	}

	public int getFoodPrice() {
		return foodPrice;
	}

	public int getEnergyPrice() {
		return energyPrice;
	}

	public int getSmithorePrice() {
		return smithorePrice;
	}

	public void setMulePrice(int mulePrice) {
		this.mulePrice = mulePrice;
	}

	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}

	public void setEnergyPrice(int energyPrice) {
		this.energyPrice = energyPrice;
	}

	public void setSmithorePrice(int smithorePrice) {
		this.smithorePrice = smithorePrice;
	}
}
