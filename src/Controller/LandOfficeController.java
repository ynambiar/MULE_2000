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
    private Button yesBuyLandBtn;
    @FXML
    private Button noBuyLandBtn;
    @FXML
    private Button yesSellLandBtn;

    @FXML
    private void setYesBuyLandBtn() {
        Main.myGame.setPurchasingLand(true);
        Main.myGame.buyLand();
    }

    @FXML
    private void setNoBuyLandBtn() {
        MasterController.getInstance().loadTownScene();
    }

    @FXML
    private void setYesSellLandBtn() {}
}
