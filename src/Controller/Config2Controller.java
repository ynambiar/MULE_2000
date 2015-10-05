package Controller;

import Model.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Config2Controller {

    @FXML
    private ComboBox<String> combo1race, combo2race, combo3race, combo4race;
//    @FXML
//    private Button nextBtn, backBtn;

    @FXML
    private Button startGame, back;
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
    private void startGame() {
            if (!combo1human.getValue().equals("Not playing")) {
                System.out.println(p1Name.getText());
                System.out.println(combo1human.getValue());
                System.out.println(combo1race.getValue());

                //This kept giving me null pointer exceptions. Originally my plan for this method was
                //to have every text field and combo box from the screen be a parameter for this method and then
                //let addPlayer() handle putting the information into the ArrayList<Player> players field of myGame
                //which is the object of the Game class that is instantiated at the very beginning of the game
                //by the Main class which contains our main method.  - Tucker
                Main.myGame.addPlayer(p1Name.getText(), combo1human.getValue(), combo1race.getValue());
                //MasterController.getInstance().changeTo__()

            } else {
                errorLabel.setVisible(true);
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