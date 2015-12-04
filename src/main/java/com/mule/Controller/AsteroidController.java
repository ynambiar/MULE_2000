// package controller;

// import javafx.animation.AnimationTimer;
// import javafx.event.EventHandler;
// import javafx.fxml.FXML;
// import javafx.scene.image.ImageView;
// import javafx.scene.input.KeyCode;
// import javafx.scene.input.KeyEvent;

// /**
// * Asteroid main.java.com.mule.Controller class.
// */

// public class AsteroidController {
//   //JAVADOC
//   @FXML
//   private ImageView spaceship;

//   private int xvel = 0;
//   private long lastUpdateTime;
//   private double minX = 0;
//   private double maxX = 400;

//   // Scene scene = MasterController.getInstance().getAsteroidScene();
//   /**
//   * ANIMATION TIMER.
//   * @param timestamp long
//   */
//   private final AnimationTimer shipAnimation = new AnimationTimer() {
//     @Override
//     public final void handle(final long timestamp) {
//       if (lastUpdateTime > 0) {
//         double elapsedSeconds = (timestamp - lastUpdateTime) / 1_000_000_000.0;
//         double deltaX = elapsedSeconds * xvel;
//         double oldX = spaceship.getTranslateX();
//         double newX = Math.max(minX, Math.min(maxX, oldX + deltaX));
//         spaceship.setTranslateX(newX);
//       }
//       lastUpdateTime = timestamp;
//     }
//   };

//   /**
//   * Initialize.
//   */
//   public final void initialize() {
//     System.out.println("Initialize asteroid main.java.com.mule.Controller \n");
//     scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//       @Override
//       public void handle(final KeyEvent event) {
//         if (event.getCode() == KeyCode.RIGHT) {
//           xvel = 10;
//         } else if (event.getCode() == KeyCode.LEFT) {
//           xvel = -10;
//         }
//       }
//     });
//     scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//       @Override
//       public void handle(final KeyEvent event) {
//         if (event.getCode() == KeyCode.RIGHT
//             || event.getCode() == KeyCode.LEFT) {
//           xvel = 0;
//         }
//       }
//     });
//     shipAnimation.start();
//   }
// }
