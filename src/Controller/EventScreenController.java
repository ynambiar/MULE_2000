package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public class EventScreenController implements Serializable {

    @FXML
    Label eventLabel;

    @FXML
    /**
     * Sets OK button action.
     */
    public void setOkBtn(MouseEvent event) {
        Main.myGame.finishEvent();
    }


    /**
     * Initialize Event screen.
     */
    public void initialize() {
        eventLabel.setText(Main.myGame.getCurrentEvent().toString());
    }
}
