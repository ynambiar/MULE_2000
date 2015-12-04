package main.java.com.mule.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.Serializable;
/**
* Start main.java.com.mule.Controller Class.
**/
public class StartController implements Serializable {

  /**
    Rules button.
   */
  @FXML
  private Button ruleBtn;

  /**
    Play button.
   */
  @FXML
  private Button playBtn;
  /**
    Load button.
   */
  @FXML
  private Button loadBtn;

  /**
   * Loads the rules screen (In Progress)

  private void setRuleBtn() {
    // MasterController.getInstance().loadRuleScene();
  }
   */

  /**
  * Sets Play button to load Config 1 scene.
  */
  @FXML
  private void setPlayBtn() {
    MasterController.getInstance().loadConfig1Scene();
  }


  /**
   * Sets Load button to Load screen scene.
   */
  @FXML
  private void setLoadBtn() {
    MasterController.getInstance().loadLoadScreenScene();
  }

  /**
   * Sets Rule button to Load screen scene.
   */
  @FXML
  private void setRuleBtn() {
    MasterController.getInstance().loadRulesScene();
  }
}
