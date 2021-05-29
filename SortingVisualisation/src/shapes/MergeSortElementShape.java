package shapes;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MergeSortElementShape extends ElementShape {
	private String instruction;
	private TextArea txt;
	private Label lb;
	int stepNum;
	int numStep;

	public MergeSortElementShape(String element, double X, double Y, Color c, int font, Color c2, String instruction,
			TextArea txt, Label lb, int stepNum, int numStep) {
		super(element, X, Y, c, font, c2);
		this.instruction = instruction;
		this.txt = txt;
		this.lb = lb;
		this.stepNum = stepNum;
		this.numStep = numStep;
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
				lb.setText((stepNum+1)+ "/" + (numStep));
			}
		});

		return fd;
	}
}
