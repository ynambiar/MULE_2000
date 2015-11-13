package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import Model.Game;
import Model.Main;


import java.io.Serializable;
/**
* Start Turn Controller.
*/
class StartTurnController implements Serializable {

  /**
    Start turn label.
   */
  @FXML
  private Label startTurnLabel;

  /**
    Start button.
   */
  @FXML
  private Button startBtn;

  /**
  * Sets labels for the scene.
  */
  @FXML
  public final void initialize() {
    Game game = Main.myGame;
    if (game.getCurrentPlayer() != null) {
      if (game.getRoundNumber() > 0) {
        startTurnLabel.setText("Round " + game.getRoundNumber() + ": "
            + game.getCurrentPlayer().getName() + "'s turn!");
      } else {
        startTurnLabel.setText("Land Selection Phase: "
            + game.getCurrentPlayer().getName() + "'s turn!");
      }
    }
  }

  /**
   * Sets Start button function.
   * @param event MouseEvent
   */
  @FXML
  public final void setStartBtn(final MouseEvent event) {
    Main.myGame.startTurn();
  }
}
