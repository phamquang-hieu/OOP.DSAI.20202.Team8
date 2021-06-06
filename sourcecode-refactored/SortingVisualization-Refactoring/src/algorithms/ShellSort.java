package algorithms;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import shapes.ElementShape;
import shapes.*;

public class ShellSort extends SortAlgorithm {
	private double startX, startY, ratio, max;
	private int[][] steps, arrState;
	private int[] flags;
	private final Color[] sortedColor = { Color.web("#05141a"), Color.WHITE };
	private final Color[] unsortedColor = { Color.web("#ffbea3"), Color.BLACK };
	private final Color[] selectingColor = { Color.web("#ff7f50"), Color.WHITE };
	private final Color[] bottomColor = { Color.WHITE, Color.BLACK };

	private void addState(int i, int j, int flag) {
		steps[numSteps][0] = i;
		steps[numSteps][1] = j;
		arrState[numSteps] = arr.clone();
		flags[numSteps] = flag;
		++numSteps;
	}

	public ShellSort(int[] inputArr, Pane inputPane, TextArea inputProgressField, String displayType) {
		super(inputArr, inputPane, inputProgressField, displayType);

		startX = pane.getWidth() / 2 - 25 * n;
		startY = displayType == "Node" ? pane.getHeight() / 2 : pane.getHeight() / 2 + 200;
		max = 0;
		for (int i = 1; i < n; ++i)
			max = max < inputArr[i] ? inputArr[i] : max;
		ratio = 0 > pane.getHeight() / (1.3 * max) ? 0 : pane.getHeight() / (1.3 * max);

		steps = new int[4 * n * n][2];
		arrState = new int[4 * n * n][n];
		flags = new int[4 * n * n];
		curSteps = numSteps = 0;
		for (int gap = n / 2; gap > 0; gap /= 2)
			for (int pos = gap; pos < n; ++pos) {
				int j = pos;
				while (j >= gap) {
					int i = j - gap;
					addState(i, j, 1);
					if (arr[i] <= arr[j]) {
						addState(i, j, 2);
						addState(i, j, 4);
						break;
					}
					swap(i, j);
					addState(i, j, 3);
					addState(i, j, 2);
					j -= gap;
				}
			}
		arrState[numSteps] = arr.clone();
	}

	@Override
	public void displayStartScreen() {
		curSteps = 0;
		progressField.setText("Start Shell Sort!");
		drawArray(arrState[0], null, unsortedColor);
	}

	@Override
	public void nextStep() {
		if (curSteps == numSteps) {
			displayFinishScreen();
			return;
		}
		int[] pos = steps[curSteps];
		if (flags[curSteps] == 1) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Comparing elements at position "
					+ pos[0] + ", " + pos[1]);
			drawArrayAnimation(arrState[curSteps], pos, startX, startY, 1);
		} else if (flags[curSteps] == 2) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Done!");
			drawArrayAnimation(arrState[curSteps], pos, startX, startY - 50, 2);
		} else if (flags[curSteps] == 3) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Swap elements at position "
					+ pos[0] + ", " + pos[1]);
			drawArrayAnimation(arrState[curSteps - 1], pos, startX, startY - 50, 3);
		} else if (flags[curSteps] == 4) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Next!");
			drawArray(arrState[curSteps], null, unsortedColor);
		}
		++curSteps;

	}

	@Override
	public void previousStep() {
		if (curSteps == 0) {
			displayStartScreen();
			return;
		}
		--curSteps;
		int[] pos = steps[curSteps];
		if (flags[curSteps] == 1) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Comparing elements at position "
					+ pos[0] + ", " + pos[1]);
			drawArrayAnimation(arrState[curSteps], pos, startX, startY - 50, 2);
		} else if (flags[curSteps] == 2) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Done!");
			drawArrayAnimation(arrState[curSteps], pos, startX, startY, 1);
		} else if (flags[curSteps] == 3) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Swap elements at position "
					+ pos[0] + ", " + pos[1]);
			drawArrayAnimation(arrState[curSteps], pos, startX, startY - 50, 3);
		} else if (flags[curSteps] == 4) {
			progressField.setText("Increment size = " + (pos[1] - pos[0]) + ".\n" + "Next!");
			drawArray(arrState[curSteps], null, unsortedColor);
		}
	}

	@Override
	public void displayFinishScreen() {
		curSteps = numSteps;
		progressField.setText("The array already sorted!");
		drawArray(arrState[numSteps - 1], null, sortedColor);
	}

	public void drawBottom(int arr[]) {
		for (int i = 0; i < n; ++i)
			pane.getChildren().add(new BarShape(startX + i * 50, startY, 20, bottomColor[0], bottomColor[1], arr[i]));
	}

	public void drawArray(int arr[], int pos[], Color[] c) {
		pane.getChildren().clear();
		for (int i = 0; i < n; i++) {
			if (pos != null && (i == pos[0] || i == pos[1]))
				continue;
			ElementShape s;
			if (displayType == "Node")
				s = new SquareShape(startX + i * 50, startY, c[0], c[1], arr[i]);
			else
				s = new BarShape(startX + i * 50, startY - arr[i] * ratio, arr[i] * ratio, c[0]);
			pane.getChildren().add(s);
		}
		if (displayType == "Bar")
			drawBottom(arrState[curSteps]);
	}

	public void drawArrayAnimation(int[] arr, int[] pos, double X, double Y, int type) {
		drawArray(arr, pos, unsortedColor);
		ElementShape[] s = new ElementShape[2];
		for (int i = 0; i < 2; ++i) {
			s[i] = displayType == "Node"
					? new SquareShape(X + pos[i] * 50, Y, selectingColor[0], selectingColor[1], arr[pos[i]])
					: new BarShape(startX + pos[i] * 50, startY - arr[pos[i]] * ratio, arr[pos[i]] * ratio,
							selectingColor[0]);
			pane.getChildren().add(s[i]);
			if (displayType == "Node" && (type == 1 || type == 2))
				s[i].movingY(type == 1 ? -50 : +50).play();
			if (type == 3)
				s[i].movingX(50 * (pos[1 - i] - pos[i])).play();
		}
	}
}
