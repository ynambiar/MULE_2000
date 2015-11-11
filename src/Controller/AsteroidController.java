//package Controller;
//
//import javafx.animation.AnimationTimer;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.scene.Scene;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//
//
///**
// * Created by William on 10/22/2015.
// */
//public class AsteroidController {
//
//    @FXML
//    ImageView spaceship;
//
//    private int xVel = 0;
//    private long lastUpdateTime;
//    double minX = 0;
//    double maxX = 400;
//
//    //Scene scene = MasterController.getInstance().getAsteroidScene();
//
//    private AnimationTimer shipAnimation = new AnimationTimer() {
//        @Override
//        public void handle(long timestamp) {
//            if (lastUpdateTime > 0) {
//                double elapsedSeconds = (timestamp - lastUpdateTime) / 1_000_000_000.0;
//                double deltaX = elapsedSeconds * xVel;
//                double oldX = spaceship.getTranslateX();
//                double newX = Math.max(minX, Math.min(maxX, oldX + deltaX));
//                spaceship.setTranslateX(newX);
//            }
//            lastUpdateTime = timestamp;
//        }
//    };
//
//    public void initialize() {
//        System.out.println("Initialize asteroid controller \n");
//        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.RIGHT) {
//                    xVel = 10;
//                } else if (event.getCode() == KeyCode.LEFT) {
//                    xVel = -10;
//                }
//            }
//        });
//        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
//                    xVel = 0;
//                }
//            }
//        });
//        shipAnimation.start();
//    }
//}
