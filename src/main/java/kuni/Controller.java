package kuni;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import logic.Kuni;
import window.MainWindow;

public class Controller implements EventHandler<MouseEvent> {
	
	Kuni kuni;
	MainWindow view;
	
	public Controller(Kuni game, MainWindow window) {
		this.kuni = game;
		this.view = window;
	}

	/**
	 * This event handles the hovering-over of menu buttons.
	 */
	public void handle(MouseEvent mouse) {
		view.getButton().collision(mouse.getSceneX(), mouse.getSceneY());
	}


}
