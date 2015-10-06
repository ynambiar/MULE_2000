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
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.util.Timer;
import java.util.TimerTask;


public class MapController {

    @FXML
    private GridPane map;
    @FXML
    private Label currentPhaseLabel;
    @FXML
    private Label currentPlayerLabel;
    @FXML
    private Label timeLeftLabel;


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

    public void startTimer() {
        Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        timeLeftLabel.setText(((Integer)Main.myGame.getTimeLeft()).toString());
                        Main.myGame.decrementTimeLeft();
                        System.out.println("tick tock");
                    }
                });
            }
        }, 0 , 1000);
    }


    public void townClicked(MouseEvent event) {
        if (Main.myGame.getRoundNumber() != 0) {
            MasterController.getInstance().loadTownScene();
        }
    }

}
