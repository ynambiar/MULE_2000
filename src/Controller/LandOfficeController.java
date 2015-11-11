package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Model.Game;
import Model.Main;

import java.io.Serializable;
import java.util.Random;

/**
* Land Office Controller class.
*/

public class LandOfficeController implements Serializable {
  /*
    Yes Buy Land button.
   */
  @FXML
  private Button yesBuyLandBtn;
  /*
   No buy land button.
  */
  @FXML
  private Button noBuyLandBtn;
  /*
   Yes sell land button.
  */
  @FXML
  private Button yesSellLandBtn;
  /*
   Buying price label.
  */
  @FXML
  private Label buyPrice;
  /*
   Selling price label.
  */
  @FXML
  private Label sellPrice;
  /*
   Buying price.
  */
  private int buyingPrice;
  /*
   Selling price.
  */
  private int sellingPrice;

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
  * Initialize Land Office Controller.
  */
  public final void initialize() {
    Game gameset = Main.myGame;
    Random random = new Random(gameset.getRoundNumber()
        + gameset.getCurrentPlayer().hashCode() + gameset.hashCode());
    buyingPrice = 300 + Main.myGame.getRoundNumber() * random.nextInt(100);
    sellingPrice = 400 + random.nextInt(200);
    this.buyPrice.setText("Buying Price: " + buyingPrice);
    this.sellPrice.setText("Selling Price: " + sellingPrice);
  }
}
