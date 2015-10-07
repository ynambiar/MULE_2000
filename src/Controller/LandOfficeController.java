package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Created by William on 10/6/2015.
 */
public class LandOfficeController {

    @FXML
    private Button yesBuyLand;
    @FXML
    private Button noBuyLand;

    @FXML
    private void setYesBuyLand() {
        Main.myGame.setPurchasingLand(true);
        Main.myGame.buyLand();
    }

    @FXML
    private void setNoBuyLand() {
        MasterController.getInstance().loadTownScene();
    }
}
