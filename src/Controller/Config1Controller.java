package Controller;

import Model.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
        for (Toggle t : difficultyToggle.getToggles()) {
            if (t.isSelected()) {
                toggles[0] = true;
                Driver.myGame.setDifficulty(t.toString());
                System.out.println(Driver.myGame.getDifficulty());
            }
        }
        for (Toggle t : mapToggle.getToggles()) {
            if (t.isSelected()) {
                toggles[1] = true;
                Driver.myGame.setMapType(t.toString());
                System.out.println(Driver.myGame.getMapType());
            }
        }
        if (toggles[0] && toggles[1]) {
            configsAreSet = true;
        }

        if (configsAreSet) {
            MasterController.getInstance().loadPlaceHolderScene();
            //Driver.mygame.createMap()
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
