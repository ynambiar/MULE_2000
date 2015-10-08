package Controller;
import Model.Main;
import Model.Player;
import javafx.scene.layout.GridPane;
import Model.Map;
import Model.Map.MapType;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

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
    @FXML
    private Label foodLbl;
    @FXML
    private Label energyLbl;
    @FXML
    private Label smithoreLbl;
    @FXML
    private Label moneyLbl;
    @FXML
    private Button endTurnBtn;

    public void createMap(MapType type) {
        Map myMap;
        if (type == MapType.RANDOM) {
            myMap = new Map(MapType.RANDOM);
        } else {
            myMap = new Map();
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                ImageView tile = new ImageView(myMap.getTile(i, j).imagePath());
                BorderPane tileContainer = new BorderPane();
                tileContainer.setCenter(tile);
                if (i == 2 && j == 4) {
                    tileContainer.setOnMouseClicked(this::townClicked);
                } else {
                    tileContainer.setOnMouseClicked(this::tileClicked);
                }
                map.add(tileContainer, j, i);
            }
        }
    }

    public void tileClicked(MouseEvent event) {
        BorderPane tile = (BorderPane) event.getSource();
        int row = map.getRowIndex(tile);
        int col = map.getColumnIndex(tile);
        Player p = Main.myGame.getCurrentPlayer();
        if (Main.myGame.tileClicked(row, col)) {
            tile.setStyle("-fx-border-color: " + p.getColor() + "; -fx-border-width: 6px;");
            if (Main.myGame.getRoundNumber() < 1) {
                Main.myGame.endTurn();
            }
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
                        System.out.println(Main.myGame.getTimeLeft());
                    }
                });
            }
        }, 0 , 1000);
    }


    public void townClicked(MouseEvent event) {
        if (Main.myGame.getRoundNumber() >= 1) {
            MasterController.getInstance().loadTownScene();
            Main.myGame.setPurchasingLand(false);
        }
    }

    @FXML
    public void setEndTurnBtn() {
        Main.myGame.endTurn();
    }

    public void setCurrentPhaseLabel(String s) {
        currentPhaseLabel.setText(s);
    }
    public void setCurrentPlayerLabel(String s) {
        currentPlayerLabel.setText(s);
    }
    public void setTimeLeftLabel(String s) {
        timeLeftLabel.setText(s);
    }
    public void setFoodLabel(String s) {
        foodLbl.setText(s);
    }
    public void setEnergyLabel(String s) {
        energyLbl.setText(s);
    }
    public void setSmithoreLabel(String s) {
        smithoreLbl.setText(s);
    }
    public void setMoneyLabel(String s) { moneyLbl.setText(s);}


}
