package shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ElementBarShape extends Rectangle {
	public ElementBarShape(double height, double X, double Y, Color c) {
		super(X, Y-height, 20, height);
		this.setFill(c);
    	this.setStroke(c);
    	this.setArcWidth(5);
    	this.setArcHeight(5);

    	this.setLayoutX(X);
    	this.setLayoutX(Y);
		
	}
}
