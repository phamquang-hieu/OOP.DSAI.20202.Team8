package shapes;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class SquareShape extends ElementShape {
	public SquareShape(double X, double Y, Color c1, Color c2, int element) {
		setLayoutX(X);
		setLayoutY(Y);

		Rectangle rectangle = new Rectangle(X, Y, 40, 40);
		rectangle.setFill(c1);
		rectangle.setArcHeight(10);
		rectangle.setArcWidth(10);
		getChildren().add(rectangle);

		Label label = new Label();
		label.setText(element != -1 ? Integer.toString(element): "");
		label.setFont(new Font(14));
		label.setTextFill(c2);
		getChildren().add(label);
	}
	
	public SquareShape(String element, double X, double Y, Color c, int font, Color c2) {
		rectangle = new Rectangle(X, Y, 40, 40);
		rectangle.setFill(c);
		rectangle.setStroke(c);
		rectangle.setArcWidth(20);
		rectangle.setArcHeight(20);

		Label lb = new Label(element);
		lb.setFont(new Font(font));
		lb.setTextFill(c2);

		this.getChildren().addAll(rectangle, lb);
		this.setLayoutX(X);
		this.setLayoutY(Y);
	}

}
