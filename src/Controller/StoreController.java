package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
/**
 * Created by tuckerlocicero on 10/6/15.
 */
public class StoreController {

    @FXML
    private Button buyFood, buyEnergy, buySmithore, buyMule, leaveStore, confirmBtnFood;

    @FXML
    private Label titleLbl;

    @FXML
    private TextField buyTxtFood, sellTxtFood, buyTxtEnergy, sellTxtEnergy, buyTxtSmithore, sellTxtSmithore;

    //sound code
    private void chaChing() {
        final URL resource = getClass().getResource("/View/Resources/money.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    //end of sound code here

    @FXML
    private void setBuyFood() {
        chaChing();
        MasterController.getInstance().loadStoreTransactionSceneFood();
    }

    @FXML
    private void setBuyEnergy() {
        chaChing();
        MasterController.getInstance().loadStoreTransactionSceneEnergy();
    }

    @FXML
    private void setBuySmithore() {
        chaChing();
        MasterController.getInstance().loadStoreTransactionSceneSmithore();
    }

    @FXML
    private void setBuyMule() {
        chaChing();
        MasterController.getInstance().loadMuleTransactionScene();
    }

    @FXML
    private void setConfirmBtnFood() {
        Main.myGame.doStoreTransaction("Food", true, Integer.parseInt(buyTxtFood.getText()));
        Main.myGame.doStoreTransaction("Food", true, -1 * Integer.parseInt(sellTxtFood.getText()));
        MasterController.getInstance().loadStoreScene();
    }
    @FXML
    private void setConfirmBtnEnergy() {
        Main.myGame.doStoreTransaction("Energy", true, Integer.parseInt(buyTxtEnergy.getText()));
        Main.myGame.doStoreTransaction("Energy", true, -1 * Integer.parseInt(sellTxtEnergy.getText()));
        MasterController.getInstance().loadStoreScene();
    }
    @FXML
    private void setConfirmBtnSmithore() {
        Main.myGame.doStoreTransaction("Energy", true, Integer.parseInt(buyTxtSmithore.getText()));
        Main.myGame.doStoreTransaction("Energy", true, -1 * Integer.parseInt(sellTxtSmithore.getText()));
        MasterController.getInstance().loadStoreScene();
    }

    @FXML
    private void setLeaveStore() {
        MasterController.getInstance().loadTownScene();
    }



    //  THIS PART OF CODE IS FOR ANIMATION:
    @FXML
    private ImageView foodAnim, energyAnim, muleAnim, cAnim, oreAnim;

    @FXML
    private void foodmouseEnter () {foodAnim.setVisible(true);}
    @FXML
    private void foodmouseLeave () { foodAnim.setVisible(false);}
    @FXML
    private void energymouseEnter () {
        energyAnim.setVisible(true);
    }
    @FXML
    private void energymouseLeave () {
        energyAnim.setVisible(false);
    }
    @FXML
    private void mulemouseEnter () {
        muleAnim.setVisible(true);
    }
    @FXML
    private void mulemouseLeave () {
        muleAnim.setVisible(false);
    }
    @FXML
    private void cmouseEnter () {
        cAnim.setVisible(true);
    }
    @FXML
    private void cmouseLeave () { cAnim.setVisible(false); }
    @FXML
    private void oremouseEnter () {
        oreAnim.setVisible(true);
    }
    @FXML
    private void oremouseLeave () {
        oreAnim.setVisible(false);
    }
//  END OF ANIMATION HERE

}
