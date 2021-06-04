package shapes;

import javafx.animation.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public abstract class ElementShape extends StackPane {
	public Rectangle rectangle;

	public TranslateTransition movingXY(double X, double Y) {
		TranslateTransition t = new TranslateTransition();
		t.setNode(this);
		t.setDuration(Duration.millis(5000));
		t.setByX(X);
		t.setByY(Y);
		return t;
	}

	public TranslateTransition movingX(double X) {
		TranslateTransition t = new TranslateTransition();
		t.setNode(this);
		t.setDuration(Duration.millis(500));
		t.setByX(X);
		return t;
	}

	public TranslateTransition movingY(double Y) {
		TranslateTransition t = new TranslateTransition();
		t.setNode(this);
		t.setDuration(Duration.millis(500));
		t.setByY(Y);
		return t;
	}

}
