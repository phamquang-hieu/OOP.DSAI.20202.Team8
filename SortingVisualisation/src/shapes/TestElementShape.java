package shapes;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class TestElementShape extends StackPane {
	private Rectangle rectangle;
	private Label lb;
	private String instruction;
	private TextArea txt;
	
	public TestElementShape(String element, double X, double Y, Color c, int font, Color c2, String instruction, TextArea txt) {
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
    	this.instruction = instruction;
    	this.txt = txt;
    	
	}
	
	public FadeTransition display() {
		FadeTransition fd = new FadeTransition();
    	fd.setDuration(Duration.millis(500));
    	fd.setFromValue(0);
    	fd.setToValue(10);
    	fd.setCycleCount(1);
    	fd.setAutoReverse(false);
    	fd.setNode(this);
    	fd.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent actionEvent) {
                setVisible(true);
                txt.setText(instruction);
            }
        });
    	
    	return fd;
	}
}
