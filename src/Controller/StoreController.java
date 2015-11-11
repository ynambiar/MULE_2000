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

/**
 * Store Controller Class.
 */
public class StoreController implements Serializable {

  /*
    Button to buy food
   */
  @FXML
  private Button buyFood;
  /*
    Button to buy energy
   */
  @FXML
  private Button buyEnergy;
  /*
    Button to buy smithore
   */
  @FXML
  private Button buySmithore;
  /*
    Button to buy mule
   */
  @FXML
  private Button buyMule;
  /*
    Button to leave store and go back to town
   */
  @FXML
  private Button leaveStore;
  /*
    Confirm button for buying/selling food
   */
  @FXML
  private Button confirmBtnFood;

  /*
    Title label
   */
  @FXML
  private Label titleLbl;

  /*
    Text field to enter how much food one wants to buy
   */
  @FXML
  private TextField buyTxtFood;
  /*
    Text field to enter how much food one wants to sell
   */
  @FXML
  private TextField sellTxtFood;
  /*
    Text field to enter how much energy one wants to buy
   */
  @FXML
  private TextField buyTxtEnergy;
  /*
    Text field to enter how much energy one wants to sell
   */
  @FXML
  private TextField sellTxtEnergy;
  /*
    Text field to enter how much smithore one wants to buy
   */
  @FXML
  private TextField buyTxtSmithore;
  /*
    Text field to enter how much smithore one wants to sell
   */
  @FXML
  private TextField sellTxtSmithore;

  /**
   * Sound code that makes chaChing sound.
   */
  private void chaChing() {
    final URL resource = getClass().getResource("/View/Resources/money.mp3");
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

  /*
    Food animation
   */
  @FXML
  private ImageView foodAnim;
  /*
    Energy animation
   */
  @FXML
  private ImageView energyAnim;
  /*
    Mule animation
   */
  @FXML
  private ImageView muleAnim;
  /*
    ca animation
   */
  @FXML
  private ImageView caAnim;
  /*
    ore animation
   */
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
