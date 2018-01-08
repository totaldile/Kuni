package kuni;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        
        //scene.getStylesheets().add("src/main/resources/intro.css");
        //String s = this.getClass().getResource("/intro.css").toURI().toString();
        scene.getStylesheets().add(this.getClass().getResource("/intro.css").toURI().toString());
        primaryStage.setScene(scene);
        
        Text text = new Text("Hello? Can you hear me?");
        try { 
            // load a custom font from a specific location (change path!)
            // 12 is the size to use
            Font f = Font.loadFont(new FileInputStream(new File("src/main/resources/8bitOperatorPlus8-Regular.ttf")), 24);
            text.setFont(f); // use this font with our label
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setLayoutX(400 - (text.getLayoutBounds().getWidth()/2));
        //text.setLayoutX(0);
        text.setLayoutY(300 - (text.getLayoutBounds().getHeight()/2));
        text.getStyleClass().add("intro-text");       
        root.getChildren().add(text);
        //Bounds b = text.getBoundsInParent();
        //Rectangle rect = new Rectangle(0, 0, b.getWidth(), b.getHeight());
        //rect.setStrokeType(StrokeType.OUTSIDE);
        //rect.setStroke(Color.web("blue", 1));
        //rect.setStrokeWidth(1);
        //root.getChildren().add(rect);
        
        Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0
                    new KeyValue(text.opacityProperty(), 0)
                ),
                new KeyFrame(new Duration(1000), // set end position at 40s
                    new KeyValue(text.opacityProperty(), 1)
            ),
                new KeyFrame(new Duration(3500), // set end position at 40s
                        new KeyValue(text.opacityProperty(), 1)
                ),
                new KeyFrame(new Duration(5000),
                		new KeyValue(text.opacityProperty(), 0)));
        // play 40s of animation
        timeline.play();
        
        primaryStage.show();
	}
	
	 public static void main(String[] args) {
	        launch(args);
	    }
}
