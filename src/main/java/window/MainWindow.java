package window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainWindow {
	
	Stage stage;
	
	public MainWindow(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		playLoadingSequence();
		System.setProperty("prism.lcdtext", "false"); //may or may not improve crispness
		System.setProperty("prism.text", "t2k"); //map or may not improve crispness
		//main menu now loads after the animation; next goal is animated menu buttons
		//css styles are probably irrelevant now - easier to do them in the code
		//playOpeningSequence();
	}
	
	/**
	 * Draws the main menu. 
	 * This occurs after the loading sequence.
	 */
	public void initiateMainMenu() {
		Group root = new Group();
		Scene scene = new Scene(root, 800, 600, Color.BLACK);
        try {
			scene.getStylesheets().add(this.getClass().getResource("/intro.css").toURI().toString());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
        stage.setScene(scene);
		MenuButton m = new MenuButton("new game", 50, 50);
		m.draw(root);
		stage.show();
	}
	
	/**
	 * Plays the sequence that occurs before the main menu is loaded.
	 */
	public void playLoadingSequence() {
		Group root = new Group();
		Scene scene = new Scene(root, 800, 600, Color.BLACK);
        try {
			scene.getStylesheets().add(this.getClass().getResource("/intro.css").toURI().toString());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
        stage.setScene(scene);
        Text text = new Text("Miki-chan Studios");
        try { 
            Font f = Font.loadFont(new FileInputStream(new File("src/main/resources/8bitOperatorPlus8-Regular.ttf")), 32);
            text.setFont(f);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.getStyleClass().add("loading-text"); 
        text.setLayoutX(0);
        text.setLayoutY(300 - text.getLayoutBounds().getHeight()/2);
        Bounds b = text.getLayoutBounds();
        root.getChildren().add(text);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
        		new KeyFrame(Duration.ZERO, new KeyValue(text.translateXProperty(), 0 - b.getWidth())),
        		new KeyFrame(new Duration(2000), new KeyValue(text.translateXProperty(), (400 - (b.getWidth()/2)))),
        		new KeyFrame(new Duration(3000), new KeyValue(text.translateXProperty(), (400 - (b.getWidth()/2)))),
        		new KeyFrame(new Duration(5000), new KeyValue(text.translateXProperty(), 800)));
        timeline.setOnFinished(new EventHandler<ActionEvent>() {

            /**
             * Transitions to the main menu after the animation has finished executing.
             */
        	public void handle(ActionEvent event) {
                initiateMainMenu();
            }
        });
        timeline.play();
        stage.show();
	}
	
	/**
	 * Plays the opening sequence of the game.
	 * Prompted by clicking "new game" from the main menu.
	 */
	public void playOpeningSequence() {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        
        try {
			scene.getStylesheets().add(this.getClass().getResource("/intro.css").toURI().toString());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
        stage.setScene(scene);
        
        Text text = new Text("Hello? Can you hear me?");
        try { 
            Font f = Font.loadFont(new FileInputStream(new File("src/main/resources/8bitOperatorPlus8-Regular.ttf")), 24);
            text.setFont(f);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setLayoutX(400 - (text.getLayoutBounds().getWidth()/2));
        text.setLayoutY(300 - (text.getLayoutBounds().getHeight()/2));
        text.getStyleClass().add("intro-text");       
        root.getChildren().add(text);
        
        Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, 
                    new KeyValue(text.opacityProperty(), 0)
                ),
                new KeyFrame(new Duration(1000), 
                    new KeyValue(text.opacityProperty(), 1)
            ),
                new KeyFrame(new Duration(3500),
                        new KeyValue(text.opacityProperty(), 1)
                ),
                new KeyFrame(new Duration(5000),
                		new KeyValue(text.opacityProperty(), 0)));
        timeline.play();
        
        stage.show();
	}

}
