package kuni;

import javafx.application.Application;
import javafx.stage.Stage;
import window.MainWindow;

public class Main extends Application {
	
	 public static void main(String[] args) { 
		 launch(args);
	    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainWindow m = new MainWindow(primaryStage);
	}
}
