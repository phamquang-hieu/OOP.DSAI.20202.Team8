package algorithms;

import java.util.Arrays;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import shapes.SquareShape;

public class MergeSort extends SortAlgorithm {
	private int[] arr, cloneArr;
	private double startX, startY;
	private double[][] steps;
	private Color[] initColor = { Color.YELLOWGREEN, Color.BLACK };
	private final String[] instructions;

	public MergeSort(int[] inputArr, Pane inputPane, TextArea inputProgressField, String inputDisplayType) {
		super(inputArr, inputPane, inputProgressField, inputDisplayType);
		this.arr = inputArr;
		steps = new double[6][120];
		this.startX = 0;
		this.startY = 50;
		cloneArr = arr.clone();
		curSteps = numSteps = 0;
		assignSteps(0, n - 1, pane.getWidth() / 2 - (n) * 25, startY, 0, 0);
		merge_sort(0, n - 1, startX, pane.getWidth(), startY); //sort the array beforehand
		
		instructions = new String[6];
		instructions[0] = "Split the selected array (yellow)\n(as evenly as possible)";
		instructions[1] = "Select the left sub-array and ready to merge";
		instructions[2] = "Select the right sub-array and ready to merge";
		instructions[3] = "Put the minimum of the to selected value\n(red) into the sorted array";
		instructions[4] = "Left array run out of elements,\ncopy all remaining values \nto the sorted array";
		instructions[5] = "Right array run out of elements,\ncopy all remaining values \nto the sorted array";
	}

	private void merge(int start, int end, int mid, double midX, double startY, double startLeftX, double startRightX) {
		int[] aux = new int[end - start + 1];

		int left_index = start;
		int right_index = mid + 1;
		assignSteps(start, end, midX, startY, 3, 3);

		for (int i = 0; i < (end - start + 1); ++i) {
			if (left_index > mid) {
				// all element from left array has been taken.
				aux[i] = this.arr[right_index++];
				assignSteps(-1, -1, startRightX, startY + 80, 3, 4);
				assignSteps(-1, aux[i], midX + (i) * 50, startY, 2, 4);
				if (right_index < end + 1) {
					startRightX += 50;
					assignSteps(-1, this.arr[right_index], startRightX, startY + 80, 1, 4);
				}
			} else if (right_index > end) {
				// all element from right array has been taken.
				aux[i] = this.arr[left_index++];
				assignSteps(-1, -1, startLeftX, startY + 80, 3, 5);
				assignSteps(-1, aux[i], midX + (i) * 50, startY, 2, 5);
				if (left_index < mid + 1) {
					startLeftX += 50;
					assignSteps(-1, this.arr[left_index], startLeftX, startY + 80, 1, 5);
				}
			} else if (this.arr[left_index] < this.arr[right_index]) {
				aux[i] = this.arr[left_index++];
				assignSteps(-1, -1, startLeftX, startY + 80, 3, 3);
				assignSteps(-1, aux[i], midX + (i) * 50, startY, 2, 3);
				if (left_index < mid + 1) {
					startLeftX += 50;
					assignSteps(-1, this.arr[left_index], startLeftX, startY + 80, 1, 3);
				}
			} else if (this.arr[left_index] >= this.arr[right_index]) {
				aux[i] = this.arr[right_index++];
				assignSteps(-1, -1, startRightX, startY + 80, 3, 3);
				assignSteps(-1, aux[i], midX + (i) * 50, startY, 2, 3);
				if (right_index < end + 1) {
					startRightX += 50;
					assignSteps(-1, this.arr[right_index], startRightX, startY + 80, 1, 3);
				}
			}
		}
		int j = 0;
		for (int i = start; i <= end; ++i)
			this.arr[i] = aux[j++];

	}

