package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.Serializable;
/**
* Start controller Class.
**/

public class StartController implements Serializable {
  //JAVADOC
  @FXML
  private Button ruleBtn;
  @FXML
  private Button playBtn;
  @FXML
  private Button loadBtn;

  @FXML
  private void setRuleBtn() {
    // MasterController.getInstance().loadAsteroidScene();
  }

  @FXML
  /**
  * Sets Play button to load Config 1 scene.
  */
  private void setPlayBtn() {
    MasterController.getInstance().loadConfig1Scene();
  }

  @FXML
  /**
   * Sets Load button to Load screen scene.
   */
  private void setLoadBtn() {
    MasterController.getInstance().loadLoadScreenScene();
  }
}
