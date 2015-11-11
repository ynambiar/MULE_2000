package Model;

import java.io.Serializable;
import java.util.Random;
/**
* Map Class.
*/

public class Map implements Serializable {
  //NEED JAVADOCS FOR THESE VARIABLES
  /**
   * 2D Array of bord of MapTiles.
   */
  private final MapTile[][] board;
  /**
   * 2D Array of ownership status.
   */
  private final boolean[][] tilesOwned;
  /**
   * 2D Array of tiles that have mules.
   */
  private final boolean[][] hasMule;

  /**
   * Enum class for MapType.
   */

  public enum MapType {
    RANDOM, STANDARD
  }

  /**
   * Enum class for Difficulty.
   */

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
  * @param type MapType
  */
  public Map(final MapType type) {
    MapTile[][] standardMap = {
            {MapTile.P, MapTile.P, MapTile.M1, MapTile.P, MapTile.R,
                    MapTile.P, MapTile.M3, MapTile.P, MapTile.P},
            {MapTile.P, MapTile.M1, MapTile.P, MapTile.P, MapTile.R,
                    MapTile.P, MapTile.P, MapTile.P, MapTile.M3},
            {MapTile.M3, MapTile.P, MapTile.P, MapTile.P, MapTile.Town,
                    MapTile.P, MapTile.P, MapTile.P, MapTile.M1},
            {MapTile.P, MapTile.M2, MapTile.P, MapTile.P, MapTile.R,
                    MapTile.P, MapTile.M2, MapTile.P, MapTile.P},
            {MapTile.P, MapTile.P, MapTile.M2, MapTile.P, MapTile.R,
                    MapTile.P, MapTile.P, MapTile.P, MapTile.M2}};
    int height = 5;
    int width = 9;
    if (type == MapType.STANDARD) {
      board = standardMap;
    } else {
      board = new MapTile[5][9];
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 9; j++) {
          int rand = new Random().nextInt(40);
          if (rand <= 29) {
            board[i][j] = MapTile.P;
          } else if (rand <= 30) {
            board[i][j] = MapTile.F;
          } else if (rand <= 33) {
            board[i][j] = MapTile.M1;
          } else if (rand <= 36) {
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
  * @param row Row
  * @param col Col
  * @return boolean
  */
  public final boolean tileUnowned(final int row, final int col) {
    return !tilesOwned[row][col];
  }

  /**
  * Sets if the tile at [row][col] is owned based on boolean b.
  * @param row Row
  * @param col Col
  * @param bool Boolean
  */
  public final void setTileOwned(final int row, final int col,
      final boolean bool) {
    tilesOwned[row][col] = bool;
  }


  /**
  * Returns if the tile at [row][col] has a mule on it.
  * @param row Row
  * @param col Col
  * @return boolean
  */
  public final boolean tileHasMule(final int row, final int col) {
    return hasMule[row][col];
  }

  /**
  * Returns the 2D array of the board.
  * @return MapTile[][]
  */
  public final MapTile[][] getBoard() {
    return board;
  }

  /**
  * Gets tile at location [x][y].
  * @param xloc X
  * @param yloc Y
  * @return MapTile
  */
  public final MapTile getTile(final int xloc, final int yloc) {
    return board[xloc][yloc];
  }

  /**
  * Sets tile to the give tile, newTile, at location
  * [x][y].
  * @param xloc X
  * @param yloc Y
  * @param newTile MapTile
  */
  public final void setTile(final int xloc, final int yloc,
      final MapTile newTile) {
    this.board[xloc][yloc] = newTile;
  }


}
