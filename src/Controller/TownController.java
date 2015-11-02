package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

import java.io.Serializable;


public class TownController implements Serializable {

    @FXML
    private void setLeaveTownBtn() {
        Main.myGame.setPhase("Regular Turn");
        Main.myGame.refreshLabels();
        MasterController.getInstance().loadMapScene();
    }
    @FXML
    private void setPubBtn() { MasterController.getInstance().loadPubScene();}
    @FXML
    private void setStoreBtn() {
        MasterController.getInstance().loadStoreScene();
    }
    @FXML
    private void setLandBtn() { MasterController.getInstance().loadLandOfficeScene();}
}
