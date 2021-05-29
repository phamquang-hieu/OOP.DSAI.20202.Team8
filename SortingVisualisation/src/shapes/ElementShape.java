package shapes;


import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ElementShape extends StackPane{
	private Rectangle rectangle;
	private Label lb;
	
	
	
	public Rectangle getRectangle() {
		return rectangle;
	}


	public ElementShape(String element, double X, double Y, Color c, int font, Color c2) {
    	rectangle = new Rectangle(X, Y, 40, 40);
    	rectangle.setFill(c);
    	rectangle.setStroke(c);
    	rectangle.setArcWidth(20);
    	rectangle.setArcHeight(20);


    	
    	lb = new Label(element);
    	lb.setFont(new Font(font));
    	lb.setTextFill(c2);

    	this.getChildren().addAll(rectangle, lb);
    	this.setLayoutX(X);
    	this.setLayoutY(Y);
    	
	}

	
	public ElementShape(int height, Color c, double X, double Y) {
    	rectangle = new Rectangle(X, Y-height, 20, height);
    	rectangle.setFill(c);
    	rectangle.setStroke(Color.WHITE);
    	rectangle.setArcWidth(5);
    	rectangle.setArcHeight(5);
    	
    	this.getChildren().addAll(rectangle);
    	this.setLayoutX(X);
    	this.setLayoutY(Y-height);

    	}
	
	public TranslateTransition movingX(double X) {
	    TranslateTransition t = new TranslateTransition();
	    t.setNode(this);
	    t.setDuration(Duration.millis(500));
	    t.setByX(X);

	    return t; 
	}
	
	public TranslateTransition movingY(int Y) {
	    TranslateTransition t = new TranslateTransition();
	    t.setNode(this);
	    t.setDuration(Duration.millis(500));
	    t.setByY(Y);

	    return t; 
	}

}
