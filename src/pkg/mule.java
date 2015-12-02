package pkg;
import Model.*;
/**
 * MULE Game
 * By: Ashika, Yamini, William, Tucker, and Saqlain
 *
 */
import Controller.MasterController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.Serializable;
/**
 * Main Class.
 */

public class mule extends Application {

    /**
     * One instance of game to be used throughout the program.
     **/
    public static Game myGame = new Game();

    /**
     * Required start() method for extension of Application class. Sets the Stage
     * and loads the Start Scene.
     *
     * @param stage Stage
     */
    public final void start(final Stage stage) {
        MasterController controller = MasterController.getInstance();
        controller.setStage(stage);
        controller.loadStartScene();
        stage.setTitle("M.U.L.E. - Deep Thought");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Launches the program.
     *
     * @param args String[]
     */
    public static void main(final String[] args) {
        System.out.println("Welcome");
        launch(args);
    }

    /**
     * Sets the game.
     *
     * @param game Game
     */
    public static void setMyGame(final Game game) {
        myGame = game;
    }
}
