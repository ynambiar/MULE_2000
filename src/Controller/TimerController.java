package game;
/**
 * Created by yamininambiar on 10/2/15.
 */


        import javafx.application.Platform;
        import javafx.fxml.FXML;
        import java.awt.event.ActionEvent;

        import javax.swing.*;
        import javax.xml.soap.Text;
        import java.util.TimerTask;

/**
 * Created by AshikaGanesh on 9/30/15.
 */
public class TimerController {



//    @FXML
//    public boolean endRoundEarly(ActionEvent a) {
//        Button source2 = (Button) a.getSource();
//        if(source2.equals(endRound)) {
//            bonusPoints = countDown;
//            countDown = 0;
//            if(bonusPoints >= 37 || bonusPoints < 50) {
//                bonusPoints = 200;
//            } else if(bonusPoints >= 25 || bonusPoints < 37) {
//                bonusPoints = 150;
//            } else if(bonusPoints >= 12 || bonusPoints < 25) {
//                bonusPoints = 100;
//            } else {
//                bonusPoints = 50;
//            }
//            System.out.println(bonusPoints);
//            //HOW DO YOU GET CURRENT PLAYER
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @FXML
//    private int countDown;
//    @FXML
//    private Text time = new Text();
//
//    @FXML
//    public void startCountDown() {
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(new Runnable() {
//                    public void run() {
//                        countDown = currentPlayer.calculatePlayerTime(currentPlayer);
//                        if (countDown != 0) {
//                            System.out.println(countDown);
//                            //time.setText("Time left:" + countDown);
//                            countDown--;
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//                            System.out.println("Your turn is up");
//                            if (driver.getPlayers().size() >= currentPlayer.getPlayerNumber() + 1) {
//                                currentPlayer.setRoundNumber(currentPlayer.getRoundNumber() + 1);
//                                currentPlayer = driver.getPlayers().get(currentPlayer.getPlayerNumber());
//                                System.out.println("Player " + currentPlayer + "'s  turn!");
//                                countDown = driver.getPlayers().get(currentPlayer.getPlayerNumber()).calculatePlayerTime(driver.getPlayers().get(currentPlayer.getPlayerNumber()));
//                                //time.setText("Your turn is up.");
//                                return;
//                            } else {
//                                System.out.println("End of round " + currentPlayer.getRoundNumber());
//                                currentPlayer.setRoundNumber(currentPlayer.getRoundNumber() + 1);
//                                System.out.println("Start of round " + currentPlayer.getRoundNumber());
//                                driver.calculatePlayerOrder();
//                                currentPlayer = driver.getPlayers().get(0);
//                            }
//                        }
//
//                    }
//                });
//            }
//        }, 1000, 1000);
//    }
}