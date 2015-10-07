package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

/**
 * Created by tuckerlocicero on 10/6/15.
 */
public class StoreController {

    @FXML
    private Button buyFood, buyEnergy, buySmithore, buyMule, leaveStore, confirmBtnFood, foodMuleBtn, energyMuleBtn, smithoreMuleBtn
            , confirmMuleSell, confirmMuleBuy, cancelBtn;

    @FXML
    private Label titleLbl;

    @FXML
    private Rectangle foodMuleSelected, energyMuleSelected, smithoreMuleSelected;

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

    private String selectedMule;

    @FXML
    private void setFoodMuleBtn() {
        foodMuleSelected.setVisible(true);
        energyMuleSelected.setVisible(false);
        smithoreMuleSelected.setVisible(false);
        selectedMule = "Food Mule";
    }

    @FXML
    private void setEnergyMuleBtn() {
        foodMuleSelected.setVisible(false);
        energyMuleSelected.setVisible(true);
        smithoreMuleSelected.setVisible(false);
        selectedMule = "Energy Mule";
    }

    @FXML
    private void setSmithoreMuleBtn() {
        foodMuleSelected.setVisible(false);
        energyMuleSelected.setVisible(false);
        smithoreMuleSelected.setVisible(true);
        selectedMule = "Smithore Mule";
    }

    /**
     * TODO go to map and let the player choose wehere to place their mule
     */
    @FXML
    private void setConfirmMuleBuy() {

    }

    @FXML
    private void setConfirmMuleSell() {
        Main.myGame.getCurrentPlayer().sellThatMule(selectedMule);
    }

    @FXML
    private void setCancelBtn() {
        MasterController.getInstance().loadStoreScene();
    }


    @FXML
    private void setLeaveStore() {
        MasterController.getInstance().loadTownScene();
    }
}
