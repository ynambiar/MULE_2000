package Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import Model.Game;
import Model.Main;
import Model.Map.MapType;
import Model.Map;
import Model.Mule;
import Model.Player;

import java.io.IOException;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Map Controller Class
 */
public class MapController implements Serializable {

  /*
    map
   */
  @FXML
  private GridPane map;
  /*
    current phase label
   */
  @FXML
  private Label currentPhaseLabel;
  /*
    current player label
   */
  @FXML
  private Label currentPlayerLabel;
  /*
    time left label
   */
  @FXML
  private Label timeLeftLabel;
  /*
    food label
   */
  @FXML
  private Label foodLbl;
  /*
    energy label
   */
  @FXML
  private Label energyLbl;
  /*
    smithore label
   */
  @FXML
  private Label smithoreLbl;
  /*
    money label
   */
  @FXML
  private Label moneyLbl;

  /*
    "End Turn" button
   */
  @FXML
  private Button endTurnBtn;
  /*
    "Save Game" button
   */
  @FXML
  private Button saveGameBtn;

  /**
   * Creates a Map
   * @param type the type of Map (either standard or randomized)
   */
  public final void createMap(final MapType type) {
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

  /**
   * Sets a map
   * @param myMap the map that needs to be set as the main map
   */
  public final void setMap(final Map myMap) {
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
        for (Player play : Main.myGame.getPlayers()) {
          if (play.getTileOwned(i, j)) {
            tileContainer.setStyle("-fx-border-color: " + play.getColor()
                + "; -fx-border-width: 6px;");
            Mule mule = play.getMuleEmplaced(i, j);
            if (mule != null) {
              ImageView muleImg;
              if (mule == Mule.FOOD) {
                muleImg = new ImageView("/View/Resources/tinyFoodMule.png");
              } else if (mule == Mule.ENERGY) {
                muleImg = new ImageView("/View/Resources/tinyEnergyMule.png");
              } else {
                muleImg = new ImageView(
                    "/View/Resources/tinySmithoreMule.png");
              }
              tileContainer.getChildren().add(muleImg);
            }

          }
        }
      }
    }
  }

  /**
   * Carries out various actions if a tile is cliced
   * @param event an event/click of a tile
   */
  private void tileClicked(final MouseEvent event) {
    Game gameset = Main.myGame;
    StackPane tile = (StackPane) event.getSource();
    int row = map.getRowIndex(tile);
    int col = map.getColumnIndex(tile);
    Player play = gameset.getCurrentPlayer();
    if (gameset.tileClicked(row, col)) {
      switch (gameset.getPhase()) {
        case "Selling Land":
          tile.setStyle("-fx-border-color: GREEN;");
          break;
        case "Emplacing Mule":
          ImageView mule;
          if (gameset.getMuleType() == Mule.FOOD) {
            mule = new ImageView("/View/Resources/tinyFoodMule.png");
          } else if (gameset.getMuleType() == Mule.ENERGY) {
            mule = new ImageView("/View/Resources/tinyEnergyMule.png");
          } else {
            mule = new ImageView("/View/Resources/tinySmithoreMule.png");
          }
          tile.getChildren().add(mule);
          break;
        case "Selling Mules":
          tile.getChildren().remove(1);
          break;
        default:
          tile.setStyle("-fx-border-color: " + play.getColor()
                  + "; -fx-border-width: 6px;");
          break;
      }
      if (gameset.getRoundNumber() < 1) {
        gameset.endTurn();
      }
    }
  }

  /**
   * Timer is started when run
   */
  public final void startTimer() {
    Timer timer = new java.util.Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        Platform.runLater(() -> {
          timeLeftLabel.setText(((Integer) Main.myGame.getTimeLeft())
                  .toString());
          Main.myGame.decrementTimeLeft();
        });
      }
    }, 0, 1000);
  }

  /**
   * Loads town when town tile is clicked
   * @param event a click on the tile with the town
   */
  private void townClicked(final MouseEvent event) {
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
  public final void setEndTurnBtn() {
    Main.myGame.endTurn();
  }

  /**
   * Sets current phase label to given String.
   *
   * @param newstring String
   */
  public final void setCurrentPhaseLabel(final String newstring) {
    currentPhaseLabel.setText(newstring);
  }

  /**
   * Sets current player label to given String.
   *
   * @param newstring String
   */
  public final void setCurrentPlayerLabel(final String newstring) {
    currentPlayerLabel.setText(newstring);
  }

  /**
   * Sets time left label to given String.
   *
   * @param newstring String
   */
  public final void setTimeLeftLabel(final String newstring) {
    timeLeftLabel.setText(newstring);
  }

  /**
   * Sets food label to given String.
   *
   * @param newstring String
   */
  public final void setFoodLabel(final String newstring) {
    foodLbl.setText(newstring);
  }

  /**
   * Sets energy label to given String.
   *
   * @param newstring String
   */
  public final void setEnergyLabel(final String newstring) {
    energyLbl.setText(newstring);
  }

  /**
   * Sets smithore label to given String.
   *
   * @param newstring String
   */
  public final void setSmithoreLabel(final String newstring) {
    smithoreLbl.setText(newstring);
  }

  /**
   * Sets money label to given String.
   *
   * @param newstring String
   */
  public final void setMoneyLabel(final String newstring) {
    moneyLbl.setText(newstring);
  }

  /**
   * Sets Save Game button to save data.
   *
   * @throws IOException exception
   */
  public final void setSaveGameBtn() throws IOException {
    Main.myGame.saveData();
    System.out.println("Game Saved.");
  }

}
