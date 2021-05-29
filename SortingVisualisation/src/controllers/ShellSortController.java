package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import algorithms.ShellSort;
import datastructure.Array;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import shapes.ElementShape;

public class ShellSortController extends SortScreenController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		boolean tmp = true;
		formBar.setVisible(!tmp);
		sortedLabel.setVisible(tmp);
		unsortedLabel.setVisible(tmp);
		sortedNode.setVisible(tmp);
		unsortedNode.setVisible(tmp);
		sortedName.setVisible(!tmp);
		unsortedName.setVisible(!tmp);
		smallestName.setVisible(!tmp);
		currentName.setVisible(!tmp);
		sortedBar.setVisible(!tmp);
		unsortedBar.setVisible(!tmp);
		smallestBar.setVisible(!tmp);
		currentBar.setVisible(!tmp);

	}

	private int n, numSteps, curSteps;
	ShellSort shellsort;
	private double X;
	private double Y;
	private double startX;
	private double startY;

	public void screenInit() {
		progressField.setText("Start Shell Sort!");
		drawArray(shellsort.getArrState(0));
	}

	void init(Array Arr) {
		n = Arr.getLength();
		shellsort = new ShellSort(Arr.data);
		shellsort.Sort();
		numSteps = shellsort.getNumSteps();
		curSteps = 0;
		X = arrayDisplayArea.getWidth() / 2;
		Y = arrayDisplayArea.getHeight() / 2;
		startX = X - 25 * n;
		startY = Y;
	}

	@FXML
	void buttonRunPressed(ActionEvent event) throws Exception {
		progressField.setEditable(false);
		try {
			Array Arr;
			arrayDisplayArea.getChildren().clear();
			if (randomizeMode.isSelected()) {
				Arr = new Array(getLength());
			} else {
				Arr = new Array(textFieldArray.getText());
			}
			init(Arr);
			screenInit();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	void btnNextPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();
		if (curSteps == numSteps) {
			drawArrayFinal(shellsort.getArrState(numSteps - 1));
			progressField.setText("The array already sorted!");
			return;
		}
		int[] pos = shellsort.getSteps(curSteps);
		if (shellsort.getFlags(curSteps) == 1) {
			drawArray(shellsort.getArrState(curSteps), pos);
			progressField.setText("Steps: " + curSteps + "." + " Increment size = " + (pos[1] - pos[0]) + ".\n"
					+ "Comparing elements at position " + pos[0] + ", " + pos[1]);
		} else if (shellsort.getFlags(curSteps) == 2) {
			drawArray(shellsort.getArrState(curSteps));
			progressField
					.setText("Steps: " + curSteps + "." + " Increment size = " + (pos[1] - pos[0]) + ".\n" + "Next!");
		} else {
			drawArrayAnimation(shellsort.getArrState(curSteps), pos);
			progressField.setText("Steps: " + curSteps + "." + " Increment size = " + (pos[1] - pos[0]) + ".\n"
					+ "Swap elements at position " + pos[0] + ", " + pos[1]);
		}
		++curSteps;
	}

	@FXML
	void btnBackPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();
		if (curSteps == 0) {
			progressField.setText("Start Shell Sort!");
			drawArray(shellsort.getArrState(0));
			return;
		}
		--curSteps;
		int[] pos = shellsort.getSteps(curSteps);
		if (shellsort.getFlags(curSteps) == 1) {
			drawArray(shellsort.getArrState(curSteps), pos);
			progressField.setText("Steps: " + curSteps + "." + " Increment size = " + (pos[1] - pos[0]) + ".\n"
					+ "Comparing elements at position " + pos[0] + ", " + pos[1]);
		} else if (shellsort.getFlags(curSteps) == 2) {
			drawArray(shellsort.getArrState(curSteps));
			progressField
					.setText("Steps: " + curSteps + "." + " Increment size = " + (pos[1] - pos[0]) + ".\n" + "Next!");
		} else {
			drawArrayAnimation(shellsort.getArrState(curSteps - 1), pos);
			progressField.setText("Steps: " + curSteps + "." + " Increment size = " + (pos[1] - pos[0]) + ".\n"
					+ "Swap elements at position " + pos[0] + ", " + pos[1]);
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
	void buttonAutoPressed(ActionEvent event) throws InterruptedException {
	}

	public ElementShape preDrawElement(int element, double X, double Y, Color c1, Color c2) {
		return new ElementShape(Integer.toString(element), X, Y, c1, 14, c2);
	}

	public void drawElement(int element, double X, double Y, Color c1, Color c2) {
		ElementShape stack = new ElementShape(Integer.toString(element), X, Y, c1, 14, c2);
		arrayDisplayArea.getChildren().add(stack);
	}

	public void drawArrayFinal(int[] arr) {
		for (int i = 0; i < n; i++)
			drawElement(arr[i], startX + i * 50, startY, Color.web("#05141a"), Color.WHITE);
	}

	public void drawArray(int[] arr) {
		for (int i = 0; i < n; i++)
			drawElement(arr[i], startX + i * 50, startY, Color.web("#ffbea3"), Color.BLACK);
	}

	public void drawArray(int[] arr, int[] pos) {
		for (int i = 0; i < n; i++) {
			if (i == pos[0] || i == pos[1])
				continue;
			drawElement(arr[i], startX + i * 50, startY, Color.web("#ffbea3"), Color.BLACK);
		}
		for (int i : pos)
			drawElement(arr[i], startX + i * 50, startY - 50, Color.web("#ff7f50"), Color.WHITE);
	}

	private static PathTransition newPathTransitionTo(ElementShape block, double toX, double toY) {
		double fromX = block.getLayoutBounds().getWidth() / 2;
		double fromY = block.getLayoutBounds().getHeight() / 2;
		toX -= block.getLayoutX() - block.getLayoutBounds().getWidth() / 2;
		toY -= block.getLayoutY() - block.getLayoutBounds().getHeight() / 2;

		Path path = new Path();
		path.getElements().add(new MoveTo(fromX + 20, fromY + 20));
		path.getElements().add(new LineTo(toX + 20, toY + 20));

		PathTransition transition = new PathTransition();
		transition.setPath(path);
		transition.setNode(block);
		transition.setDelay(Duration.seconds(0));
		transition.setDuration(Duration.seconds(0.3));
		return transition;
	}

	public void drawArrayAnimation(int[] arr, int[] pos) {
		for (int i = 0; i < n; i++) {
			if (i == pos[0] || i == pos[1])
				continue;
			drawElement(arr[i], startX + i * 50, startY, Color.web("#ffbea3"), Color.BLACK);
		}
		ElementShape s0 = preDrawElement(arr[pos[1]], startX + pos[0] * 50, startY - 50, Color.web("#ff7f50"),
				Color.WHITE);
		ElementShape s1 = preDrawElement(arr[pos[0]], startX + pos[1] * 50, startY - 50, Color.web("#ff7f50"),
				Color.WHITE);
		arrayDisplayArea.getChildren().add(s0);
		arrayDisplayArea.getChildren().add(s1);
		PathTransition transition01, transition10;
		transition01 = newPathTransitionTo(s0, s1.getLayoutX(), s1.getLayoutY());
		transition10 = newPathTransitionTo(s1, s0.getLayoutX(), s0.getLayoutY());
		transition01.play();
		transition10.play();
	}
}