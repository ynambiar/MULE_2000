package main.java.com.mule.Model;

import main.java.com.mule.Controller.MasterController;
import main.java.com.mule.Main;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
* Save class.
*/

class Save implements Serializable {

  /**
   * Save map file.
   **/
  private final File m1 = new File("MapSave.ser");
  /**
   * Save store file.
   **/
  private final File s1 = new File("StoreSave.ser");
  /**
   * Save game file.
   **/
  private final File g1 = new File("GameSave.ser");

  /**
   * Saves data.
   *
   * @param map Map
   * @param store Store
   * @param game Game
   */
  public final void saveData(final Map map,
      final Store store, final Game game) {

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
   */
  public final void loadData() {

    Map mapval = null;
    Store storeval = null;
    Game gameval = null;

    try {
      FileInputStream fileInMap = new FileInputStream(m1);
      ObjectInputStream inMap = new ObjectInputStream(fileInMap);
      mapval = (Map) inMap.readObject();
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
      inGame.close();
      fileInGame.close();
    } catch (ClassNotFoundException e) {
      System.out.println("Loading game doesn't work.");
    } catch (IOException x) {
      System.out.println("Loading game doesn't work b/c game doesn't exist?");
    }
    if (null != gameval) {
      Main.setMyGame(gameval);
      if (null != storeval) {
        gameval.setStore(storeval);
      }
      if (null != mapval) {
        gameval.setMap(mapval);
      }
      MasterController.getInstance().getMapController().setMap(mapval);
      gameval.startTurn();

    }
  }
}
