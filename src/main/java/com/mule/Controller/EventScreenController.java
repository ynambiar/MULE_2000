package main.java.com.mule.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import main.java.com.mule.Main;

import java.io.Serializable;
/**
* Event Screen main.java.com.mule.Controller class.
*/

public class EventScreenController implements Serializable {

  /**
   Event Label.
  */
  @FXML
  private Label eventLabel;

  /**
   * Sets OK button action.
   */
  @FXML
  public final void setOkBtn(final MouseEvent event) {
    Main.myGame.finishEvent();
  }

  /**
   * Initialize Event screen.
   */
  public final void initialize() {
    eventLabel.setText(Main.myGame.getCurrentEvent().toString());
  }
}
