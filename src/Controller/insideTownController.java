package game;

import Controller.MasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by tuckerlocicero on 9/24/15.
 */
public class insideTownController {

    @FXML
    private Button leaveTownBtn;
    @FXML
    private Button pubBtn;

    @FXML
    private void buttonHandler(ActionEvent event) {
        Button source = (Button) event.getSource();
        if (source == leaveTownBtn) {
            MasterController.getInstance().loadMapScene();
        } else if (source == pubBtn) {
            MasterController.getInstance().loadPubScene();
        }
    }



}
