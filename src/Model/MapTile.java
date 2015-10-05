package Model;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by yamininambiar on 10/3/15.
 */
public enum MapTile {
    P, R, M1, M2, M3, Town;


    private Map<String, String> imagePath;

    MapTile() {
        imagePath = new HashMap<String, String>();
        imagePath.put("P", "/View/Resources/TilePlain.png");
        imagePath.put("R", "/View/Resources/TileRiver.png");
        imagePath.put("M1", "/View/Resources/TileMountain1.png");
        imagePath.put("M2", "/View/Resources/TileMountain2.png");
        imagePath.put("M3", "/View/Resources/TileMountain3.png");
        imagePath.put("Town", "/View/Resources/TileTown.png");
    }

    public String imagePath() {
        return imagePath.getOrDefault(this.name(), imagePath.get("Plains"));
    }
}
