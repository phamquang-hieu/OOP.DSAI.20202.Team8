package shapes;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ElementShape extends StackPane{
	private Rectangle rectangle;
	private Label lb;
	
	public ElementShape(int element, double X, double Y, Color c) {
    	rectangle = new Rectangle(X, Y, 40, 40);
    	rectangle.setFill(c);
    	rectangle.setStroke(c);
    	rectangle.setArcWidth(20);
    	rectangle.setArcHeight(20);

    	
    	lb = new Label(Integer.toString(element));
    	
    	this.getChildren().addAll(rectangle, lb);
    	this.setLayoutX(X);
    	this.setLayoutY(Y);
	}
	public ElementShape(String element, double X, double Y, Color c) {
		super();
		this.rectangle = new Rectangle();
		this.lb = new Label();
		this.rectangle.setWidth(40);
		this.rectangle.setHeight(40);
		this.rectangle.setFill(c);
		this.lb.setFont(new Font("Arial", 24));
		this.lb.setText(element);
		this.getChildren().addAll(rectangle, this.lb);
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
