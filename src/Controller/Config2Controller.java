package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Game;
import model.Main;
import model.Player;


import java.io.IOException;
import java.io.Serializable;

/**
* Confgi2Controller Class.
*/

public class Config2Controller implements Serializable {
  //JAVADOC THIS
  @FXML
  private ComboBox<String> combo1race;
  @FXML
  private ComboBox<String> combo2race;
  @FXML
  private ComboBox<String> combo3race;
  @FXML
  private ComboBox<String> combo4race;
  @FXML
  private Button startGame;
  @FXML
  private Button backBtn;
  @FXML
  private ComboBox<String> combo1human;
  @FXML
  private ComboBox<String> combo2human;
  @FXML
  private ComboBox<String> combo3human;
  @FXML
  private ComboBox<String> combo4human;
  @FXML
  private Label errorLabel;
  @FXML
  private TextField p1Name;
  @FXML
  private TextField p2Name;
  @FXML
  private TextField p3Name;
  @FXML
  private TextField p4Name;
  @FXML
  private ObservableList<String> humanBox = FXCollections.observableArrayList(
      "Human", "AI", "Not playing");
  @FXML
  private ObservableList<String> raceBox =
      FXCollections.observableArrayList("Human",
      "Flapper", "Bonzoid", "Ugaite", "Buzzite", "Strandoid", "Pegasite",
      "Techoid");

  @FXML
  /**
  * Sets Back button function.
  */
  public final void setBackBtn() {
    MasterController.getInstance().loadConfig1Scene();
  }

  @FXML
  /**
  * Sets Start Game button function.
  */
  private void setStartGameBtn() {
    try {
      if (comboBoxesAreVerififed()) {
        Main.myGame.startGame();
      } else {
        errorLabel.setVisible(true);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
  * Verifies combo boxes and adds players accordingly.
  *
  * @return boolean
  */
  private boolean comboBoxesAreVerififed() {
    Game game = Main.myGame;
    boolean verified = false;
    if (combo1human.getValue() != "Not Playing"
        && combo1race.getValue() != null && p1Name.getText() != null) {
      game.addPlayer(new Player(combo1human.getValue(),
          combo1race.getValue(), p1Name.getText(), "DODGERBLUE"));
    }
    if (combo2human.getValue() != "Not Playing"
        && combo2race.getValue() != null && p2Name.getText() != null) {
      game.addPlayer(new Player(combo2human.getValue(),
          combo2race.getValue(), p2Name.getText(), "#ff321f"));
    }
    if (combo3human.getValue() != "Not Playing"
        && combo3race.getValue() != null && p3Name.getText() != null) {
      game.addPlayer(new Player(combo3human.getValue(),
          combo3race.getValue(), p3Name.getText(), "#dd02cf"));
    }
    if (combo4human.getValue() != "Not Playing"
        && combo4race.getValue() != null && p4Name.getText() != null) {
      game.addPlayer(new Player(combo4human.getValue(),
          combo4race.getValue(), p4Name.getText(), "#ffa639"));
    }
    if (game.getPlayers().size() > 1) {
      verified = true;
    }
    for (Player p : game.getPlayers()) {
      System.out.println(p.toString());
    }
    return verified;
  }

  /**
  * Initializes Configuration screen 2.
  *
  * @throws IOException exception
  */
  public final void initialize() throws IOException {
    combo1human.setItems(humanBox);
    combo2human.setItems(humanBox);
    combo3human.setItems(humanBox);
    combo4human.setItems(humanBox);
    combo1race.setItems(raceBox);
    combo2race.setItems(raceBox);
    combo3race.setItems(raceBox);
    combo4race.setItems(raceBox);
  }
}
