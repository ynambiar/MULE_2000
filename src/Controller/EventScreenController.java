package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import Model.Main;

import java.io.Serializable;
/**
* Event Screen Controller class.
*/

public class EventScreenController implements Serializable {

  /*
   Event Label.
  */
  @FXML
  private Label eventLabel;

  @FXML
  /**
   * Sets OK button action.
   */
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