	public void merge_sort(int start, int end, double startX, double endX, double startY) {
		if (start < end) {
			int mid = (int) (start + end) / 2;
			double midX = (startX + endX) / 2;

			assignSteps(start, end, midX - (end - start + 1) * 25, startY, 4, 0);
			assignSteps(start, mid, (startX + midX) / 2 - (mid - start + 1) * 25, startY + 80, 0, 0);
			assignSteps(mid + 1, end, (midX + endX) / 2 - (end - mid) * 25, startY + 80, 0, 0);
			merge_sort(start, mid, startX, midX, startY + 80);

			merge_sort(mid + 1, end, midX, endX, startY + 80);
			double startLeftX = (startX + midX) / 2 - (mid - start + 1) * 25;
			double startRightX = (midX + endX) / 2 - (end - mid) * 25;

			assignSteps(-1, this.arr[start], startLeftX, startY + 80, 1, 1); // select and ready to merge
			assignSteps(-1, this.arr[mid + 1], startRightX, startY + 80, 1, 2); // select and ready to merge

			merge(start, end, mid, midX - (end - start + 1) * 25, startY, startLeftX, startRightX);
		}
	}

	private void assignSteps(double args1, double args2, double args3, double args4, int color, int instruction) {
		// color:
		// 0: yellowgreen
		// 1: red: selected and ready to merge
		// 2: blueviolet: merged
		// 3: white: vanished
		// 4: yellow: selected and ready to split
		this.steps[0][numSteps] = args1;
		this.steps[1][numSteps] = args2;
		this.steps[2][numSteps] = args3;
		this.steps[3][numSteps] = args4;
		this.steps[4][numSteps] = color;
		this.steps[5][numSteps] = instruction;
		numSteps++;
	}

	@Override
	public void displayStartScreen() {
		pane.getChildren().clear();
		curSteps = 0;
		displayStep(curSteps++);
	}

	@Override
	public void nextStep() {
		if (curSteps == numSteps) {
			displayFinishScreen();
			return;
		}
		displayStep(curSteps);
		++curSteps;
	}

	@Override
	public void previousStep() {
		pane.getChildren().clear();
		int tmp = curSteps;
		for (curSteps = 0; curSteps <= tmp - 2; ++curSteps) {
			displayStep(curSteps);
		}
	}

	@Override
	public void displayFinishScreen() {
		pane.getChildren().clear();
		drawArray(this.arr, initColor, pane.getWidth() / 2 - n * 25, startY);
		progressField.setText("Done sorting!");
		curSteps = numSteps;
	}

	public void drawArray(int arr[], Color[] c, double startX, double startY) {
		for (int i = 0; i < arr.length; i++) {
			SquareShape s;
			s = new SquareShape(startX + i * 50, startY, c[0], c[1], arr[i]);
			pane.getChildren().add(s);
		}
	}

	public void displayStep(int stepNum) {
		Color[] c = initColor.clone();
		int tmp = (int) steps[4][stepNum];
		if (tmp == 0)
			c[0] = Color.YELLOWGREEN;
		else if (tmp == 1)
			c[0] = Color.RED;
		else if (tmp == 2)
			c[0] = Color.BLUEVIOLET;
		else if (tmp == 3)
			c[0] = Color.WHITE;
		else if (tmp == 4)
			c[0] = Color.YELLOW;

		if (steps[0][stepNum] == -1) {
			int[] tmp2 = new int[1];
			tmp2[0] = (int) steps[1][stepNum];
			drawArray(tmp2, c, steps[2][stepNum], steps[3][stepNum]);
		} else {
			if (steps[4][curSteps] != 3) {
				drawArray(Arrays.copyOfRange(cloneArr, (int) steps[0][stepNum], (int) steps[1][stepNum] + 1), c,
						steps[2][stepNum], steps[3][stepNum]);
			} else {
				int len = (int) (steps[1][stepNum] - steps[0][stepNum] + 1);
				int[] tmp3 = new int[len];
				for (int i = 0; i < (len); ++i)
					tmp3[i] = -1; // create array with full of -1 to draw white square.
				drawArray(tmp3, c, steps[2][stepNum], steps[3][stepNum]);
			}
		}
		progressField.setText(instructions[(int) steps[5][curSteps]]);
	}
}
