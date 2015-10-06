package Controller;

import Model.Game;
import Model.Main;
import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by tuckerlocicero on 10/6/15.
 */
public class StoreController {

    @FXML
    private Button buyFood, buyEnergy, buySmithore, buyMule, leaveStore;

    @FXML
    private void setLeaveStore() {
        MasterController.getInstance().loadTownScene();
    }

    @FXML
    private void setBuyFood() {
        Main.myGame.doStoreTransaction("Food");
    }

    @FXML
    private void setBuyEnergy() {
        Main.myGame.doStoreTransaction("Energy");
    }

    @FXML
    private void setBuySmithore() {
        Main.myGame.doStoreTransaction("Smithore");
    }

    @FXML
    private void setBuyMule() {
    }

}
