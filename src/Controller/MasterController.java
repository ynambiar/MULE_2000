package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class MasterController implements Serializable {

    private Stage stage;
    private Scene config1Scene, config2Scene, insidePubScene,
            insideStoreScene, storeTransactionSceneFood, storeTransactionSceneEnergy, storeTransactionSceneSmithore,
            insideTownScene, rulesScene, mapScene, startScene, muleTransactionScene, loadGameScreen;
    private MapController mapController;

    //create a singleton
    private static MasterController masterController = new MasterController();

    /**
     * Returns the single instance of MasterController within the entire scope of the project.
     * @return
     */
    public static MasterController getInstance() {
        return masterController;
    }

    /**
     * Constructor for Master Controller. Loads all the scenes.
     */
    private MasterController() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/Config1.fxml"));
            config1Scene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/Config2.fxml"));
            config2Scene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/Pub.fxml"));
            insidePubScene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/Store.fxml"));
            insideStoreScene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/StoreTransactionScreenFood.fxml"));
            storeTransactionSceneFood = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/StoreTransactionScreenEnergy.fxml"));
            storeTransactionSceneEnergy = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/StoreTransactionScreenSmithore.fxml"));
            storeTransactionSceneSmithore = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/MuleTransactionScreen.fxml"));
            muleTransactionScene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/Town.fxml"));
            insideTownScene = new Scene(root);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Map.fxml"));
            root = (Parent) loader.load();
            mapScene = new Scene(root);
            mapController = (MapController) loader.getController();
            System.out.println("master controller" + mapController);

            root = FXMLLoader.load(getClass().getResource("/View/RulesScreen.fxml"));
            rulesScene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/loadGameScreen.fxml"));
            loadGameScreen = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/Start.fxml"));
            startScene = new Scene(root);

        } catch (IOException e) {
            System.out.println("MasterController(): " + e);
        }

    }

    /**
     * Load Config1 scene.
     */
    public void loadConfig1Scene() {
        stage.setScene(config1Scene);
    }

    /**
     * Load Config 2 scene.
     */
    public void loadConfig2Scene() {
        stage.setScene(config2Scene);
    }

    /**
     *  Load Pub scene.
     */
    public void loadPubScene() {
        stage.setScene(insidePubScene);
    }

    /**
     * Load Store scene.
     */
    public void loadStoreScene() {
        stage.setScene(insideStoreScene);
    }

    /**
     * Load food Store Transaction Scene.
     */
    public void loadStoreTransactionSceneFood() {
        stage.setScene(storeTransactionSceneFood);
    }

    /**
     * Load energy Store Transaction Scene.
     */
    public void loadStoreTransactionSceneEnergy() {
        stage.setScene(storeTransactionSceneEnergy);
    }

    /**
     * Load smithore Store Transaction Scene.
     */
    public void loadStoreTransactionSceneSmithore() {
        stage.setScene(storeTransactionSceneSmithore);
    }

    /**
     * Load mule Transaction Scene.
     */
    public void loadMuleTransactionScene() {
        stage.setScene(muleTransactionScene);
    }

    /**
     * Load town Scene.
     */
    public void loadTownScene() {
        stage.setScene(insideTownScene);
    }

    /**
     * Load Map Scene.
     */
    public void loadMapScene() {
        stage.setScene(mapScene);
    }

    /**
     * Load Rules Scene.
     */
    public void loadRulesScene() {
        stage.setScene(rulesScene);
    }

    /**
     * Load Start Scene.
     */
    public void loadStartScene() {
        stage.setScene(startScene);
    }

    /**
     * Load Load Screen.
     */
    public void loadLoadScreenScene() {
        stage.setScene(loadGameScreen);
    }

    /**
     * Load Event Scene.
     */
    public void loadEventScene() {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/EventScreen.fxml"))));
        } catch (IOException e) {
        }
    }

    /**
     * Load Land Office Scene.
     */
    public void loadLandOfficeScene() {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/LandOffice.fxml"))));
        } catch (IOException e) {
        }
    }

    /**
     * Load Start Turn Scene.
     */
    public void loadStartTurnScene() {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/StartTurn.fxml"))));
        } catch (IOException e) {
        }
    }

    /**
     * Load Gambling Results Scene.
     */
    public void loadGamblingResultsScene() {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/GamblingResults.fxml"))));
        } catch (IOException e) {
        }
    }

    /**
     * Return MapController.
     * @return
     */
    public MapController getMapController() {
        return mapController;
    }

    /**
     * Sets stage to given Stage.
      * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }


}