package shapes;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class SquareShape extends ElementShape {
	public SquareShape(double X, double Y, Color c1, Color c2, int element) {
		setLayoutX(X);
		setLayoutY(Y);

		rectangle = new Rectangle(40, 40);
		rectangle.setFill(c1);
		rectangle.setArcHeight(10);
		rectangle.setArcWidth(10);
		getChildren().add(rectangle);

		Label label = new Label();
		label.setText(element != -1 ? Integer.toString(element) : "");
		label.setFont(new Font(14));
		label.setTextFill(c2);
		getChildren().add(label);
	}
}
