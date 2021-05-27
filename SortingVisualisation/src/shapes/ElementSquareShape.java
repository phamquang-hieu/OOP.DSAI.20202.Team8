package shapes;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ElementSquareShape extends StackPane{
	Rectangle rect;
	Label element;
	public ElementSquareShape(String value, double X, double Y, Color c) {
		super();
		this.rect = new Rectangle();
		this.element = new Label();
		this.rect.setWidth(40);
		this.rect.setHeight(40);
		this.rect.setFill(c);
		this.element.setFont(new Font("Arial", 24));
		this.element.setText(value);
		this.getChildren().addAll(rect, this.element);
		this.setLayoutX(X);
		this.setLayoutY(Y);
	}
}
