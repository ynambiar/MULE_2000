package Controller;

import Model.Game;
import Model.Main;
import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * Created by tuckerlocicero on 10/6/15.
 */
public class StoreController {

    @FXML
    private Button buyFood, buyEnergy, buySmithore, buyMule, leaveStore;

    public void buttonHandler(MouseEvent event) {
        Button source = (Button) event.getSource();
        if (source == leaveStore) {
            MasterController.getInstance().loadTownScene();
        } else if (source == buyFood) {
            Main.myGame.doStoreTransaction("food", true, 1);
        } else if (source == buyEnergy) {
            Main.myGame.doStoreTransaction("energy", true, 1);
        } else if (source == buySmithore) {
            Main.myGame.doStoreTransaction("smithore", true, 1);
        } else if (source == buyMule) {
            Main.myGame.doStoreTransaction("mule", true, 1);
        }
    }

}
