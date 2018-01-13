package kuni;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import logic.Kuni;
import window.MainWindow;

public class MenuController implements EventHandler<MouseEvent> {
	
	Kuni kuni;
	MainWindow view;
	
	public MenuController(Kuni game, MainWindow window) {
		this.kuni = game;
		this.view = window;
	}

	/**
	 * This event handles the hovering-over of menu buttons.
	 */
	public void handle(MouseEvent mouse) {
		if(mouse.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
			view.getButton().onHover();
		}
		else if(mouse.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
			view.getButton().stopHover();
		}
	}


}
