package window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class MenuButton extends Rectangle {
	
	String label = "";
	Text text;
	
	//hover animation
	Timeline timeline;
	Duration duration = new Duration(0);
	boolean hover = false;
	
	public MenuButton(String labeltext, double x, double y) {
		this.label = labeltext;
		text = new Text(label);
        try { 
            Font f = Font.loadFont(new FileInputStream(new File("src/main/resources/8bitOperatorPlus8-Regular.ttf")), 32);
            text.setFont(f);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setLayoutX(x);
        text.setLayoutY(y);
        text.setFill(Color.WHITE);
        Bounds b = text.getLayoutBounds();
        // = new Rectangle(xPos - 5, yPos - b.getHeight() - 2, b.getWidth() + 10, b.getHeight() + 10);
        this.setX(x - 5);
        this.setY(y - b.getHeight() - 2);
        this.setWidth(b.getWidth() + 10);
        this.setHeight(b.getHeight() + 10);
        this.setStroke(Color.WHITE);
        this.setStrokeWidth(2);
        this.setFill(Color.TRANSPARENT);
        timeline = new Timeline(
        		new KeyFrame(new Duration(1500), new KeyValue(text.fillProperty(), Color.web("#fff6aa"))),
        		new KeyFrame(new Duration(1500), new KeyValue(this.strokeProperty(), Color.web("#fff6aa"))),
        		new KeyFrame(new Duration(3000), new KeyValue(text.fillProperty(), Color.web("#b4ffaa"))),
        		new KeyFrame(new Duration(3000), new KeyValue(this.strokeProperty(), Color.web("#b4ffaa"))),
        		new KeyFrame(new Duration(4500), new KeyValue(text.fillProperty(), Color.web("#b0aaff"))),
        		new KeyFrame(new Duration(4500), new KeyValue(this.strokeProperty(), Color.web("#b0aaff"))),
        		new KeyFrame(new Duration(6000), new KeyValue(text.fillProperty(), Color.web("#ffaafc"))),
        		new KeyFrame(new Duration(6000), new KeyValue(this.strokeProperty(), Color.web("#ffaafc"))),
        		new KeyFrame(new Duration(7500), new KeyValue(text.fillProperty(), Color.web("#ffffff"))),
        		new KeyFrame(new Duration(7500), new KeyValue(this.strokeProperty(), Color.web("#ffffff")))
        		);
        timeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	/**
	 * Draws the MenuButton directly onto the node presented in the specified location.
	 * @param node
	 */
	public void draw(Group node) {
        node.getChildren().add(text);
        node.getChildren().add(this);
        
	}
	
	/**
	 * Changes the x-coordinate of the MenuButton.
	 * @param x
	 */
	public void setNewX(double x) {
		text.setLayoutX(x);
		this.setX(x);
	}
	
	/**
	 * Changes the y-coordinate of the MenuButton.
	 * @param y
	 */
	public void setNetY(double y) {
		text.setY(y);
		this.setY(y);
	}
	
	/**
	 * Returns true if the co-ordinates are contained within the bounds of the button.
	 * Else, returns false.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean collision(double x, double y) {
		if(this.contains(new Point2D(x, y))) {
			//System.out.println("A collision!");
			return true;
		}
		else return false;
	}
	
	public void onHover() {
			timeline.playFrom(duration);
	}
	
	public void stopHover() {
			duration = timeline.currentTimeProperty().getValue();
			timeline.stop();
			text.setFill(Color.WHITE);
			this.setStroke(Color.WHITE);

	}

}
