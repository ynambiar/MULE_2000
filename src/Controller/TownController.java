package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;


public class TownController {

    @FXML
    private void setLeaveTownBtn() { MasterController.getInstance().loadMapScene();}
    @FXML
    private void setPubBtn() { MasterController.getInstance().loadPubScene();}
    @FXML
    private void setStoreBtn() {
        MasterController.getInstance().loadStoreScene();
    }
    @FXML
    private void setLandBtn() { MasterController.getInstance().loadLandOfficeScene();}
}
