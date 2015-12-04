package main.java.com.mule.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.java.com.mule.Main;

import java.io.Serializable;

import java.util.Random;

/**
 * Pub main.java.com.mule.Controller Class.
 */
public class PubController implements Serializable {

  /**
    Money label.
   */
  @FXML
  private Label moneyLabel;

  /**
   * Set Yes Gamble button to add bonus money to current player.
  */
  @FXML
  private void setYesGambleBtn() {
    System.out.println("moneyLabel: " + moneyLabel);
    int bonus;
    int[] roundBonus = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150,
        200};
    bonus = roundBonus[Main.myGame.getRoundNumber() - 1];
    int[] timeBonus = {200, 150, 100, 50};
    int time = Main.myGame.getTimeLeft();
    if (time >= 37) {
      bonus *= new Random().nextInt(timeBonus[0]);
    } else if (time >= 25) {
      bonus *= new Random().nextInt(timeBonus[1]);
    } else if (time >= 12) {
      bonus *= new Random().nextInt(timeBonus[2]);
    } else if (time >= 0) {
      bonus *= new Random().nextInt(timeBonus[3]);
    } else {
      bonus = 0;
    }
    if (bonus > 250) {
      bonus = 250;
    }
    System.out.println("Currentplayer: " + Main.myGame.getCurrentPlayer()
        + " bonus: " + bonus);
    Main.myGame.getCurrentPlayer().addMoney(bonus);
    Main.myGame.setGamble(bonus);
    MasterController.getInstance().loadGamblingResultsScene();
  }

  /**
   * Set No Gamble button to load town scene.
  */
  @FXML
  private void setNoGambleBtn() {
    MasterController.getInstance().loadTownScene();
  }

}
