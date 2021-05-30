package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import algorithms.ShellSort;
import datastructure.Array;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import shapes.ElementShape;

public class ShellSortController extends SortScreenController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progressField.setEditable(false);
		sortedLabel.setVisible(true);
		sortedNode.setVisible(true);
		unsortedLabel.setVisible(true);
		unsortedNode.setVisible(true);
		selectingLabel.setVisible(true);
		selectingNode.setVisible(true);
	}

	private int n, numSteps, curSteps;
	ShellSort shellsort;
	private double startX;
	private double startY;
	private boolean flag;
	private double ratio;

	public void screenStart() {
		progressField.setText("Start Shell Sort!");
		drawArrayStart(shellsort.getArrState(0));
	}

	public void screenFinal() {
		progressField.setText("The array already sorted!");
		drawArrayFinal(shellsort.getArrState(numSteps - 1));
	}

	@FXML
	void buttonRunPressed(ActionEvent event) throws Exception {
		try {
			Array Arr;
			arrayDisplayArea.getChildren().clear();
			if (randomizeMode.isSelected()) {
				Arr = new Array(getLength());
			} else {
				Arr = new Array(textFieldArray.getText());
			}

			n = Arr.getLength();
			shellsort = new ShellSort(Arr.data);
			shellsort.Sort();
			numSteps = shellsort.getNumSteps();
			curSteps = 0;
			stepShow.setText("" + curSteps + "/" + numSteps);
			stepShow.setTextFill(Color.WHITE);

			int max = Arr.data[0];
			for (int i = 1; i < n; ++i)
				max = max < Arr.data[i] ? Arr.data[i] : max;
			ratio = 0 > arrayDisplayArea.getHeight() / (1.3 * max) ? 0 : arrayDisplayArea.getHeight() / (1.3 * max);

			flag = formNode.isSelected();

			startX = arrayDisplayArea.getWidth() / 2 - 25 * n;
			startY = arrayDisplayArea.getHeight() / 2;

			if (!flag)
				startY += 200;

			screenStart();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	void btnNextPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();
		if (curSteps == numSteps) {
			stepShow.setText("" + curSteps + "/" + numSteps);
			screenFinal();
			return;
		}
		stepShow.setText("" + (curSteps + 1) + "/" + numSteps);
		int[] pos = shellsort.getSteps(curSteps);
		if (shellsort.getFlags(curSteps) == 1) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Comparing elements at position "
					+ pos[0] + ", " + pos[1]);
			drawArrayAnimation(shellsort.getArrState(curSteps), pos, startX, startY, 1);

		} else if (shellsort.getFlags(curSteps) == 2) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Done!");
			drawArrayAnimation(shellsort.getArrState(curSteps), pos, startX, startY - 50, 2);
		} else if (shellsort.getFlags(curSteps) == 3) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Swap elements at position "
					+ pos[0] + ", " + pos[1]);
			drawArrayAnimation(shellsort.getArrState(curSteps - 1), pos, startX, startY - 50, 3);
		} else if (shellsort.getFlags(curSteps) == 4) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Next!");
			drawArrayStart(shellsort.getArrState(curSteps));
		}
		++curSteps;
	}

	@FXML
	void btnBackPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();
		if (curSteps == 0) {
			stepShow.setText("" + curSteps + "/" + numSteps);
			screenStart();
			return;
		}
		stepShow.setText("" + curSteps + "/" + numSteps);
		--curSteps;
		int[] pos = shellsort.getSteps(curSteps);
		if (shellsort.getFlags(curSteps) == 1) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Comparing elements at position "
					+ pos[0] + ", " + pos[1]);
			drawArrayAnimation(shellsort.getArrState(curSteps), pos, startX, startY - 50, 2);
		} else if (shellsort.getFlags(curSteps) == 2) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Done!");
			drawArrayAnimation(shellsort.getArrState(curSteps), pos, startX, startY, 1);
		} else if (shellsort.getFlags(curSteps) == 3) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Swap elements at position "
					+ pos[0] + ", " + pos[1]);
			drawArrayAnimation(shellsort.getArrState(curSteps), pos, startX, startY - 50, 3);
		} else if (shellsort.getFlags(curSteps) == 4) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Next!");
			drawArray(shellsort.getArrState(curSteps), null, Color.web("#ffbea3"), Color.BLACK);
		}
	}

	@FXML
	void btnResetPressed(ActionEvent event) {
		curSteps = 0;
		btnBackPressed(new ActionEvent());
	}

	@FXML
	void btnSkipPressed(ActionEvent event) {
		curSteps = numSteps;
		btnNextPressed(new ActionEvent());
	}

	@FXML
	void buttonAutoPressed(ActionEvent event) {
		if (curSteps == numSteps) {
			btnNextPressed(new ActionEvent());
			return;
		}
		btnNextPressed(new ActionEvent());

		Circle s = new Circle(0, 0, 3);
		arrayDisplayArea.getChildren().add(s);

		Path path = new Path();
		path.getElements().add(new MoveTo(0, 0));
		path.getElements().add(new LineTo(arrayDisplayArea.getWidth(), 0));

		PathTransition delay = new PathTransition();
		delay.setDelay(Duration.seconds(0));
		delay.setDuration(Duration.seconds(0.5));
		delay.setNode(s);
		delay.setPath(path);
		delay.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent actionEvent) {
				buttonAutoPressed(new ActionEvent());
			}
		});
		delay.play();
	}

	public ElementShape preDrawElement(int element, double X, double Y) {
		return new ElementShape(Integer.toString(element), X, Y);
	}

	public ElementShape preDrawElement(int element, double X, double Y, Color c1, Color c2) {
		return new ElementShape(Integer.toString(element), X, Y, c1, 14, c2);
	}

	public ElementShape preDrawElement(int height, Color c, double X, double Y) {
		return new ElementShape(height * ratio, c, X, Y);
	}

	public void drawArrayBottom(int[] arr) {
		for (int i = 0; i < n; i++) {
			ElementShape s;
			s = preDrawElement(arr[i], startX + i * 50, startY);
			arrayDisplayArea.getChildren().add(s);
		}
	}

	public void drawArray(int arr[], int pos[], Color c1, Color c2) {
		for (int i = 0; i < n; i++) {
			if (pos != null && (i == pos[0] || i == pos[1]))
				continue;
			ElementShape s;
			if (flag)
				s = preDrawElement(arr[i], startX + i * 50, startY, c1, c2);
			else
				s = preDrawElement(arr[i], c1, startX + i * 50, startY);
			arrayDisplayArea.getChildren().add(s);
		}
		if (!flag)
			drawArrayBottom(shellsort.getArrState(curSteps));
	}

	public void drawArrayStart(int[] arr) {
		drawArray(arr, null, Color.web("#ffbea3"), Color.BLACK);
	}

	public void drawArrayFinal(int[] arr) {
		drawArray(arr, null, Color.web("#05141a"), Color.WHITE);
	}

	public void drawArrayAnimation(int[] arr, int[] pos, double X, double Y, int type) {
		drawArray(arr, pos, Color.web("#ffbea3"), Color.BLACK);
		ElementShape[] s = new ElementShape[2];
		for (int i = 0; i < 2; ++i) {
			s[i] = flag ? preDrawElement(arr[pos[i]], X + pos[i] * 50, Y, Color.web("#ff7f50"), Color.WHITE)
					: preDrawElement(arr[pos[i]], Color.web("#ff7f50"), startX + pos[i] * 50, startY);
			arrayDisplayArea.getChildren().add(s[i]);
			if (flag && (type == 1 || type == 2))
				s[i].movingY(type == 1 ? -50 : +50).play();
			if (type == 3)
				s[i].movingX(50 * (pos[1 - i] - pos[i])).play();
		}

	}
}