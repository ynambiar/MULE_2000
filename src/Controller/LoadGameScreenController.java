package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by SaqlainGolandaz on 10/27/15.
 */
public class LoadGameScreenController {

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    private void setNoButton() {
        MasterController.getInstance().loadStartScene();
    }



}
