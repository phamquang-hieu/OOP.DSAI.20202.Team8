package shapes;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class BarShape extends ElementShape {
	public BarShape(double X, double Y, double height, Color c) {
		setLayoutX(X);
		setLayoutY(Y);

		rectangle = new Rectangle(X, Y, 40, height);
		rectangle.setArcHeight(10);
		rectangle.setArcWidth(10);
		rectangle.setFill(c);
		getChildren().add(rectangle);
	}

	public BarShape(double X, double Y, double height, Color c1, Color c2, int element) {
		this(X, Y, height, c1);

		Label label = new Label(Integer.toString(element));
		label.setFont(new Font(14));
		label.setTextFill(c2);
		getChildren().add(label);

	}
	
	public BarShape(double height, Color c, double X, double Y) {
		rectangle = new Rectangle(X, Y - height, 40, height);
		rectangle.setFill(c);
		rectangle.setStroke(Color.WHITE);
		rectangle.setArcWidth(10);
		rectangle.setArcHeight(10);

		this.getChildren().addAll(rectangle);
		this.setLayoutX(X);
		this.setLayoutY(Y - height);
	}
}
