//package game;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.Random;
//
//public class insidePubController extends configScreens.ControllerSuper{
//
//    @FXML
//    private Button yesGambleBtn;
//    @FXML
//    private Button noGambleBtn;
//
//    @FXML
//    private Button doneGamblingBtn;
//
//    @FXML
//    private Label resultsLabel = new Label();
//
//    @FXML
//    private void buttonHandler(ActionEvent event) {
//        try {
//            Stage stage;
//            Parent root = null;
//            Button source = (Button) event.getSource();
//            stage = (Stage) source.getScene().getWindow();
//            if (source == yesGambleBtn) {
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
//                //Display money earned screen TODO
//                root = FXMLLoader.load(getClass().getResource("InsidePub.fxml"));
////                Scene scene = new Scene(root);
////                stage.setScene(scene);
////                stage.show();
//                resultsLabel.setText(Integer.toString(bonus));
//            } else if (source == noGambleBtn) {
//                root = FXMLLoader.load(getClass().getResource("InsideTown.fxml"));
//            } else if (source == doneGamblingBtn) {
//                driver.endTurn();
//                root = FXMLLoader.load(getClass().getResource("InsideTown.fxml"));
//            }
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            System.out.println("> IOException in insidePubController's buttonHandler()");
//        }
//    }
//}
