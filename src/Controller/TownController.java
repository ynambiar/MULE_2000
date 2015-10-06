package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;


public class TownController {

    @FXML
    private Button leaveTownBtn;
    @FXML
    private Label pubLabel;

    @FXML
    private void buttonHandler(MouseEvent event) {
        Button source = (Button) event.getSource();
        if (source == leaveTownBtn) {
            MasterController.getInstance().loadMapScene();
        }
    }

    @FXML
    private void townHandler(MouseEvent event) {
        Label source = (Label) event.getSource();
        if (source == pubLabel) {
            MasterController.getInstance().loadPubScene();
        }
    }

    @FXML
    private void setStoreBtn() {
        MasterController.getInstance().loadStoreScene();
    }
}
