package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import Model.Main;
import Model.Map.Difficulty;
import Model.Map.MapType;

import java.io.Serializable;

public class Config1Controller implements Serializable {

  /*
   Anchor pane.
  */
  @FXML
  private AnchorPane pane;
  /*
   Back button.
  */
  @FXML
  private Button backBtn;
  /*
   Next button.
  */
  @FXML
  private Button nextBtn;
  /*
   Difficulty toggle.
  */
  @FXML
  private ToggleGroup difficultyToggle;
  /*
   Map toggle.
  */
  @FXML
  private ToggleGroup mapToggle;
  /*
   Easy radio button.
  */
  @FXML
  private RadioButton easyRadio;
  /*
   Medium radio button.
  */
  @FXML
  private RadioButton mediumRadio;
  /*
   Hard radio button.
  */
  @FXML
  private RadioButton hardRadio;
  /*
   Standard radio button.
  */
  @FXML
  private RadioButton standardMapRadio;
  /*
   Random map radio button.
  */
  @FXML
  private RadioButton randomMapRadio;
  /*
   Error Label.
  */
  @FXML
  private Label errorLabel;

  private boolean configsAreSet = false;
  //JAVADOC
  @FXML
  private void setBackBtn() {
    MasterController.getInstance().loadStartScene();
  }

  @FXML
  /**
  * Sets action for the next button.
  */
  private void setNextBtn() {
    boolean toggles[] = {false, false};
    Model.Game game = Main.myGame;
    difficultyToggle.getToggles().stream().filter(Toggle::isSelected).forEach(t -> {
      toggles[0] = true;
      if (easyRadio.isSelected()) {
        game.setDifficulty(Difficulty.EASY);
      }
      if (mediumRadio.isSelected()) {
        game.setDifficulty(Difficulty.MEDIUM);
      }
      if (hardRadio.isSelected()) {
        game.setDifficulty(Difficulty.HARD);
      }
      System.out.println(game.getDifficulty());
    });
    mapToggle.getToggles().stream().filter(Toggle::isSelected).forEach(t -> {
      toggles[1] = true;
      if (standardMapRadio.isSelected()) {
        game.setMapType(MapType.STANDARD);
      }
      if (randomMapRadio.isSelected()) {
        game.setMapType(MapType.RANDOM);
      }
      System.out.println(Main.myGame.getMapType());
    });
    if (toggles[0] && toggles[1]) {
      configsAreSet = true;
    }
    if (configsAreSet) {
      MasterController.getInstance().getMapController().createMap(
          game.getMapType());
      MasterController.getInstance().loadConfig2Scene();
      // Driver.mygame.createMap();
    } else {
      errorLabel.setVisible(true);
    }
  }

  /**
  * Intilializes toggle groups.
  */
  public final void initialize() {
    mapToggle = new ToggleGroup();
    difficultyToggle = new ToggleGroup();
    easyRadio.setToggleGroup(difficultyToggle);
    mediumRadio.setToggleGroup(difficultyToggle);
    hardRadio.setToggleGroup(difficultyToggle);
    standardMapRadio.setToggleGroup(mapToggle);
    randomMapRadio.setToggleGroup(mapToggle);
  }
}
