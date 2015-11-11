package Controller;

import javafx.fxml.FXML;
import Model.Main;

import java.io.Serializable;
/**
* Town Controller Class.
*/

public class TownController implements Serializable {

  @FXML
  private void setLeaveTownBtn() {
    Main.myGame.setPhase("Regular Turn");
    Main.myGame.refreshLabels();
    MasterController.getInstance().loadMapScene();
  }

  @FXML
  private void setPubBtn() {
    MasterController.getInstance().loadPubScene();
  }

  @FXML
  private void setStoreBtn() {
    MasterController.getInstance().loadStoreScene();
  }

  @FXML
  private void setLandBtn() {
    MasterController.getInstance().loadLandOfficeScene();
  }
}
