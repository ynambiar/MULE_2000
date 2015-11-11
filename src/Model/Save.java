package Model;

import Controller.MasterController;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by SaqlainGolandaz on 11/3/15.
 */
public class Save implements Serializable {

    private File f1 = new File("Player1.ser");
    private File f2 = new File("Player2.ser");
    private File f3 = new File("Player3.ser");
    private File f4 = new File("Player4.ser");
    private File m1 = new File("MapSave.ser");
    private File s1 = new File("StoreSave.ser");
    private File g1 = new File("GameSave.ser");
    private File main1 = new File("MainSave.ser");

    /**
     * Saves data.
     *
     * @param p
     * @throws IOException
     */
    public void saveData(ArrayList<Player> p, Map map, Store store, Game game) throws IOException {

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
     * @throws IOException
     */
    public void loadData() throws IOException {

        Map z = null;
        Store y = null;
        Game w = null;

        try {
            FileInputStream fileInMap = new FileInputStream(m1);
            ObjectInputStream inMap = new ObjectInputStream(fileInMap);
            z = (Map) inMap.readObject();
            inMap.read();
            inMap.close();
            fileInMap.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Loading map doesn't work. shit");
        } catch (IOException x) {
            System.out.println("Loading map doesn't work b/c map doesn't exist?");
        }

        try {
            FileInputStream fileInStore = new FileInputStream(s1);
            ObjectInputStream inStore = new ObjectInputStream(fileInStore);
            y = (Store) inStore.readObject();
            inStore.read();
            inStore.close();
            fileInStore.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Loading store doesn't work. shit");
        } catch (IOException x) {
            System.out.println("Loading store doesn't work b/c store doesn't exist?");
        }

        try {
            FileInputStream fileInGame = new FileInputStream(g1);
            ObjectInputStream inGame = new ObjectInputStream(fileInGame);
            w = (Game) inGame.readObject();
            inGame.read();
            inGame.close();
            fileInGame.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Loading game doesn't work. shit");
        } catch (IOException x) {
            System.out.println("Loading game doesn't work b/c game doesn't exist?");
        }

        Main.setMyGame(w);
        w.setStore(y);
        w.setMap(z);
        MasterController.getInstance().getMapController().setMap(z);
        w.startTurn();
    }
}
