package Model;

import Controller.MasterController;
import javafx.application.Application;
import javafx.stage.Stage;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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

        //Code for the music
        final URL resource = getClass().getResource("/View/Resources/music.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        stage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}