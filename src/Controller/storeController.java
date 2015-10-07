package Controller;

import Model.Game;
import Model.Main;
import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;

/**
 * Created by tuckerlocicero on 10/6/15.
 */
public class StoreController {

    @FXML
    private Button buyFood, buyEnergy, buySmithore, buyMule, leaveStore, confirmBtn;

    @FXML
    private Label titleLbl;

    @FXML
    private TextField buyTxt, sellTxt;

    private void chaChing() {
        final URL resource = getClass().getResource("/View/Resources/money.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @FXML
    private void setBuyFood() {
//        titleLbl.setText("Food Transaction Screen");
//        MasterController.getInstance().loadStoreTransactionScene();
        chaChing();
        Main.myGame.doStoreTransaction("Food", true, 1);

    }

    @FXML
    private void setBuyEnergy() {
//        titleLbl.setText("Energy Transaction Screen");
//        MasterController.getInstance().loadStoreTransactionScene();
        chaChing();
        Main.myGame.doStoreTransaction("Energy", true, 1);
    }

    @FXML
    private void setBuySmithore() {
//        titleLbl.setText("Smithore Transaction Screen");
//        MasterController.getInstance().loadStoreTransactionScene();
        chaChing();
        Main.myGame.doStoreTransaction("Smithore", true, 1);
    }

    @FXML
    private void setBuyMule() {
        chaChing();
        Main.myGame.doStoreTransaction("Mule", true, 1);
    }

    @FXML
    private void setConfirmBtn() {

    }

    @FXML
    private void setLeaveStore() {
        MasterController.getInstance().loadTownScene();
    }
}
