package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import algorithms.ShellSort;
import datastructure.Array;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
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

			flag = formNode.isSelected();

			startX = arrayDisplayArea.getWidth() / 2 - 25 * n;
			startY = arrayDisplayArea.getHeight() / 2;

			screenStart();
		} catch (NullPointerException e) {
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
			drawArrayAnimation1(shellsort.getArrState(curSteps), pos);

		} else if (shellsort.getFlags(curSteps) == 2) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Next!");
			drawArrayAnimation2(shellsort.getArrState(curSteps), pos);
		} else if (shellsort.getFlags(curSteps) == 3) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Swap elements at position "
					+ pos[0] + ", " + pos[1]);
			drawArrayAnimation3(shellsort.getArrState(curSteps - 1), pos);
		}
		if (!flag)
			for (int i = 0; i < n; i++) {
				ElementShape s;
				s = preDrawElement(shellsort.getArrState(curSteps)[i], startX + i * 50, startY + 100);
				arrayDisplayArea.getChildren().add(s);
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
			drawArrayAnimation2(shellsort.getArrState(curSteps), pos);
		} else if (shellsort.getFlags(curSteps) == 2) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Next!");
			drawArrayAnimation1(shellsort.getArrState(curSteps), pos);
		} else if (shellsort.getFlags(curSteps) == 3) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Swap elements at position "
					+ pos[0] + ", " + pos[1]);
			drawArrayAnimation3(shellsort.getArrState(curSteps), pos);
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
		return new ElementShape(height * 2, c, X, Y);
	}

	public void drawArrayStart(int[] arr) {
		for (int i = 0; i < n; i++) {
			ElementShape s;
			if (flag)
				s = preDrawElement(arr[i], startX + i * 50, startY, Color.web("#ffbea3"), Color.BLACK);
			else
				s = preDrawElement(arr[i], Color.web("#ffbea3"), startX + i * 50, startY + 100);
			arrayDisplayArea.getChildren().add(s);
		}
		if (!flag)
			for (int i = 0; i < n; i++) {
				ElementShape s;
				s = preDrawElement(shellsort.getArrState(curSteps)[i], startX + i * 50, startY + 100);
				arrayDisplayArea.getChildren().add(s);
			}
	}

	public void drawArrayFinal(int[] arr) {
		for (int i = 0; i < n; i++) {
			ElementShape s;
			if (flag)
				s = preDrawElement(arr[i], startX + i * 50, startY, Color.web("#05141a"), Color.WHITE);
			else
				s = preDrawElement(arr[i], Color.web("#05141a"), startX + i * 50, startY + 100);
			arrayDisplayArea.getChildren().add(s);
		}
		if (!flag)
			for (int i = 0; i < n; i++) {
				ElementShape s;
				s = preDrawElement(shellsort.getArrState(curSteps)[i], startX + i * 50, startY + 100);
				arrayDisplayArea.getChildren().add(s);
			}
	}

	public void drawArrayAnimation1(int[] arr, int[] pos) {
		for (int i = 0; i < n; i++) {
			if (i == pos[0] || i == pos[1])
				continue;
			ElementShape s;
			if (flag)
				s = preDrawElement(arr[i], startX + i * 50, startY, Color.web("#ffbea3"), Color.BLACK);
			else
				s = preDrawElement(arr[i], Color.web("#ffbea3"), startX + i * 50, startY + 100);
			arrayDisplayArea.getChildren().add(s);
		}
		ElementShape s0, s1;
		if (flag) {
			s0 = preDrawElement(arr[pos[0]], startX + pos[0] * 50, startY, Color.web("#ff7f50"), Color.WHITE);
			s1 = preDrawElement(arr[pos[1]], startX + pos[1] * 50, startY, Color.web("#ff7f50"), Color.WHITE);
		} else {
			s0 = preDrawElement(arr[pos[0]], Color.web("#ff7f50"), startX + pos[0] * 50, startY + 100);
			s1 = preDrawElement(arr[pos[1]], Color.web("#ff7f50"), startX + pos[1] * 50, startY + 100);
		}
		int bonusY = flag ? -50 : 0;
		arrayDisplayArea.getChildren().add(s0);
		arrayDisplayArea.getChildren().add(s1);
		TranslateTransition t0 = s0.movingY(bonusY);
		t0.play();
		TranslateTransition t1 = s1.movingY(bonusY);
		t1.play();
	}

	public void drawArrayAnimation2(int[] arr, int[] pos) {
		for (int i = 0; i < n; i++) {
			if (i == pos[0] || i == pos[1])
				continue;
			ElementShape s;
			if (flag)
				s = preDrawElement(arr[i], startX + i * 50, startY, Color.web("#ffbea3"), Color.BLACK);
			else
				s = preDrawElement(arr[i], Color.web("#ffbea3"), startX + i * 50, startY + 100);
			arrayDisplayArea.getChildren().add(s);
		}
		ElementShape s0, s1;
		if (flag) {
			s0 = preDrawElement(arr[pos[0]], startX + pos[0] * 50, startY - 50, Color.web("#ff7f50"), Color.WHITE);
			s1 = preDrawElement(arr[pos[1]], startX + pos[1] * 50, startY - 50, Color.web("#ff7f50"), Color.WHITE);
		} else {
			s0 = preDrawElement(arr[pos[0]], Color.web("#ff7f50"), startX + pos[0] * 50, startY + 100);
			s1 = preDrawElement(arr[pos[1]], Color.web("#ff7f50"), startX + pos[1] * 50, startY + 100);
		}
		int bonusY = flag ? +50 : 0;
		arrayDisplayArea.getChildren().add(s0);
		arrayDisplayArea.getChildren().add(s1);
		TranslateTransition t0 = s0.movingY(bonusY);
		t0.play();
		TranslateTransition t1 = s1.movingY(bonusY);
		t1.play();
	}

	public void drawArrayAnimation3(int[] arr, int[] pos) {
		for (int i = 0; i < n; i++) {
			if (i == pos[0] || i == pos[1])
				continue;
			ElementShape s;
			if (flag)
				s = preDrawElement(arr[i], startX + i * 50, startY, Color.web("#ffbea3"), Color.BLACK);
			else
				s = preDrawElement(arr[i], Color.web("#ffbea3"), startX + i * 50, startY + 100);
			arrayDisplayArea.getChildren().add(s);
		}
		ElementShape s0, s1;
		if (flag) {
			s0 = preDrawElement(arr[pos[0]], startX + pos[0] * 50, startY - 50, Color.web("#ff7f50"), Color.WHITE);
			s1 = preDrawElement(arr[pos[1]], startX + pos[1] * 50, startY - 50, Color.web("#ff7f50"), Color.WHITE);
		} else {
			s0 = preDrawElement(arr[pos[0]], Color.web("#ff7f50"), startX + pos[0] * 50, startY + 100);
			s1 = preDrawElement(arr[pos[1]], Color.web("#ff7f50"), startX + pos[1] * 50, startY + 100);
		}
		arrayDisplayArea.getChildren().add(s0);
		arrayDisplayArea.getChildren().add(s1);
		TranslateTransition t0 = s0.movingX(50 * (pos[1] - pos[0]));
		t0.play();
		TranslateTransition t1 = s1.movingX(50 * (pos[0] - pos[1]));
		t1.play();
	}
}