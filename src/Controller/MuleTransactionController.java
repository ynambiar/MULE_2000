package Controller;

import Model.Game;
import Model.Main;
import Model.Mule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class MuleTransactionController implements Serializable {

    @FXML
    Button sellBtn;
    @FXML
    Button buyBtn;
    @FXML
    Button cancelBtn;
    @FXML
    Rectangle foodMule;
    @FXML
    Rectangle energyMule;
    @FXML
    Rectangle smithoreMule;

    boolean foodSelected, energySelected, smithoreSelected;

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
        Game g = Main.myGame;
        if (foodSelected || energySelected || smithoreSelected) {
            int cost;
            if (foodSelected) {
                g.setMuleType(Mule.FOOD);
                cost = 125;
            } else if (energySelected) {
                g.setMuleType(Mule.ENERGY);
                cost = 150;
            } else {
                g.setMuleType(Mule.SMITHORE);
                cost = 175;
            }
            if (g.getCurrentPlayer().getMoney() >= cost) {
                g.setPhase("Emplacing Mule");
                g.getCurrentPlayer().addMoney(cost * -1);
                g.refreshLabels();
                MasterController.getInstance().loadMapScene();
                foodSelected = false;
                energySelected = false;
                smithoreSelected = false;
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
        }
    }
}
