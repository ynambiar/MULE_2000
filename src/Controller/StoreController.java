package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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



    @FXML
    private void setBuyFood() {
        MasterController.getInstance().loadStoreTransactionSceneFood();
    }

    @FXML
    private void setBuyEnergy() {
        MasterController.getInstance().loadStoreTransactionSceneEnergy();
    }

    @FXML
    private void setBuySmithore() {
        MasterController.getInstance().loadStoreTransactionSceneSmithore();
    }

    @FXML
    private void setBuyMule() {
        Main.myGame.doStoreTransaction("Mule", true, 1);
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
}
