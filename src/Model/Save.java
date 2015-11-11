package Model;

import Controller.MasterController;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
* Save class.
*/

public class Save implements Serializable {

  /**
   * Save map file.
   **/
  private File m1 = new File("MapSave.ser");
  /**
   * Save store file.
   **/
  private File s1 = new File("StoreSave.ser");
  /**
   * Save game file.
   **/
  private File g1 = new File("GameSave.ser");

  /**
   * Saves data.
   *
   * @param play ArrayList of players
   * @param map Map
   * @param store Store
   * @param game Game
   * @throws IOException Exception
   */
  public final void saveData(final ArrayList<Player> play, final Map map,
      final Store store, final Game game)
      throws IOException {

    try {
      FileOutputStream fileOutMap = new FileOutputStream(m1);
      ObjectOutputStream outMap = new ObjectOutputStream(fileOutMap);
      outMap.writeObject(map);
      outMap.close();
    } catch (IOException i) {
      System.out.println("saving map doesn't work");
    }

    try {
      FileOutputStream fileOutStore = new FileOutputStream(s1);
      ObjectOutputStream outStore = new ObjectOutputStream(fileOutStore);
      outStore.writeObject(store);
      outStore.close();
    } catch (IOException i) {
      System.out.println("saving store doesn't work");
    }

    try {
      FileOutputStream fileOutGame = new FileOutputStream(g1);
      ObjectOutputStream outGame = new ObjectOutputStream(fileOutGame);
      outGame.writeObject(game);
      outGame.close();
    } catch (IOException i) {
      System.out.println("saving game doesn't work");
    }
  }

  /**
   * Loads data.
   *
   * @throws IOException Exception
   */
  public final void loadData() throws IOException {

    Map mapval = null;
    Store storeval = null;
    Game gameval = null;

    try {
      FileInputStream fileInMap = new FileInputStream(m1);
      ObjectInputStream inMap = new ObjectInputStream(fileInMap);
      mapval = (Map) inMap.readObject();
      inMap.read();
      inMap.close();
      fileInMap.close();
    } catch (ClassNotFoundException e) {
      System.out.println("Loading map doesn't work.");
    } catch (IOException x) {
      System.out.println("Loading map doesn't work b/c map doesn't exist?");
    }

    try {
      FileInputStream fileInStore = new FileInputStream(s1);
      ObjectInputStream inStore = new ObjectInputStream(fileInStore);
      storeval = (Store) inStore.readObject();
      inStore.read();
      inStore.close();
      fileInStore.close();
    } catch (ClassNotFoundException e) {
      System.out.println("Loading store doesn't work.");
    } catch (IOException x) {
      System.out
          .println("Loading store doesn't work b/c store doesn't exist?");
    }

    try {
      FileInputStream fileInGame = new FileInputStream(g1);
      ObjectInputStream inGame = new ObjectInputStream(fileInGame);
      gameval = (Game) inGame.readObject();
      inGame.read();
      inGame.close();
      fileInGame.close();
    } catch (ClassNotFoundException e) {
      System.out.println("Loading game doesn't work.");
    } catch (IOException x) {
      System.out.println("Loading game doesn't work b/c game doesn't exist?");
    }

    Main.setMyGame(gameval);
    gameval.setStore(storeval);
    gameval.setMap(mapval);
    MasterController.getInstance().getMapController().setMap(mapval);
    gameval.startTurn();
  }
}
