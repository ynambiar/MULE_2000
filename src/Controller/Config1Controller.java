package Controller;

import Model.*;
import Model.Map.Difficulty;
import Model.Map.MapType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Toggle;
import javafx.scene.layout.AnchorPane;

public class Config1Controller {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button backBtn, nextBtn;
    @FXML
    private ToggleGroup difficultyToggle, mapToggle;
    @FXML
    private RadioButton easyRadio, mediumRadio, hardRadio, standardMapRadio, randomMapRadio;
    @FXML
    private Label errorLabel;

    boolean configsAreSet = false;

    @FXML
    private void setBackBtn() {
        MasterController.getInstance().loadStartScene();
    }

    @FXML
    private void setNextBtn() {
        boolean toggles[] = {false, false};
        Model.Game game = Main.myGame;
        for (Toggle t : difficultyToggle.getToggles()) {
            if (t.isSelected()) {
                toggles[0] = true;
                if (easyRadio.isSelected()) {
                    game.setDifficulty(Map.Difficulty.EASY);
                }
                if (mediumRadio.isSelected()) {
                    game.setDifficulty(Difficulty.MEDIUM);
                }
                if (hardRadio.isSelected()) {
                    game.setDifficulty(Difficulty.HARD);
                }
                System.out.println(game.getDifficulty());
            }
        }
        for (Toggle t : mapToggle.getToggles()) {
            if (t.isSelected()) {
                toggles[1] = true;
                if (standardMapRadio.isSelected()) {
                    game.setMapType(MapType.STANDARD);
                }
                if (randomMapRadio.isSelected()) {
                    game.setMapType(MapType.RANDOM);
                }
                System.out.println(Main.myGame.getMapType());
            }
        }
        if (toggles[0] && toggles[1]) {
            configsAreSet = true;
        }
        if (configsAreSet) {
            MasterController.getInstance().loadConfig2Scene();
            //Driver.mygame.createMap();
        } else {
            errorLabel.setVisible(true);
        }
    }



    public void initialize() {
        mapToggle = new ToggleGroup();
        difficultyToggle = new ToggleGroup();
        easyRadio.setToggleGroup(difficultyToggle);
        mediumRadio.setToggleGroup(difficultyToggle);
        hardRadio.setToggleGroup(difficultyToggle);
        standardMapRadio.setToggleGroup(mapToggle);
        randomMapRadio.setToggleGroup(mapToggle);
    }
}
