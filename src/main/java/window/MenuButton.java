package window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MenuButton {
	
	String label = "";
	double xPos = 0;
	double yPos = 0;
	
	public MenuButton(String text, double x, double y) {
		this.label = text;
		this.xPos = x;
		this.yPos = y;
	}
	
	/**
	 * Draws the MenuButton directly onto the node presented in the specified location.
	 * @param node
	 */
	public void draw(Group node) {
		Text text = new Text(label);
        try { 
            Font f = Font.loadFont(new FileInputStream(new File("src/main/resources/8bitOperatorPlus8-Regular.ttf")), 32);
            text.setFont(f);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setLayoutX(xPos);
        text.setLayoutY(yPos);
        text.setFill(Color.WHITE);
        node.getChildren().add(text);
        Bounds b = text.getLayoutBounds();
        Rectangle rec = new Rectangle(xPos - 5, yPos - b.getHeight() - 2, b.getWidth() + 10, b.getHeight() + 10);
        rec.setStroke(Color.WHITE);
        rec.setStrokeWidth(2);
        rec.setFill(Color.TRANSPARENT);
        node.getChildren().add(rec);
        
	}

}
