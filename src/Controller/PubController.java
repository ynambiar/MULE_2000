package Controller;

import Model.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PubController {

    //pub screen
    @FXML
    private Button yesGambleBtn, noGambleBtn;

    //results screen
    @FXML
    private Button okBtn;
    @FXML
    private Label resultsLabel;

    @FXML
    private void buttonHandler(MouseEvent event) {
        Button source = (Button) event.getSource(); //yesGambleBtn, noGambleBtn, okBtn
        if (source == yesGambleBtn) {
            /*
            TODO
            must calculate bonus based on time
            set resultsLabel equal to the bonus
            show the results screen
             */
            MasterController.getInstance().loadGamblingResultsScene();
        } else if (source == noGambleBtn) {
            System.out.println("going to the town");
            MasterController.getInstance().loadTownScene();
        } else if (source == okBtn){
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
//                resultsLabel.setText(Integer.toString(bonus));
    }
}
