package algorithms;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public abstract class SortAlgorithm implements Displayable {
	protected int n;
	protected int[] arr;
	protected Pane pane;
	protected TextArea progressField;
	protected String displayType;
	protected int curSteps, numSteps;

	public int getN() {
		return n;
	}

	public int getCurSteps() {
		return curSteps;
	}

	public int getNumSteps() {
		return numSteps;
	}

	public SortAlgorithm(int[] inputArr, Pane inputPane, TextArea inputProgressField, String inputDisplayType) {
		n = inputArr.length;
		arr = inputArr.clone();
		pane = inputPane;
		progressField = inputProgressField;
		displayType = inputDisplayType;
	}

	protected void swap(int posi, int posj) {
		int tmp = arr[posi];
		arr[posi] = arr[posj];
		arr[posj] = tmp;
	}
}
