package Controller;

import javafx.fxml.FXML;
import Model.Main;

import java.io.Serializable;
/**
* Town Controller Class.
*/
public class TownController implements Serializable {

  /**
   * Changes screens from town to main map
   */
  @FXML
  private void setLeaveTownBtn() {
    Main.myGame.setPhase("Regular Turn");
    Main.myGame.refreshLabels();
    MasterController.getInstance().loadMapScene();
  }

  /**
   * Changes screens from town to pub
   */
  @FXML
  private void setPubBtn() {
    MasterController.getInstance().loadPubScene();
  }

  /**
   * Changes screens from town to store
   */
  @FXML
  private void setStoreBtn() {
    MasterController.getInstance().loadStoreScene();
  }

  /**
   * Changes screens from town to land office
   */
  @FXML
  private void setLandBtn() {
    MasterController.getInstance().loadLandOfficeScene();
  }
}
