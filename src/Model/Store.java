package Model;

/**
 * Created by tuckerlocicero on 10/6/15.
 */
public class Store {
    private int muleStock, foodStock, energyStock, smithoreStock;

    public Store() {
        this.muleStock = 14;
        this.foodStock = 8;
        this.energyStock = 8;
        this.smithoreStock = 8;
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
}
