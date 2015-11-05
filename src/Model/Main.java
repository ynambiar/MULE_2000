package model;

import Controller.MasterController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.Serializable;

public class Main extends Application implements Serializable {

	public static Game myGame;

	/**
	 * Required start() method for extension of Application class.
	 * Sets the Stage and loads the Start Scene.
	 * @param stage
	 */
	public void start(Stage stage) {
		myGame = new Game();
		MasterController controller = MasterController.getInstance();
		controller.setStage(stage);
		controller.loadStartScene();
		stage.setTitle("M.U.L.E. - Deep Thought");
		stage.setResizable(false);
		stage.show();
	}

	/**
	 * Launches the program.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	public static void setMyGame(Game game) {
		myGame = game;
	}
}