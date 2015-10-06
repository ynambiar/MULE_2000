package Model;

/**
 * Created by yamininambiar on 10/3/15.
 */
public class Map {

    private int height, width;
    private MapTile[][] board;
    private boolean[][] tilesOwned;

    private final MapTile[][] standardMap =
            {{MapTile.P,  MapTile.P,  MapTile.M1, MapTile.P,  MapTile.R,    MapTile.P,  MapTile.M3, MapTile.P,  MapTile.P},
                    {MapTile.P,  MapTile.M1, MapTile.P,  MapTile.P,  MapTile.R,    MapTile.P,  MapTile.P,  MapTile.P,  MapTile.M3},
                    {MapTile.M3, MapTile.P,  MapTile.P,  MapTile.P,  MapTile.Town, MapTile.P,  MapTile.P,  MapTile.P,  MapTile.M1},
                    {MapTile.P,  MapTile.M2, MapTile.P,  MapTile.P,  MapTile.R,    MapTile.P,  MapTile.M2, MapTile.P,  MapTile.P},
                    {MapTile.P,  MapTile.P,  MapTile.M2, MapTile.P,  MapTile.R,    MapTile.P,  MapTile.P,  MapTile.P,  MapTile.M2}};

    public enum MapType {
        RANDOM, STANDARD;
    }

    public enum Difficulty {
        EASY, MEDIUM, HARD;
    }

    private MapType mapType;

    public Map() {
        this(5, 9, MapType.STANDARD);
    }

    public Map(int height, int width, MapType type) {
        this.height = height;
        this.width = width;
        if (type == MapType.STANDARD) {
            board = standardMap;
        } else {
            board = new MapTile[height][width];
        }
        tilesOwned = new boolean[height][width];
        tilesOwned[2][4] = true;
    }

    public boolean purchase(int row, int col) {
        System.out.println("Map purchase");
        if (!tilesOwned[row][col]) {
            System.out.println("Map tile not owned");
            if (Main.myGame.addProperty(row, col)) {
                System.out.println("Map add property is true");
                tilesOwned[row][col] = true;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public MapTile[][] getBoard() {
        return board;
    }

    public MapTile getTile(int x, int y) {
        return board[x][y];
    }

    public void setTile(int x, int y, MapTile newTile) {
        this.board[x][y] = newTile;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }



}
