package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Created by William on 10/20/2015.
 */
public class EventScreenController {

    @FXML
    Label eventLabel;

    @FXML
    public void setOkBtn(MouseEvent event) {
        Main.myGame.finishEvent();
    }

    public void initialize() {
        eventLabel.setText(Main.myGame.getCurrentEvent().toString());
    }
}
