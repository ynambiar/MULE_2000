package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class StartController {

    @FXML
    private Button ruleBtn;
    @FXML
    private Button playBtn;

    @FXML
    private void setRuleBtn() {
        MasterController.getInstance().loadAsteroidScene();
    }

    @FXML
    private void setPlayBtn() {
        MasterController.getInstance().loadConfig1Scene();
    }
}
