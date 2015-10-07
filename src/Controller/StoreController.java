package Controller;

import Model.Game;
import Model.Main;
import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

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



    @FXML
    private void setBuyFood() {
//        titleLbl.setText("Food Transaction Screen");
//        MasterController.getInstance().loadStoreTransactionScene();
        Main.myGame.doStoreTransaction("Food", true, 1);
    }

    @FXML
    private void setBuyEnergy() {
//        titleLbl.setText("Energy Transaction Screen");
//        MasterController.getInstance().loadStoreTransactionScene();
        Main.myGame.doStoreTransaction("Energy", true, 1);
    }

    @FXML
    private void setBuySmithore() {
//        titleLbl.setText("Smithore Transaction Screen");
//        MasterController.getInstance().loadStoreTransactionScene();
        Main.myGame.doStoreTransaction("Smithore", true, 1);
    }

    @FXML
    private void setBuyMule() {
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
