package Controller;

import Model.Game;
import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;
import java.util.Random;

public class LandOfficeController implements Serializable {

    @FXML
    private Button yesBuyLandBtn;
    @FXML
    private Button noBuyLandBtn;
    @FXML
    private Button yesSellLandBtn;
    @FXML
    private Label buyPrice;
    @FXML
    private Label sellPrice;

    private int buyingPrice, sellingPrice;

    @FXML
    /**
     * Set Yes Buy Land button and sets land cost.
     */
    private void setYesBuyLandBtn() {
        Main.myGame.setPhase("Purchasing Land");
        Main.myGame.setLandCost(buyingPrice);
        MasterController.getInstance().loadMapScene();
        Main.myGame.refreshLabels();
    }

    @FXML
    /**
     * Sets No button and loads town screen.
     */
    private void setNoBuyLandBtn() {
        MasterController.getInstance().loadTownScene();
    }

    @FXML
    /**
     * Set Yes Sell Land button and sets land selling price.
     */
    private void setYesSellLandBtn() {
        Main.myGame.setPhase("Selling Land");
        Main.myGame.setLandCost(sellingPrice);
        MasterController.getInstance().loadMapScene();
        Main.myGame.refreshLabels();
    }

    /**
     * Initialize Land Office controller.
     */
    public void initialize() {
        Game g = Main.myGame;
        Random random = new Random(g.getRoundNumber() + g.getCurrentPlayer().hashCode() + g.hashCode());
        buyingPrice = 300 + Main.myGame.getRoundNumber() * random.nextInt(100);
        sellingPrice = 400 + random.nextInt(200);
        this.buyPrice.setText("Buying Price: " + buyingPrice);
        this.sellPrice.setText("Selling Price: " + sellingPrice);
    }
}
