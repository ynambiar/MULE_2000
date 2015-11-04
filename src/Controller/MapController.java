package Controller;

import Model.*;
import javafx.scene.layout.GridPane;
import Model.Map.MapType;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;


public class MapController implements Serializable {

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
    @FXML
    private Button saveGameBtn;

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
                StackPane tileContainer = new StackPane();
                tileContainer.getChildren().add(tile);
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
        Game g = Main.myGame;
        StackPane tile = (StackPane) event.getSource();
        int row = map.getRowIndex(tile);
        int col = map.getColumnIndex(tile);
        Player p = g.getCurrentPlayer();
        if (g.tileClicked(row, col)) {
            if (g.getPhase().equals("Selling Land")) {
                tile.setStyle("-fx-border-color: GREEN;");
            } else if (g.getPhase().equals("Emplacing Mule")) {
                ImageView mule;
                if (g.getMuleType() == Mule.FOOD) {
                    mule = new ImageView("/View/Resources/tinyFoodMule.png");
                } else if (g.getMuleType() == Mule.ENERGY) {
                    mule = new ImageView("/View/Resources/tinyEnergyMule.png");
                } else {
                    mule = new ImageView("/View/Resources/tinySmithoreMule.png");
                }
                tile.getChildren().add(mule);
            } else if (g.getPhase().equals("Selling Mules")) {
                tile.getChildren().remove(1);
            } else {
                tile.setStyle("-fx-border-color: " + p.getColor() + "; -fx-border-width: 6px;");
            }
            if (g.getRoundNumber() < 1) {
                g.endTurn();
            }
        }
    }

    public void startTimer() {
        Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        timeLeftLabel.setText(((Integer) Main.myGame.getTimeLeft()).toString());
                        Main.myGame.decrementTimeLeft();
                    }
                });
            }
        }, 0, 1000);
    }


    public void townClicked(MouseEvent event) {
        if (Main.myGame.getRoundNumber() >= 1) {
            MasterController.getInstance().loadTownScene();
            Main.myGame.setPhase("Normal Play");
            Main.myGame.refreshLabels();
        }
    }

    @FXML
    /**
     * Set End Turn button to end turn.
     */
    public void setEndTurnBtn() {
        Main.myGame.endTurn();
    }

    /**
     * Sets current phase label to given String.
     * @param s
     */
    public void setCurrentPhaseLabel(String s) {
        currentPhaseLabel.setText(s);
    }

    /**
     * Sets current player label to given String.
     * @param s
     */
    public void setCurrentPlayerLabel(String s) {
        currentPlayerLabel.setText(s);
    }

    /**
     * Sets time left label to given String.
     * @param s
     */
    public void setTimeLeftLabel(String s) {
        timeLeftLabel.setText(s);
    }

    /**
     * Sets food label to given String.
     * @param s
     */
    public void setFoodLabel(String s) {
        foodLbl.setText(s);
    }

    /**
     * Sets energy label to given String.
     * @param s
     */
    public void setEnergyLabel(String s) {
        energyLbl.setText(s);
    }

    /**
     * Sets smithore label to given String.
     * @param s
     */
    public void setSmithoreLabel(String s) {
        smithoreLbl.setText(s);
    }

    /**
     * Sets money label to given String.
     * @param s
     */
    public void setMoneyLabel(String s) { moneyLbl.setText(s);}

    /**
     * Sets Save Game button to save data.
     * @throws IOException
     */
    public void setSaveGameBtn() throws IOException {
        Main.myGame.saveData();
        System.out.println("Game Saved.");
    }

}