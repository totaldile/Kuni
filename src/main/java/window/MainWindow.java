package window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
		//need to figure out how to get these to play sequentially - onfinish, or something? also look into sequentialtransition, another good class
		//https://stackoverflow.com/questions/11188018/how-to-wait-for-a-transition-to-end-in-javafx-2-1
		playOpeningSequence();
	}
	
	public void playLoadingSequence() {
		Group root = new Group();
		Scene scene = new Scene(root, 800, 600, Color.BLUE);
        try {
			scene.getStylesheets().add(this.getClass().getResource("/intro.css").toURI().toString());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
        stage.setScene(scene);
        Text text = new Text("Miki-chan Studios");
        try { 
            Font f = Font.loadFont(new FileInputStream(new File("src/main/resources/8bitOperatorPlus8-Regular.ttf")), 24);
            text.setFont(f);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setLayoutX(0);
        text.setLayoutY(400);
        text.getStyleClass().add("loading-text"); 
        root.getChildren().add(text);
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
        		new KeyFrame(Duration.ZERO, new KeyValue(text.translateXProperty(), 0)),
        		new KeyFrame(new Duration(2000), new KeyValue(text.translateXProperty(), (400 - (text.getLayoutBounds().getWidth()/2)))),
        		new KeyFrame(new Duration(3000), new KeyValue(text.translateXProperty(), (400 - (text.getLayoutBounds().getWidth()/2)))),
        		new KeyFrame(new Duration(5000), new KeyValue(text.translateXProperty(), 800)));
        timeline.play();
        stage.show();
        		
	}
	
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
