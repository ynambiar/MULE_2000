package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import Model.Game;
import Model.Main;
import Model.Mule;


import java.io.Serializable;

public class MuleTransactionController implements Serializable {

  @FXML
  private Button sellBtn;
  @FXML
  private Button buyBtn;
  @FXML
  private Button cancelBtn;
  @FXML
  private Rectangle foodMule;
  @FXML
  private Rectangle energyMule;
  @FXML
  private Rectangle smithoreMule;
  @FXML
  private Rectangle flowerMule;

  private boolean foodSelected;
  private boolean energySelected;
  private boolean smithoreSelected;
  private boolean flowerSelected;

  @FXML
  /**
  * Set Cancel button to load store scene.
  */
  private void setCancelBtn() {
    MasterController.getInstance().loadStoreScene();
  }

  @FXML
  /**
  * Set Buy button function to update current player.
  */
  private void setBuyBtn() {
    Game gameset = Main.myGame;
    int cost = 0;
    if (foodSelected || energySelected || smithoreSelected || flowerSelected) {
      if (foodSelected) {
        gameset.setMuleType(Mule.FOOD);
        cost = 125;
      } else if (energySelected) {
        gameset.setMuleType(Mule.ENERGY);
        cost = 150;
      } else if (smithoreSelected) {
        gameset.setMuleType(Mule.SMITHORE);
        cost = 175;
      } else if (Main.myGame.getMapType().equals("Random")) {
        gameset.setMuleType(Mule.FLOWER);
        cost = 600;
      }

      if (gameset.getCurrentPlayer().getMoney() >= cost && cost != 0) {
        gameset.setPhase("Emplacing Mule");
        gameset.getCurrentPlayer().addMoney(cost * -1);
        gameset.refreshLabels();
        MasterController.getInstance().loadMapScene();
        foodSelected = false;
        energySelected = false;
        smithoreSelected = false;
        flowerSelected = false;
        flowerMule.setOpacity(0);
        smithoreMule.setOpacity(0);
        energyMule.setOpacity(0);
        foodMule.setOpacity(0);
      } else {
        System.out.println("You can't afford this mule");
      }
    }
  }

  @FXML
  /**
  * Set Sell button to load Map scene.
  */
  private void setSellBtn() {
    Main.myGame.setPhase("Selling Mules");
    Main.myGame.refreshLabels();
    MasterController.getInstance().loadMapScene();
  }

  @FXML
  /**
  * Sets properties for food mule.
  */
  private void setFoodMule() {
    if (foodSelected) {
      foodMule.setOpacity(0);
      foodSelected = false;
    } else {
      foodMule.setOpacity(.30);
      foodSelected = true;
      energyMule.setOpacity(0);
      energySelected = false;
      smithoreMule.setOpacity(0);
      smithoreSelected = false;
      flowerMule.setOpacity(0);
      flowerSelected = false;
    }
  }

  @FXML
  /**
  * Sets properties for energy mule.
  */
  private void setEnergyMule() {
    if (energySelected) {
      energyMule.setOpacity(0);
      energySelected = false;
    } else {
      energyMule.setOpacity(.30);
      energySelected = true;
      foodMule.setOpacity(0);
      foodSelected = false;
      smithoreMule.setOpacity(0);
      smithoreSelected = false;
      flowerMule.setOpacity(0);
      flowerSelected = false;
    }
  }

  @FXML
  /**
  * Sets properts of smithore mule.
  */
  private void setSmithoreMule() {
    if (smithoreSelected) {
      smithoreMule.setOpacity(0);
      smithoreSelected = false;
    } else {
      smithoreMule.setOpacity(.30);
      smithoreSelected = true;
      energyMule.setOpacity(0);
      energySelected = false;
      foodMule.setOpacity(0);
      foodSelected = false;
      flowerMule.setOpacity(0);
      flowerSelected = false;
    }
  }

  @FXML
  /**
  * Sets properts of flower mule.
  */
  private void setFlowerMule() {
    if (flowerSelected) {
      flowerMule.setOpacity(0);
      flowerSelected = false;
    } else {
      flowerMule.setOpacity(.30);
      flowerSelected = true;
      energyMule.setOpacity(0);
      energySelected = false;
      foodMule.setOpacity(0);
      foodSelected = false;
      smithoreSelected = false;
      smithoreMule.setOpacity(0);
    }
  }
}
