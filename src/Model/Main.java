package Model;

import Controller.MasterController;
import com.sun.media.jfxmedia.track.AudioTrack;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import sun.audio.AudioStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioData;

import java.applet.AudioClip;
import java.io.FileInputStream;
import sun.audio.ContinuousAudioDataStream;
import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.net.URL;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Platform;
import sun.misc.IOUtils;
import java.awt.event.WindowEvent;
import java.io.File;
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
//        try {
//            startMusic();
//        } catch (Exception e) {
//            System.out.println("Error with music!");
//        }
    }
//    public void startMusic() throws Exception {
//        try {
//            Path path = Paths.get("/Users/AshikaGanesh/Documents/Final_Github/MULE_2000/src/View/Resources/music.wav");
    //  NEED TO FIND A WAY TO ACCESS THAT MUSIC FILE!!
    // also need a way to break and stop music if system quits.
//            String soundFile = path.toString();
//
//            InputStream in = new FileInputStream(soundFile);
//            AudioStream audioStream = new AudioStream(in);
//            AudioPlayer.player.start(audioStream);
//            } catch (Exception e) {
//            System.out.println(e);
//        }
//
//    }



    public static void main(String[] args) {
        launch(args);
    }
}