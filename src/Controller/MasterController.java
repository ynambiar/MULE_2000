package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yamininambiar on 10/3/15.
 */
public class MasterController {


    private ArrayList<String> scenes = new ArrayList<String>();
    private Stage stage;
    private Stage currentStage;
//    private Scene currentScene;
    private Scene config1Scene, config2Scene, gamblingResultsScene, placeHolderScreen, insidePubScene,
        insideStoreScene, storeTransactionSceneFood, storeTransactionSceneEnergy, storeTransactionSceneSmithore,
        insideTownScene, rulesScene, mapScene, startScene, startTurnScene, insideLandOfficeScene;
    private FXMLLoader loader;
    private MapController mapController;

    //create a singleton
    private static MasterController masterController = new MasterController();

    //returns the single instance of MasterController within the entire scope of the project
    public static MasterController getInstance() { return masterController; }

    //ctor! loads all the scene upon initialization
    //TODO Ask Yamini if we can kill this ctor
    public MasterController() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/Config1.fxml"));
            config1Scene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/Config2.fxml"));
            config2Scene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/LandOffice.fxml"));
            insideLandOfficeScene = new Scene(root);

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

            root = FXMLLoader.load(getClass().getResource("/View/Town.fxml"));
            insideTownScene = new Scene(root);

            loader = new FXMLLoader(getClass().getResource("/View/Map.fxml"));
            root = (Parent) loader.load();
            mapScene = new Scene(root);
            mapController = (MapController) loader.getController();
            System.out.println("master controller" + mapController);

            root = FXMLLoader.load(getClass().getResource("/View/GamblingResults.fxml"));
            gamblingResultsScene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/RulesScreen.fxml"));
            rulesScene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/StartTurn.fxml"));
            startTurnScene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/Start.fxml"));
            startScene = new Scene(root);

        } catch (IOException e) {
            System.out.println("shit's broke");
            System.out.println("MasterController(): " + e);
        }

    }


    //methods to allow other Controller classes to change scenes remotely
    public void loadConfig1Scene()  {
        stage.setScene(config1Scene);
    }
    public void loadConfig2Scene() {
        stage.setScene(config2Scene);
    }
    public void loadPubScene() { stage.setScene(insidePubScene);}
    public void loadStoreScene() {stage.setScene(insideStoreScene);}
    public void loadLandOfficeScene() {stage.setScene(insideLandOfficeScene);}
    public void loadStoreTransactionSceneFood() {stage.setScene(storeTransactionSceneFood);}
    public void loadStoreTransactionSceneEnergy() {stage.setScene(storeTransactionSceneEnergy);}
    public void loadStoreTransactionSceneSmithore() {stage.setScene(storeTransactionSceneSmithore);}

    public void loadTownScene() { stage.setScene(insideTownScene);}
    public void loadMapScene() { stage.setScene(mapScene);}
    public void loadPlaceHolderScene() {stage.setScene(placeHolderScreen);}
    public void loadRulesScene() { stage.setScene(rulesScene);}
    public void loadStartScene() { stage.setScene(startScene);}
    public void loadStartTurnScene() {
        try{
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/StartTurn.fxml"))));
        } catch (IOException e) {
        }
    }
    public void loadGamblingResultsScene() { try{
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/GamblingResults.fxml"))));
    } catch (IOException e) {
    }}

    public MapController getMapController() { return mapController;}

    //called once
    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
