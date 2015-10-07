package Controller;

import Model.Game;
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
        Game game = Main.myGame;
        if (game.getCurrentPlayer() != null) {
            if (game.getRoundNumber() > 0) {
                startTurnLabel.setText("Round " + game.getRoundNumber() + ": " + game.getCurrentPlayer().getName() + "'s turn!");
            } else {
                startTurnLabel.setText("Land Selection Phase: " + game.getCurrentPlayer().getName() + "'s turn!");
            }
        }
    }

    @FXML
    public void setStartBtn(MouseEvent event) {
        MasterController.getInstance().loadMapScene();
        Main.myGame.startTurn();
    }
}
