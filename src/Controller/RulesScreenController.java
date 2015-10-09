package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class RulesScreenController {

    @FXML
    private Button backBtn;

    //TODO Add Label (or maybe Scroll Pane) that has the rules written out

    @FXML
    private void setBackBtn() {
        MasterController.getInstance().loadStartScene();
    }

    public void initialize() {

    }


}
