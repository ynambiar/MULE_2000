package Model;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by yamininambiar on 10/3/15.
 */
public class Map implements Serializable {

    private int height, width;
    private MapTile[][] board;
    private boolean[][] tilesOwned;
    private boolean[][] hasMule;
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
        this(MapType.STANDARD);
    }

    public Map(MapType type) {
        this.height = 5;
        this.width = 9;
        if (type == MapType.STANDARD) {
            board = standardMap;
        } else {
            board = new MapTile[5][9];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    int r = new Random().nextInt(40);
                    if (r <= 30) {
                        board[i][j] = MapTile.P;
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

    public boolean tileUnowned(int row, int col) {
        return !tilesOwned[row][col];
    }

    public void setTileOwned(int row, int col, boolean b) { tilesOwned[row][col] = b;}

    public boolean tileHasMule(int row, int col) { return hasMule[row][col];}

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
