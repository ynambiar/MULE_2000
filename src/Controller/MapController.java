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
                muleImg = new ImageView("/view/Resources/tinyFoodMule.png");
              } else if (mule == Mule.ENERGY) {
                muleImg = new ImageView("/view/Resources/tinyEnergyMule.png");
              } else {
                muleImg = new ImageView(
                        "/view/Resources/tinySmithoreMule.png");
              }
              tileContainer.getChildren().add(muleImg);
            }

          }
        }
      }
    }
  }

  public final void tileClicked(final MouseEvent event) {
    Game gameset = Main.myGame;
    StackPane tile = (StackPane) event.getSource();
    int row = map.getRowIndex(tile);
    int col = map.getColumnIndex(tile);
    Player play = gameset.getCurrentPlayer();
    if (gameset.tileClicked(row, col)) {
      if (gameset.getPhase().equals("Selling Land")) {
        tile.setStyle("-fx-border-color: GREEN;");
      } else if (gameset.getPhase().equals("Emplacing Mule")) {
        ImageView mule;
        if (gameset.getMuleType() == Mule.FOOD) {
          mule = new ImageView("/view/Resources/tinyFoodMule.png");
        } else if (gameset.getMuleType() == Mule.ENERGY) {
          mule = new ImageView("/view/Resources/tinyEnergyMule.png");
        } else {
          mule = new ImageView("/view/Resources/tinySmithoreMule.png");
        }
        tile.getChildren().add(mule);
      } else if (gameset.getPhase().equals("Selling Mules")) {
        tile.getChildren().remove(1);
      } else {
        tile.setStyle("-fx-border-color: " + play.getColor()
            + "; -fx-border-width: 6px;");
      }
      if (gameset.getRoundNumber() < 1) {
        gameset.endTurn();
      }
    }
  }

  public final void startTimer() {
    Timer timer = new java.util.Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        Platform.runLater(new Runnable() {
          public void run() {
            timeLeftLabel.setText(((Integer) Main.myGame.getTimeLeft())
                    .toString());
            Main.myGame.decrementTimeLeft();
          }
        });
      }
    }, 0, 1000);
  }

  public final void townClicked(final MouseEvent event) {
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
