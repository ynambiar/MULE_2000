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
public class StartTurnController implements Serializable {

  /*
    start turn label
   */
  @FXML
  private Label startTurnLabel;
  /*
    start button
   */
  @FXML
  private Button startBtn;

  /**
  * Sets labels for the scene.
  */
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

  @FXML
  /**
   * Sets Start button function.
   * @param event MouseEvent
   */
  public final void setStartBtn(final MouseEvent event) {
    Main.myGame.startTurn();
  }
}
