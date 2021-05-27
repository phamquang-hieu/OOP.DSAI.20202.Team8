package shapes;

import javax.swing.text.StyleConstants.FontConstants;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ElementShape extends StackPane{
	private Rectangle rectangle;
	private Label lb;
	
	public ElementShape(String element, double X, double Y, Color c) {
    	rectangle = new Rectangle(X, Y, 40, 40);
    	rectangle.setFill(c);
    	rectangle.setStroke(c);
    	rectangle.setArcWidth(20);
    	rectangle.setArcHeight(20);

    	
    	lb = new Label(element);
    	lb.setFont(new Font(21));
    	this.getChildren().addAll(rectangle, lb);
    	this.setLayoutX(X);
    	this.setLayoutY(Y);
	}

	
	public ElementShape(int height, Color c, double X, double Y) {
    	Rectangle rectangle = new Rectangle(X, Y-height, 20, height);
    	rectangle.setFill(c);
    	rectangle.setStroke(c);
    	rectangle.setArcWidth(5);
    	rectangle.setArcHeight(5);
    	
    	this.getChildren().addAll(rectangle);
    	this.setLayoutX(X);
    	this.setLayoutY(Y-height);
	}
}
