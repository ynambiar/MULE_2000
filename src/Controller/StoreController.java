package Controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import Model.Main;

import java.io.Serializable;
import java.net.URL;

public class StoreController implements Serializable {

  @FXML
  private Button buyFood;
  @FXML
  private Button buyEnergy;
  @FXML
  private Button buySmithore;
  @FXML
  private Button buyMule;
  @FXML
  private Button leaveStore;
  @FXML
  private Button confirmBtnFood;

  @FXML
  private Label titleLbl;

  @FXML
  private TextField buyTxtFood;
  @FXML
  private TextField sellTxtFood;
  @FXML
  private TextField buyTxtEnergy;
  @FXML
  private TextField sellTxtEnergy;
  @FXML
  private TextField buyTxtSmithore;
  @FXML
  private TextField sellTxtSmithore;

  /**
   * Sound code that makes chaChing sound.
   */
  private void chaChing() {
    final URL resource = getClass().getResource("/view/Resources/money.mp3");
    final Media media = new Media(resource.toString());
    final MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();
  }

  @FXML
  /**
   * Loads food Store transaction scene.
   */
  private void setBuyFood() {
    chaChing();
    MasterController.getInstance().loadStoreTransactionSceneFood();
  }

  @FXML
  /**
   * Loads energy Store transaction scene.
   */
  private void setBuyEnergy() {
    chaChing();
    MasterController.getInstance().loadStoreTransactionSceneEnergy();
  }

  /**
   * Loads smithore Store transaction scene.
   */
  @FXML
  private void setBuySmithore() {
    chaChing();
    MasterController.getInstance().loadStoreTransactionSceneSmithore();
  }

  @FXML
  /**
   * Loads mule transaction scene.
   */
  private void setBuyMule() {
    chaChing();
    MasterController.getInstance().loadMuleTransactionScene();
  }

  @FXML
  /**
   * Sets Food Confirm button and does transaction with food.
   */
  private void setConfirmBtnFood() {
    Main.myGame.doStoreTransaction("Food", true, Integer.parseInt(buyTxtFood
        .getText()));
    Main.myGame.doStoreTransaction("Food", true, -1
        * Integer.parseInt(sellTxtFood.getText()));
    buyTxtFood.setText("0");
    sellTxtFood.setText("0");
    MasterController.getInstance().loadStoreScene();
  }

  @FXML
  /**
   * Sets Energy Confirm button and does transaction with energy.
   */
  private void setConfirmBtnEnergy() {
    Main.myGame.doStoreTransaction("Energy", true, Integer
        .parseInt(buyTxtEnergy.getText()));
    Main.myGame.doStoreTransaction("Energy", true, -1
        * Integer.parseInt(sellTxtEnergy.getText()));
    buyTxtEnergy.setText("0");
    sellTxtEnergy.setText("0");
    MasterController.getInstance().loadStoreScene();
  }

  @FXML
  /**
   * Sets Smirthore Confirm button and does transaction with smithore.
   */
  private void setConfirmBtnSmithore() {
    Main.myGame.doStoreTransaction("Smithore", true, Integer
        .parseInt(buyTxtSmithore.getText()));
    Main.myGame.doStoreTransaction("Smithore", true, -1
        * Integer.parseInt(sellTxtSmithore.getText()));
    buyTxtSmithore.setText("0");
    sellTxtSmithore.setText("0");
    MasterController.getInstance().loadStoreScene();
  }

  @FXML
  /**
   * Loads Town scene.
   */
  private void setLeaveStore() {
    MasterController.getInstance().loadTownScene();
  }

  /* CODE FOR ANIMATION */

  @FXML
  private ImageView foodAnim;
  @FXML
  private ImageView energyAnim;
  @FXML
  private ImageView muleAnim;
  @FXML
  private ImageView caAnim;
  @FXML
  private ImageView oreAnim;

  @FXML
  /**
   * Sets food animation to visible.
   */
  private void foodmouseEnter() {
    foodAnim.setVisible(true);
  }

  @FXML
  /**
   * Sets food animation to not visible.
   */
  private void foodmouseLeave() {
    foodAnim.setVisible(false);
  }

  @FXML
  /**
   * Sets energy animation to visible.
   */
  private void energymouseEnter() {
    energyAnim.setVisible(true);
  }

  @FXML
  /**
   * Sets energy animation to not visible.
   */
  private void energymouseLeave() {
    energyAnim.setVisible(false);
  }

  @FXML
  /**
   * Sets mule animation to visible.
   */
  private void mulemouseEnter() {
    muleAnim.setVisible(true);
  }

  @FXML
  /**
   * Sets mule animation to not visible.
   */
  private void mulemouseLeave() {
    muleAnim.setVisible(false);
  }

  @FXML
  /**
   * Sets crystite animation to visible.
   */
  private void cmouseEnter() {
    caAnim.setVisible(true);
  }

  @FXML
  /**
   * Sets crystite animation to not visible.
   */
  private void cmouseLeave() {
    caAnim.setVisible(false);
  }

  @FXML
  /**
   * Sets smithore animation to visible.
   */
  private void oremouseEnter() {
    oreAnim.setVisible(true);
  }

  @FXML
  /**
   * Sets smithore animation to visible.
   */
  private void oremouseLeave() {
    oreAnim.setVisible(false);
  }
  /* END OF ANIMATION CODE */
}
