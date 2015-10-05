package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class insideTownController {

    @FXML
    private Button leaveTownBtn;
    @FXML
    private Button pubBtn;

    @FXML
    private void buttonHandler(MouseEvent event) {
        Button source = (Button) event.getSource();
        if (source == leaveTownBtn) {
            MasterController.getInstance().loadMapScene();
        } else if (source == pubBtn) {
            MasterController.getInstance().loadPubScene();
        }
    }
}
