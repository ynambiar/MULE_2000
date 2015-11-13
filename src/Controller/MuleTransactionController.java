package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import Model.Game;
import Model.Main;
import Model.Mule;

import java.io.Serializable;

/**
 * Mule Transaction Controller class
 */
public class MuleTransactionController implements Serializable {

  /**
    Sell button.
   */
  @FXML
  private Button sellBtn;

  /**
    Buy button.
   */
  @FXML
  private Button buyBtn;

  /**
   Cancel button.
   */
  @FXML
  private Button cancelBtn;

  /**
    Food mule.
   */
  @FXML
  private Rectangle foodMule;

  /**
    Energy mule.
   */
  @FXML
  private Rectangle energyMule;

  /**
    Smithore mule.
   */
  @FXML
  private Rectangle smithoreMule;

  /**
    Flower mule.
   */
  @FXML
  private Rectangle flowerMule;

  /**
    Boolean that tells us if food is selected in store.
   */
  private boolean foodSelected;

  /**
    Boolean that tells us if energy is selected in store.
   */
  private boolean energySelected;

  /**
    Boolean that tells us if smithore is selected in store.
   */
  private boolean smithoreSelected;

  /**
    Boolean that tells us if flower is selected in store.
   */
  private boolean flowerSelected;

  /**
  * Set Cancel button to load store scene.
  */
  @FXML
  private void setCancelBtn() {
    MasterController.getInstance().loadStoreScene();
  }


  /**
  * Set Buy button function to update current player.
  */
  @FXML
  private void setBuyBtn() {
    Game gameset = Main.myGame;
    int cost;
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
      } else {
        gameset.setMuleType(Mule.FLOWER);
        cost = 600;
      }

      if (gameset.getCurrentPlayer().getMoney() >= cost) {
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


  /**
  * Set Sell button to load Map scene.
  */
  @FXML
  private void setSellBtn() {
    Main.myGame.setPhase("Selling Mules");
    Main.myGame.refreshLabels();
    MasterController.getInstance().loadMapScene();
  }

  /**
  * Sets properties for food mule.
  */
  @FXML
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

  /**
  * Sets properties for energy mule.
  */
  @FXML
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

  /**
  * Sets properts of smithore mule.
  */
  @FXML
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

  /**
  * Sets properts of flower mule.
  */
  @FXML
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
