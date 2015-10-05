//package game;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
///**
// * Created by ashika ganesh.
// */
//public class playerTurnFinishedController extends ControllerSuper {
//
//        @FXML
//        private Button editAbout;
//
//        @FXML
//        private Label errorLabel;
//
//        @FXML
//        private void handleButtonAction(ActionEvent event) throws IOException {
//            Stage stage;
//            Parent root;
//            Button source = (Button) event.getSource();
//            stage = (Stage) source.getScene().getWindow();
//            if (source == editAbout) {
//                root = FXMLLoader.load(getClass().getResource("/game/playerTurnFinished.fxml"));
//            } else {
//                root = null;
//            }
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//
//}
