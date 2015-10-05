//package game;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
///**
// * Created by tuckerlocicero on 9/24/15.
// */
//public class insideTownController extends configScreens.ControllerSuper {
//
//    @FXML
//    private Button leaveTownBtn;
//    @FXML
//    private Button pubBtn;
//
//    @FXML
//    private void buttonHandler(ActionEvent event) throws IOException {
//        try {
//            Stage stage;
//            Parent root;
//            Button source = (Button) event.getSource();
//            stage = (Stage) source.getScene().getWindow();
//            if (source == leaveTownBtn) {
//                root = FXMLLoader.load(getClass().getResource("Map.fxml"));
//            } else if (source == pubBtn){
//                root = FXMLLoader.load(getClass().getResource("InsidePub.fxml"));
//            } else {
//                root = null;
//            }
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            System.out.println("> IOException in insideTownController's buttonHandler()");
//        }
//    }
//
//
//
//}
