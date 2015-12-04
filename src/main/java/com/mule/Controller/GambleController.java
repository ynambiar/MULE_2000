package main.java.com.mule.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.java.com.mule.Main;

import java.io.Serializable;
/**
* Gamble main.java.com.mule.Controller Class.
*/

public class GambleController implements Serializable {
  /*
   Results label.
  */
  @FXML
  private Label resultsLabel;

  /**
  * Set OK button function so that turn ends.
  */
  @FXML
  public final void setOkBtn() {
    Main.myGame.endTurn();
  }

  /**
  * Initialize Gamble main.java.com.mule.Controller.
  */
  public final void initialize() {
    resultsLabel.setText(Integer.toString(Main.myGame.getGamble()));
  }
}
