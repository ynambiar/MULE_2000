package Model;

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

    /**
     * Saves data.
     *
     * @param p
     * @throws IOException
     */
    public void saveData(ArrayList<Player> p, Map map, Store store) throws IOException {

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
            FileOutputStream fileOut = new FileOutputStream(f1);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(p.get(0));
            out.writeObject("\n");
            out.close();
        } catch (IOException i) {
            System.out.println("save doesn't work");
        }

        if (p.size() > 1) {
            try {
                FileOutputStream fileOut = new FileOutputStream(f2);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(p.get(1));
                out.writeObject("\n");
                out.close();
            } catch (IOException i) {
                System.out.println("save doesn't work");
            }
        } else {
            FileOutputStream fileOut = new FileOutputStream(f2);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(null);
            out.close();
        }

        if (p.size() > 2) {
            try {
                FileOutputStream fileOut = new FileOutputStream(f3);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(p.get(2));
                out.writeObject("\n");
                out.close();
            } catch (IOException i) {
                System.out.println("save doesn't work");
            }
        } else {
            FileOutputStream fileOut = new FileOutputStream(f3);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(null);
            out.close();
        }

        if (p.size() > 3) {
            try {
                FileOutputStream fileOut = new FileOutputStream(f4);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(p.get(3));
                out.writeObject("\n");
                out.close();
            } catch (IOException i) {
                System.out.println("save doesn't work");
            }
        } else {
            FileOutputStream fileOut = new FileOutputStream(f4);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(null);
            out.close();
        }
    }

    /**
     * Loads data.
     *
     * @throws IOException
     */
    public void loadData() throws IOException {

        Player g = null;
        Player h = null;
        Player i = null;
        Player j = null;

        Map z = null;
        Store y = null;

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

        System.out.println(z.getBoard());


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

        System.out.println(y.getEnergyStock());

        try {
            FileInputStream fileIn = new FileInputStream(f1);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            g = (Player) in.readObject();
            in.read();
            in.close();
            fileIn.close();
        } catch (IOException x) {
            System.out.print("load doesn't work");
        } catch (ClassNotFoundException c) {
            System.out.print("load doesn't work");
        }

        System.out.println(g.getName());
        System.out.println(g.getNumTilesOwned());
        System.out.println(g.getColor());

        try {
            FileInputStream fileIn2 = new FileInputStream(f2);
            ObjectInputStream in2 = new ObjectInputStream(fileIn2);
            h = (Player) in2.readObject();
            in2.read();
            in2.close();
            fileIn2.close();
        } catch (IOException x) {
            System.out.print("load doesn't work");
        } catch (ClassNotFoundException c) {
            System.out.print("load doesn't work");
        }

        System.out.println(h.getName());
        System.out.println(h.getNumTilesOwned());
        System.out.println(h.getColor());

        try {
            FileInputStream fileIn3 = new FileInputStream(f3);
            ObjectInputStream in3 = new ObjectInputStream(fileIn3);
            i = (Player) in3.readObject();
            if (i == null) {
                return;
            }
            in3.read();
            in3.close();
            fileIn3.close();
        } catch (IOException x) {
            System.out.print("load doesn't work");
        } catch (ClassNotFoundException c) {
            System.out.print("load doesn't work");
        }

        System.out.println(i.getName());
        System.out.println(i.getNumTilesOwned());
        System.out.println(i.getColor());

        try {
            FileInputStream fileIn4 = new FileInputStream(f4);
            ObjectInputStream in4 = new ObjectInputStream(fileIn4);
            j = (Player) in4.readObject();
            if (j == null) {
                return;
            } else {
                in4.read();
                in4.close();
                fileIn4.close();
                System.out.println(j.getName());
                System.out.println(j.getNumTilesOwned());
                System.out.println(j.getColor());
            }
        } catch (IOException x) {
            System.out.print("load doesn't work");
        } catch (ClassNotFoundException c) {
            System.out.print("load doesn't work");
        }

    }
}
