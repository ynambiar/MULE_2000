package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Created by William on 10/6/2015.
 */
public class StartTurnController {

    @FXML
    private Label startTurnLabel;
    @FXML
    private Button startBtn;

    public void initialize() {
        if (Main.myGame.getCurrentPlayer() != null) {

            System.out.println("start screen");
            System.out.println("player: " + Main.myGame.getCurrentPlayer());

            startTurnLabel.setText("Round " + Main.myGame.getRoundNumber() + ": " + Main.myGame.getCurrentPlayer().getName() + "'s turn!");
        }
    }

    @FXML
    public void setStartBtn(MouseEvent event) {
        MasterController.getInstance().loadMapScene();
    }
}
