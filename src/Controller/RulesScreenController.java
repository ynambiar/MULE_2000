//package configScreens;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//
//
///**
// * Created by AshikaGanesh on 9/21/15.
// */
//public class RulesScreenController extends ControllerSuper {
//
//    @FXML
//    private Button config3BackBtn;
//
//
//    @FXML
//    private void config3ButtonAction(ActionEvent event) throws IOException {
//        Stage stage;
//        Parent root;
//        Button source = (Button) event.getSource();
//        stage = (Stage) source.getScene().getWindow();
//        if (source == config3BackBtn) {
//            root = FXMLLoader.load(getClass().getResource("home.fxml"));
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//    }
//
//}
