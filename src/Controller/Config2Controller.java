package Controller;


import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class Config2Controller {

    @FXML
    private ComboBox<String> combo1race, combo2race, combo3race, combo4race;
//    @FXML
//    private Button nextBtn, backBtn;

    @FXML
    private Button startGame, backBtn;
    @FXML
    private ComboBox<String> combo1human, combo2human, combo3human, combo4human;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField p1Name, p2Name, p3Name, p4Name;
    @FXML
    ObservableList<String> humanBox = FXCollections.observableArrayList("Human", "AI", "Not playing");
    @FXML
    ObservableList<String> raceBox = FXCollections.observableArrayList("Human", "Flapper", "Bonzoid", "Ugaite", "Buzzite");

    @FXML
    public void setBackBtn() {
        MasterController.getInstance().loadConfig1Scene();
    }

    @FXML
    private void setStartGameBtn() {
        if (comboBoxesAreVerififed()) {
            MasterController.getInstance().loadMapScene();
        } else {
            errorLabel.setVisible(true);
        }
    }


    private boolean comboBoxesAreVerififed() {
        boolean[] players = {false, false, false, false};
        ArrayList<Player> playersList = Main.myGame.getPlayers();
        if (combo1human.getValue() == "Not playing") {
            players[0] = true;
        } else if (combo1human.getValue() != null && combo1race.getValue() != null) {
            players[0] = true;
            playersList.add(new Player(p1Name.getText(), combo1human.getValue(), combo1race.getValue()));
            //after names are added, add name parameter to game.Player ctor
        }
        if (combo2human.getValue() == "Not playing") {
            players[1] = true;
        } else if (combo2human.getValue() != null && combo2race.getValue() != null) {
            players[1] = true;
           playersList.add(new Player(p2Name.getText(), combo1human.getValue(), combo1race.getValue()));
        }
        if (combo3human.getValue() == "Not playing") {
            players[2] = true;
        } else if (combo3human.getValue() != null && combo3race.getValue() != null) {
            players[2] = true;
            playersList.add(new Player(p3Name.getText(), combo1human.getValue(), combo1race.getValue()));
        }
        if (combo4human.getValue() == "Not playing") {
            players[3] = true;
        } else if (combo4human.getValue() != null && combo4race.getValue() != null) {
            players[3] = true;
            playersList.add(new Player(p4Name.getText(), combo1human.getValue(), combo1race.getValue()));
        }
        if (players[0] && players[1] && players[2] && players[3]) {
            return true;
        } else {
            return false;
        }



    }



    public void initialize() throws IOException {
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