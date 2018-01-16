package kuni;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import logic.Kuni;
import window.MainWindow;
import window.MenuButton;

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
			((MenuButton) mouse.getSource()).onHover();
		}
		else if(mouse.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
			((MenuButton) mouse.getSource()).stopHover();
		}
		
	}


}
