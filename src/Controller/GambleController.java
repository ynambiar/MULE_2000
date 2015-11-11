package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import Model.Main;

import java.io.Serializable;
/**
* Gamble Controller Class.
*/

public class GambleController implements Serializable {
  //JAVADOC
  @FXML
  private Label resultsLabel;

  @FXML
  /**
  * Set OK button function so that turn ends.
  */
  public final void setOkBtn() {
    Main.myGame.endTurn();
  }

  /**
  * Initialize Gamble Controller.
  */
  public final void initialize() {
    resultsLabel.setText(Integer.toString(Main.myGame.getGamble()));
  }
}
