package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by William on 10/7/2015.
 */
public class GambleController {

    @FXML
    private Label resultsLabel;

    @FXML
    public void setOkBtn() {
        Main.myGame.endTurn();
    }

    public void initialize() {
        resultsLabel.setText(Integer.toString(Main.myGame.getGamble()));
    }
}
