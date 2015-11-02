package Model;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by yamininambiar on 10/3/15.
 */
public enum MapTile implements Serializable {
    P(4,2,0), R(2,3,1), M1(1,1,2), M2(1,1,3), M3(1,1,4), Town(0,0,0), F(5,5,5);



    private Map<String, String> imagePath;
    private int foodProduction, energyProduction, smithoreProduction;

    MapTile(int f, int e, int s) {
        foodProduction = f;
        energyProduction = e;
        smithoreProduction = s;
        imagePath = new HashMap<String, String>();
        imagePath.put("P", "/View/Resources/TilePlain.png");
        imagePath.put("R", "/View/Resources/TileRiver.png");
        imagePath.put("M1", "/View/Resources/TileMountain1.png");
        imagePath.put("M2", "/View/Resources/TileMountain2.png");
        imagePath.put("M3", "/View/Resources/TileMountain3.png");
        imagePath.put("Town", "/View/Resources/TileTown.png");
        imagePath.put("F", "/View/Resources/TileFlowers.png");
    }

    public int getFoodProduction() {
        return foodProduction;
    }

    public int getEnergyProduction() {
        return energyProduction;
    }

    public int getSmithoreProduction() {
        return smithoreProduction;
    }

    public String imagePath() {
        return imagePath.getOrDefault(this.name(), imagePath.get("P"));
    }
}
