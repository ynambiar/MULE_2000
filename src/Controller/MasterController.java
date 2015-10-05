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
        insideTownScene, rulesScene, mapScene, startScene;


    //create a singleton
    private static MasterController masterController = new MasterController();

    //returns the single instance of MasterController within the entire scope of the project
    public static MasterController getInstance() { return masterController; }

    //ctor! loads all the scene upon initialization
    public MasterController() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/Config1.fxml"));
            config1Scene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/Config2.fxml"));
            config2Scene = new Scene(root);

//            root = FXMLLoader.load(getClass().getResource("/View/InsidePub.fxml"));
//            insidePubScene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/InsideTown.fxml"));
            insideTownScene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/Map.fxml"));
            mapScene = new Scene(root);

//            root = FXMLLoader.load(getClass().getResource("/View/RulesScreen.fxml"));
//            rulesScene = new Scene(root);

            root = FXMLLoader.load(getClass().getResource("/View/Start.fxml"));
            startScene = new Scene(root);

        } catch (IOException e) {
            System.out.println("MasterController(): " + e);
        }

    }


    //methods to allow other Controller classes to change scenes remotely
    public void loadConfig1Scene() {
        stage.setScene(config1Scene);
    }
    public void loadConfig2Scene() {
        stage.setScene(config2Scene);
    }
    public void loadPubScene() { stage.setScene(insidePubScene);}
    public void loadTownScene() { stage.setScene(insideTownScene);}
    public void loadMapScene() { stage.setScene(mapScene);}
    public void loadPlaceHolderScene() {stage.setScene(placeHolderScreen);}
    public void loadRulesScene() { stage.setScene(rulesScene);}
    public void loadStartScene() { stage.setScene(startScene);}


    //called once
    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
