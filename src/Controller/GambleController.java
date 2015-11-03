package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.Serializable;

/**
 * Created by William on 10/7/2015.
 */
public class GambleController implements Serializable {

    @FXML
    private Label resultsLabel;

    @FXML
    /**
     * Set OK button function so that turn ends.
     */
    public void setOkBtn() {
        Main.myGame.endTurn();
    }

    /**
     * Initialize Gamble controller.
     */
    public void initialize() {
        resultsLabel.setText(Integer.toString(Main.myGame.getGamble()));
    }
}
