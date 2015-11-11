package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public enum MapTile implements Serializable {
    //NEED JAVADOCS FOR ALL PVT/LOCAL VARIABLSE
    P(4, 2, 0), R(2, 3, 1), M1(1, 1, 2), M2(1, 1, 3),
      M3(1, 1, 4), Town(0, 0, 0), F(5, 5, 5);
  /**
   * URL paths of the image, <tile type, URL>.
   */
  private Map<String, String> imagePath;
  /**
   * Integer amount of food production.
   */
  private int foodProduction;
  /**
   * Integer amount of energy production.
   */
  private int energyProduction;
  /**
   * Integer amount of smithore production.
   */
  private int smithoreProduction;

  /**
  * Constructor for a MapTile. Adds the appropriate images and sets
  * food, energy, and smithore production on it.
  *
  * @param food foodProduction
  * @param energy energyProduction
  * @param smith smithoreProduction
  * */
  MapTile(final int food, final int energy, final int smith) {
    foodProduction = food;
    energyProduction = energy;
    smithoreProduction = smith;
    imagePath = new HashMap<String, String>();
    imagePath.put("P", "/View/Resources/TilePlain.png");
    imagePath.put("R", "/View/Resources/TileRiver.png");
    imagePath.put("M1", "/View/Resources/TileMountain1.png");
    imagePath.put("M2", "/View/Resources/TileMountain2.png");
    imagePath.put("M3", "/View/Resources/TileMountain3.png");
    imagePath.put("Town", "/View/Resources/TileTown.png");
    imagePath.put("F", "/View/Resources/TileFlowers.png");
  }

  /**
  * Gets food production of the tile.
  *
  * @return int
  */
  public int getFoodProduction() {
    return foodProduction;
  }

  /**
  * Gets energy production of the tile.
  *
  * @return int
  */
  public int getEnergyProduction() {
    return energyProduction;
  }

  /**
  * Gets smithore production of the tile.
  *
  * @return int
  */
  public int getSmithoreProduction() {
    return smithoreProduction;
  }

  /**
  * Gets image path of the tile.
  *
  * @return String
  */
  public String imagePath() {
    return imagePath.getOrDefault(this.name(), imagePath.get("P"));
  }
}
