package Controller;
import javafx.scene.layout.GridPane;
import Model.Map;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;


/**
 * Created by yamininambiar on 10/3/15.
 */
public class MapController extends MasterController {

    @FXML
    private GridPane map;

    public void initialize() {
        System.out.println("hey");

        //Creates the standard map
        Map myMap = new Map(); //TODO implement random map
        for (int i = 0; i < myMap.getHeight(); i++) {
            for (int j = 0; j < myMap.getWidth(); j++) {
                ImageView tile = new ImageView(myMap.getTile(i, j).imagePath());
                BorderPane tileContainer = new BorderPane();
                tileContainer.setCenter(tile);
                tileContainer.setOnMouseClicked(this::tileChosen);
                map.add(tileContainer, j, i);
            }
        }
    }

    public void tileChosen(MouseEvent e) {


    }
}
