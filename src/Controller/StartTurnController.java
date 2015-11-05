package Controller;

import model.Game;
import model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public class StartTurnController implements Serializable {

    @FXML
    private Label startTurnLabel;
    @FXML
    private Button startBtn;

    /**
     * Sets labels for the scene.
     */
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
    /**
     * Sets Start button function.
     */
    public void setStartBtn(MouseEvent event) {
        Main.myGame.startTurn();
    }
}
