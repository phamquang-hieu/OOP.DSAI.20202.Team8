package algorithms;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import shapes.BarShape;
import shapes.ElementShape;
import shapes.SquareShape;

public class SelectionSort extends SortAlgorithm {
	private double X, Y;
	private int[][] minIndex, arrState;
	private String[] flags;
	private StackPane[][] staticNodes;
	private StackPane[][] staticBars;


	public SelectionSort(int[] inputArr, Pane inputPane, TextArea inputProgressField, String inputDisplayType) {
		super(inputArr, inputPane, inputProgressField, inputDisplayType);
		this.arrState = new int[n][n];
		this.arrState[0] = arr.clone();
		this.minIndex = new int[n-1][n];
		this.staticBars = new StackPane[100][n];
		this.staticNodes = new StackPane[100][n+1];
		this.X = pane.getWidth() / 2;
		this.Y = pane.getHeight() / 2 - 40;
		
		int currentMinIndex;
		curSteps = 1;
		numSteps = 1;
		flags = new String[100];
		staticBars[0] = drawArray(arr, 0, -1,  X, Y, -1);
		staticNodes[0] = drawArray(arr, 0, -1, -1, X, Y);
		flags[0] = "Start Selection Sort!";

		
		for (int i = 0; i < n-1; i ++) {
			currentMinIndex = i;
			minIndex[i][i] = currentMinIndex;
			staticBars[numSteps] = drawArray(arr, i, -1,  X, Y, i);
			staticNodes[numSteps] = drawArray(arr, i, arr[i], i, X, Y);
			flags[numSteps] = "Start find smallest element";
			numSteps += 1;
			
			for (int j = i+1; j < n; j++) {
				flags[numSteps] = "Find smallest element";
				staticBars[numSteps] = drawArray(arr, i, j, X, Y, currentMinIndex);
				staticNodes[numSteps] = drawArray(arr, i, arr[currentMinIndex], j, X, Y);
				numSteps += 1;
				if (arr[j] < arr[currentMinIndex]) {
					currentMinIndex = j;
					flags[numSteps]= "Found a smaller value!";
					staticBars[numSteps] = drawArray(arr, i, j, X, Y, currentMinIndex);
					staticNodes[numSteps] = drawArray(arr, i, arr[currentMinIndex], j, X, Y);
					numSteps += 1;

				}
				minIndex[i][j] = currentMinIndex;
			}
			
			int tmp = arr[currentMinIndex];
			arr[currentMinIndex] = arr[i];
			arr[i] = tmp;
			arrState[i+1]=arr.clone();
			staticBars[numSteps] = drawArray(arr, i+1, -1, X, Y, -1);
			staticNodes[numSteps] = drawArray(arr, i + 1, -1, -1, X, Y);
			flags[numSteps] = "Move the smallest value to the end of the sorted array";
			numSteps += 1;

		}
		staticBars[numSteps] = drawArray(arr, n - 1, n - 1, X, Y, n - 1);
		staticNodes[numSteps] = drawArray(arr, n - 1, arr[n - 1], n -1, X, Y);
		flags[numSteps] = "There is only one element left!";

		numSteps += 1;
		staticBars[numSteps] = drawArray(arr, n, -1, X, Y, -1);
		staticNodes[numSteps] = drawArray(arr, n, -1, -1, X, Y);
		flags[numSteps] = "Done sorting!";

		numSteps += 1;
	}

	@Override
	public void displayStartScreen() {
		// TODO Auto-generated method stub
		pane.getChildren().clear();


		if (displayType.equals("Node")) {
			pane.getChildren().addAll(staticNodes[0]);
//			colorNode();
		}

		else {
			pane.getChildren().addAll(staticBars[0]);
//			colorBar();


		}

		progressField.setText("Start Selection Sort!");
//		stepShow.setText("" + (stepNum ) + "/" + (ss.getAuto()-1));
//		curSteps += 1;

	}

	@Override
	public void nextStep() {
		pane.getChildren().clear();
//		stepShow.setText("" + (stepNum) + "/" + (ss.getAuto()-1));
		progressField.setText(flags[curSteps]);

		
		if (curSteps < numSteps) {
			if (displayType.equals("Node")) {
//				colorNode();
				pane.getChildren().addAll(staticNodes[curSteps]);
			} 
			else {
//				colorBar();
				pane.getChildren().addAll(staticBars[curSteps]);

			}
			curSteps += 1;

		}
		else {
//			stepShow.setText("" + (ss.getAuto() -1) + "/" + (ss.getAuto()-1));
			progressField.setText(flags[numSteps-1]);


			if (displayType.equals("Node")) {
//				colorNode();
				pane.getChildren().addAll(staticNodes[numSteps-1]);
			} 
			else {
//				colorBar();
				pane.getChildren().addAll(staticBars[numSteps-1]);
			}
		}
	}

