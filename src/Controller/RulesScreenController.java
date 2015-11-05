package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.Serializable;


public class RulesScreenController implements Serializable {

    @FXML
    private Button backBtn;

    //TODO Add Label (or maybe Scroll Pane) that has the rules written out

    @FXML
    /**
     * Set Back button to load Start scene.
     */
    private void setBackBtn() {
        MasterController.getInstance().loadStartScene();
    }

    /**
     * Required method for Serializable class.
     */
    public void initialize() {

    }


}
