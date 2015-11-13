package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.Serializable;
/**
* Start Controller Class.
**/
public class StartController implements Serializable {

  /*
    rules button
   */
  @FXML
  private Button ruleBtn;
  /*
    play button
   */
  @FXML
  private Button playBtn;
  /*
    load button
   */
  @FXML
  private Button loadBtn;

  /**
   * Loads the rules screen (In Progress)

  private void setRuleBtn() {
    // MasterController.getInstance().loadRuleScene();
  }
   */

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
