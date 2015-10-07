package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Random;

public class PubController {

    //results Screen
    @FXML
    private Label moneyLabel;

    @FXML
    private void setYesGambleBtn() {
        System.out.println("moneyLabel: " + moneyLabel);
        int bonus = 0;
        int[] roundBonus = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};
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
        Main.myGame.getCurrentPlayer().addMoney(bonus);
        MasterController.getInstance().loadGamblingResultsScene();
        moneyLabel.setText(Integer.toString(bonus));
    }

    @FXML
    private void setNoGambleBtn() {
        MasterController.getInstance().loadTownScene();
    }

    @FXML
    private void setOkBtn() {
        Main.myGame.endTurn();
    }
//                //int time = driver.getTimeLeft();
//                //int time = 100;
//                int time = driver.getCountDown();
//                if (time >= 37) {
//                    time = 200;
//                } else if (37 > time && time >= 25) {
//                    time = 150;
//                } else if (25 > time && time >= 12) {
//                    time = 100;
//                } else {
//                    time = 50;
//                }
//                int round = driver.getRoundNumber();
//                int[] roundBonuses = {50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};
//                /* Hey, I made this change to bonus because there was an ArrayIndexOutOfBoundsException.
//                    It might not make logical sense but I just wanted to get it to run.
//                    Feel free to change it! - Yamini
//                int bonus = new Random().nextInt(time) + roundBonuses[round - 1]; */
//                int bonus = new Random().nextInt(time) + roundBonuses[round + 2];
//                driver.getPlayer().addMoney(bonus);
//                //Display money earned screen
//                root = FXMLLoader.load(getClass().getResource("Pub.fxml"));
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//                moneyLabel.setText(Integer.toString(bonus));
}
