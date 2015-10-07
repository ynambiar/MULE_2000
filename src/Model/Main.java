package Model;

import Controller.MasterController;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Created by tuckerlocicero on 10/2/15.
 */
public class Main extends Application {

    public static Game myGame;

    public void start(Stage stage) {
        myGame = new Game(); //TODO Fill in all the fields in Game, make a default ctor
        MasterController controller = MasterController.getInstance();
        controller.setStage(stage);
        controller.loadStartScene();
        stage.setTitle("M.U.L.E. - Deep Thought");
        stage.setResizable(false);

        stage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}