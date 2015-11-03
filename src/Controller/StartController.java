package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.Serializable;

public class StartController implements Serializable {

    @FXML
    private Button ruleBtn;
    @FXML
    private Button playBtn;
    @FXML
    private Button loadBtn;

    @FXML
    private void setRuleBtn() {
        MasterController.getInstance().loadRulesScene();
    }

    @FXML
    private void setPlayBtn() {
        MasterController.getInstance().loadConfig1Scene();
    }

    @FXML
    private void setLoadBtn() {
        MasterController.getInstance().loadLoadScreenScene();
    }
}
