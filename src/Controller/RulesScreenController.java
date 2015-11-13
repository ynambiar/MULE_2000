package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.Serializable;
/**
* Rules Screen Controller.
*/
class RulesScreenController implements Serializable {

  /*
    button that takes you back to home screen from rules screen
   */
  @FXML
  private Button backBtn;

  // TODO Add Label (or maybe Scroll Pane) that has the rules written out

  @FXML
  /**
   * Set Back button to load Start scene.
   */
  private void setBackBtn() {
    MasterController.getInstance().loadStartScene();
  }

}
