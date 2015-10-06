package Model;

/**
 * Created by tuckerlocicero on 10/6/15.
 */
public class Store {
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
        if (item.equals("Food")) {
            if (p.getMoney() >= getFoodPrice() && getFoodStock() >= 1) {
                System.out.println("You bought a Food unit for $30");
                p.addMoney(-30);
                p.addFood(1);
                addFoodStock(-1);
                System.out.println("Your wallet has $" + p.getMoney() + " in it, and you have " + p.getFood() + " food units.");
                return true;
            } else if (getFoodStock() < 1) {
                System.out.println("Bazaar says: Sorry, but we're all out of Food!");
                return false;
            } else {
                System.out.println("You only have $" + p.getMoney() + ". That's not enough!");
                return false;
            }
        } else if (item.equals("Energy")) {
            if (p.getMoney() >= getEnergyPrice() && getEnergyStock() >= 1) {
                System.out.println("You bought an Energy unit for $25");
                p.addMoney(-25);
                p.addEnergy(1);
                addEnergyStock(-1);
                System.out.println("Your wallet has $" + p.getMoney() + " in it, and you have " + p.getEnergy() + " energy units.");
                return true;
            } else if (getEnergyStock() < 1) {
                System.out.println("Bazaar says: Sorry, but we're all out of Energy!");
                return false;
            } else {
                System.out.println("You only have $" + p.getMoney() + ". That's not enough!");
                return false;
            }
        } else if (item.equals("Smithore")) {
            if (p.getMoney() >= getSmithorePrice() && getSmithoreStock() >= 1) {
                System.out.println("You bought a Smithore unit for $50");
                p.addMoney(-50);
                p.addSmithore(1);
                addEnergyStock(-1);
                System.out.println("Your wallet has $" + p.getMoney() + " in it, and you have " + p.getSmithore() + " energy units.");
                return true;
            } else if (getSmithoreStock() < 1) {
                System.out.println("Bazaar says: Sorry, but we're all out of Energy!");
                return false;
            } else {
                System.out.println("You only have $" + p.getMoney() + ". That's not enough!");
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
