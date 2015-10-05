package Controller;
import Model.Main;
import Model.Player;
import javafx.scene.layout.GridPane;
import Model.Map;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class MapController {

    @FXML
    private Label time;
    @FXML
    private GridPane map;



    public void initialize() {
        //Creates the standard map
        //TODO create a random map
        Map myMap = new Map();
        for (int i = 0; i < myMap.getHeight(); i++) {
            for (int j = 0; j < myMap.getWidth(); j++) {
                ImageView tile = new ImageView(myMap.getTile(i, j).imagePath());
                BorderPane tileContainer = new BorderPane();
                tileContainer.setCenter(tile);
                if (i == 2 && j == 4) {
                    tileContainer.setOnMouseClicked(this::townClicked);
                } else {
                    tileContainer.setOnMouseClicked(this::tileChosen);
                }
                map.add(tileContainer, j, i);
            }
        }

    }

    public void tileChosen(MouseEvent event) {
        BorderPane tile = (BorderPane) event.getSource();
        int row = map.getRowIndex(tile);
        int col = map.getColumnIndex(tile);
        Player p = Main.myGame.getMap().purchase(row, col);
        if (p != null) {
            tile.setStyle("-fx-border-color: " + p.getColor() + "; -fx-border-width: 6px;");
        }
    }

    boolean timerStats;
    Timer timer;
    int timersec;
    int timermin;
    int timerhr;

    @FXML
    private void startTime(){
        if(timerStats==false)
        {
            timerStats = true;
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {

                @Override
                public void run() {

                    System.out.println("working");

                    timersec ++;

                    Platform.runLater(new Runnable(){
                        public void run(){

                            if (timersec == 60)
                            {
                                timersec = 0;
                                timermin++;
                            }
                            if (timermin == 60)
                            {
                                timermin = 0;
                                timerhr++;
                            }

                            String seconds = Integer.toString(timersec);
                            String minutes = Integer.toString(timermin);
                            String hours = Integer.toString(timerhr);

                            if (timersec <= 9)
                            {
                                seconds = "0" + Integer.toString(timersec);
                            }
                            if (timermin <= 9)
                            {
                                minutes = "0" + Integer.toString(timermin);
                            }
                            if (timerhr <= 9)
                            {
                                hours = "0" + Integer.toString(timerhr);
                            }

                            time.setText(hours + ":" + minutes +":"+ seconds);
                            System.out.println(time.getText());
                        }


                    });


                }

            };
            timer.schedule(timerTask, 50, 50); //lastone is time, milli second

        }

    }

    public void townClicked(MouseEvent event) {
        if (Main.myGame.getRoundNumber() != 0) {
            MasterController.getInstance().loadTownScene();
        }
    }

}
