package Controller;
import Model.Main;
import javafx.scene.layout.GridPane;
import Model.Map;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;


public class MapController {

    @FXML
    private GridPane map;

    public void initialize() {
        System.out.println("hey");

        //Creates the standard map
        Map myMap = new Map();
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

    public void tileChosen(MouseEvent event) {
        System.out.println("I'm chosen");
        BorderPane tile = (BorderPane) event.getSource();
        int row = map.getRowIndex(tile);
        int col = map.getColumnIndex(tile);
        if (Main.myGame.getMap().purchase(row, col)) {
            System.out.println("change me");
            ImageView select = new ImageView("/View/Resources/p1Selector");
            tile.
        }
    }
}
