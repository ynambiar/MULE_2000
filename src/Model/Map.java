package Model;

import java.io.Serializable;
import java.util.Random;

public class Map implements Serializable {

    private MapTile[][] board;
    private boolean[][] tilesOwned;
    private boolean[][] hasMule;


    public enum MapType {
        RANDOM, STANDARD
    }

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    /**
     * Constructs Map based on map type.
     */
    public Map() {
        //this(Main.myGame.getMapType());
        this(MapType.STANDARD);
        //TODO implement this such that the Map is created based on MapType
    }

    /**
     * Generate map of the game.
     * Sets tiles based on random or standard map.
     * @param type
     */
    public Map(MapType type) {
        final MapTile[][] standardMap = {
                { MapTile.P, MapTile.P, MapTile.M1, MapTile.P, MapTile.R,
                        MapTile.P, MapTile.M3, MapTile.P, MapTile.P },
                { MapTile.P, MapTile.M1, MapTile.P, MapTile.P, MapTile.R,
                        MapTile.P, MapTile.P, MapTile.P, MapTile.M3 },
                { MapTile.M3, MapTile.P, MapTile.P, MapTile.P, MapTile.Town,
                        MapTile.P, MapTile.P, MapTile.P, MapTile.M1 },
                { MapTile.P, MapTile.M2, MapTile.P, MapTile.P, MapTile.R,
                        MapTile.P, MapTile.M2, MapTile.P, MapTile.P },
                { MapTile.P, MapTile.P, MapTile.M2, MapTile.P, MapTile.R,
                        MapTile.P, MapTile.P, MapTile.P, MapTile.M2 } };
        int height = 5;
        int width = 9;
        if (type == MapType.STANDARD) {
            board = standardMap;
        } else {
            board = new MapTile[5][9];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    int r = new Random().nextInt(40);
                    if (r <= 29) {
                        board[i][j] = MapTile.P;
                    } else if (r <= 30) {
                        board[i][j] = MapTile.F;
                    } else if (r <= 33) {
                        board[i][j] = MapTile.M1;
                    } else if (r <= 36) {
                        board[i][j] = MapTile.M2;
                    } else {
                        board[i][j] = MapTile.M3;
                    }
                }
            }
            board[0][4] = MapTile.R;
            board[1][4] = MapTile.R;
            board[2][4] = MapTile.Town;
            board[3][4] = MapTile.R;
            board[4][4] = MapTile.R;
        }
        tilesOwned = new boolean[height][width];
        hasMule = new boolean[5][9];
        tilesOwned[2][4] = true;
        hasMule[2][4] = true;
    }

    /**
     * Returns if the tile at [row][col] is owned.
     * @param row
     * @param col
     * @return
     */
    public boolean tileUnowned(int row, int col) {
        return !tilesOwned[row][col];
    }

    /**
     * Sets if the tile at [row][col] is owned based on boolean b.
     * @param row
     * @param col
     * @param b
     */
    public void setTileOwned(int row, int col, boolean b) {
        tilesOwned[row][col] = b;
    }


    /**
     * Returns if the tile at [row][col] has a mule on it.
     * @param row
     * @param col
     * @return
     */
    public boolean tileHasMule(int row, int col) {
        return hasMule[row][col];
    }

    /**
     * Returns the 2D array of the board.
     * @return
     */
    public MapTile[][] getBoard() {
        return board;
    }

    /**
     * Gets tile at location [x][y].
     * @param x
     * @param y
     * @return
     */
    public MapTile getTile(int x, int y) {
        return board[x][y];
    }

    /**
     * Sets tile to the give tile, newTile, at location
     * [x][y].
     * @param x
     * @param y
     * @param newTile
     */
    public void setTile(int x, int y, MapTile newTile) {
        this.board[x][y] = newTile;
    }


}
