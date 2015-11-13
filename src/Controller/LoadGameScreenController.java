package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import Model.Main;

import java.io.IOException;

import java.io.Serializable;

/**
* Load Game Screen Class.
*/
public class LoadGameScreenController implements Serializable {
  /**
    Yes save button.
  */
  @FXML
  private Button yesButton;
  /**
   No save button.
  */
  @FXML
  private Button noButton;

  /**
   * Set No button action to load Start scene.
   */
  @FXML
  private void setNoButton() {
    MasterController.getInstance().loadStartScene();
  }

  /**
   * Set Yes button action to load data.
   */
  @FXML
  private void setYesButton() throws IOException, ClassNotFoundException {
    Main.myGame.loadData();
  }

}