	@Override
	public void previousStep() {
		// TODO Auto-generated method stub
		pane.getChildren().clear();
		if (curSteps <= 1) {
//			stepShow.setText("" + (0) + "/" + (ss.getAuto()-1));
			curSteps = 1;
			if (displayType.equals("Node")) {
//				colorNode();
				pane.getChildren().addAll(staticNodes[0]);
			} 
			else {
//				colorBar();
				pane.getChildren().addAll(staticBars[0]);
			}
			progressField.setText("Start Selection Sort!");
		}
		else {
			if (displayType.equals("Node")) {
//				colorNode();
				pane.getChildren().addAll(staticNodes[curSteps - 2]);
			} 
			else {
//				colorBar();
				pane.getChildren().addAll(staticBars[curSteps - 2]);
			}
			progressField.setText(flags[curSteps-2]);

			curSteps -= 1;
//			stepShow.setText("" + (stepNum - 1) + "/" + (ss.getAuto()-1));

		}

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		pane.getChildren().clear();

		curSteps = 1;

		if (displayType.equals("Node")) {
			pane.getChildren().addAll(staticNodes[0]);
		} 
		else {
//			colorBar();
			pane.getChildren().addAll(staticNodes[0]);
		}
		progressField.setText("Start Selection Sort!");

	}

	@Override
	public void displayFinishScreen() {
		// TODO Auto-generated method stub
		pane.getChildren().clear();
		progressField.setText("Done sorting!");
		curSteps = numSteps;

		if (displayType.equals("Node")) {
			pane.getChildren().addAll(staticNodes[curSteps-1]);
		} 
		else {
			pane.getChildren().addAll(staticBars[curSteps-1]);
		}
	}
	
	public StackPane drawElement(int element, double X, double Y, Color c, Color c2) {
		ElementShape stack = new SquareShape(Integer.toString(element), X, Y, c, 14, c2);
		return stack;

	}

	public StackPane drawElement(int height, Color c, double X, double Y) {

		ElementShape stack = new BarShape(height, c, X, Y);
		return stack;
	}
	
	public StackPane[] drawArray(int[] arr, int seperate, int minValue, int index, double X, double Y) {
		double arrayLength = 40 * (arr.length + 1) + 10 * (arr.length - 2);
		double startX = X - arrayLength / 2;
		double startY = Y - 20;
		StackPane[] currentArr;
		if (minValue != -1) {
			currentArr = new StackPane[n+1];
			currentArr[n] = drawElement(minValue, startX + 30 + index * 50, startY + 50, Color.web("#ffbea3"), Color.BLACK);
		}
		else {
			currentArr = new StackPane[n];
		}
		for (int i = 0; i < seperate; i++) {
			currentArr[i] = drawElement(arr[i], startX + i * 50, startY, Color.web("#05141a"), Color.WHITE);
			
		}
		for (int i = seperate; i < arr.length; i++) {
			currentArr[i] = drawElement(arr[i], startX + 30 + i * 50, startY, Color.web("#ffbea3"), Color.BLACK);
		}
		return currentArr;
	}

	public StackPane[] drawArray(int[] arr, int seperate, int index, double X, double Y, int minIndex) {
		
		double arrayLength = 40 * arr.length + 10 * (arr.length - 1);
		double startX = X - arrayLength / 2;
		double startY = Y + 250;
		StackPane[] currentArr = new StackPane[n];

		for (int i = 0; i < seperate; i++) {
			currentArr[i] = drawElement(arr[i] * 5, Color.web("#05141a"), startX + i * 50, startY);
		}
		for (int i = seperate; i < arr.length; i++) {
			currentArr[i] = drawElement(arr[i] * 5, Color.web("#ab93c9"), startX + i * 50, startY);
		}
		if (minIndex != -1) {
			currentArr[minIndex] = drawElement(arr[minIndex] * 5, Color.web("#50435d"), startX + minIndex * 50, startY);
		}
		if (index != -1 && minIndex != index) {
			currentArr[index] = drawElement(arr[index] * 5, Color.web("#ffbea3"), startX + index * 50, startY);
		}
		return currentArr;
	}
	
	public int getCurSteps() {
		return curSteps-1;
	}

	public int getNumSteps() {
		return numSteps-1;
	}

}
