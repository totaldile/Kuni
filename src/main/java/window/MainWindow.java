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
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import kuni.MenuController;
import logic.Kuni;

public class MainWindow {
	
	//private Kuni kuni;
	private MenuController menuController;
	
	private Stage stage;
	private MenuButton newGameButton;
	private MenuButton quitButton;
	private MenuButton optionsButton;
	
	public MainWindow(Stage primaryStage, Kuni game) throws Exception {
		//this.kuni = game;
		this.menuController = new MenuController(game, this);
		this.stage = primaryStage;
		stage.setResizable(false);
		newGameButton = new MenuButton("eh", 10, 10);
		quitButton = new MenuButton("quit", 10, 10);
		optionsButton = new MenuButton("op", 10, 10);
		playLoadingSequence();
		//System.setProperty("prism.lcdtext", "false"); //may or may not improve crispness
		//System.setProperty("prism.text", "t2k"); //map or may not improve crispness
	}
	
	/**
	 * Draws the main menu. 
	 * This occurs after the loading sequence.
	 */
	public void initiateMainMenu() {
		Group root = new Group();
		Scene scene = new Scene(root, 800, 600, Color.BLACK);
		
        stage.setScene(scene);
        
        Rectangle background = new Rectangle(scene.getWidth(), scene.getHeight(),
        		new LinearGradient(scene.getWidth() / 200, 0, scene.getWidth() / 200, scene.getHeight()/100, true, CycleMethod.NO_CYCLE, new Stop[] {
        				new Stop(0, Color.web("#000000")),
        	            new Stop(0.85, Color.web("#001b47"))
        		}));
        
        background.widthProperty().bind(scene.widthProperty());
        background.heightProperty().bind(scene.heightProperty());
        root.getChildren().add(background);
        
		newGameButton = new MenuButton("new game", 50, 300);
		newGameButton.setNewX((scene.getWidth()/2) - (newGameButton.getWidth()/2));
		newGameButton.draw(root);
		setOnMouse(newGameButton);
		
		optionsButton = new MenuButton("options", 50, 300 + 10 + newGameButton.getHeight());
		optionsButton.setNewX((scene.getWidth()/2) - (optionsButton.getWidth()/2));
		optionsButton.draw(root);
		setOnMouse(optionsButton);
		
		quitButton = new MenuButton("quit", 50, 300 + 10 + newGameButton.getHeight() + 10 + optionsButton.getHeight());
		quitButton.setNewX((scene.getWidth()/2) - (quitButton.getWidth()/2));
		quitButton.draw(root);
		setOnMouse(quitButton);
		

		
		//set-up music
		try {
			String mp3 = this.getClass().getResource("/Shadowlands-purple-planet-dot-com.mp3").toURI().toString();
			AudioClip mp = new AudioClip(new Media(mp3).getSource());
			mp.play();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		stage.show();
	}
	
	/**
	 * Helper method to reduce code repetitiveness.
	 * Assigns menuController for MouseEntered, MouseExited, and MouseClicked
	 * @param b
	 */
	public void setOnMouse(MenuButton b) {
		b.setOnMouseEntered(menuController);
		b.setOnMouseExited(menuController);
		b.setOnMouseClicked(menuController);
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
	
	/**
	 * Returns the New Game button.
	 * @return
	 */
	public MenuButton getNewGameButton() {
		return newGameButton;
	}
	
	/**
	 * Returns the Quit button.
	 * @return
	 */
	public MenuButton getQuitButton() {
		return quitButton;
	}
	
	/**
	 * Returns the Options button.
	 * @return
	 */
	public MenuButton getOptionsButton() {
		return optionsButton;
	}
	
	/**
	 * Returns an array of all the menu-screen buttons.
	 * @return
	 */
	public MenuButton[] getButtons() {
		MenuButton[] m = {newGameButton, optionsButton, quitButton};
		return m;
	}

}
