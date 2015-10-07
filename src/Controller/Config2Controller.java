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

    private boolean comboBoxesAreVerififed() {
        Game game = Main.myGame;
        boolean verified = false;
        if (combo1human.getValue() != "Not Playing" && combo1race.getValue() != null && p1Name.getText() != null) {
            game.addPlayer(new Player(combo1human.getValue(), combo1race.getValue(), p1Name.getText(), "DODGERBLUE"));
        }
        if (combo2human.getValue() != "Not Playing" && combo2race.getValue() != null && p2Name.getText() != null) {
            game.addPlayer(new Player(combo2human.getValue(), combo2race.getValue(), p2Name.getText(), "#ff321f"));
        }
        if (combo3human.getValue() != "Not Playing" && combo3race.getValue() != null && p3Name.getText() != null) {
            game.addPlayer(new Player(combo3human.getValue(), combo3race.getValue(), p3Name.getText(), "#dd02cf"));
        }
        if (combo4human.getValue() != "Not Playing" && combo4race.getValue() != null && p4Name.getText() != null) {
            game.addPlayer(new Player(combo4human.getValue(), combo4race.getValue(), p4Name.getText(), "#ffa639"));
        }
        if (game.getPlayers().size() > 1) {
            verified = true;
        }
        for (Player p : game.getPlayers()) {
            System.out.println(p.toString());
        }
        return verified;
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