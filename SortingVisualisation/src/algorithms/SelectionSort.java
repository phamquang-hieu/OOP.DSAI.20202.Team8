package algorithms;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import shapes.ElementShape;

public class SelectionSort {
	private int[] arr;
	private int[][] steps;
	private int[][] minIndex;
	private int size;
	private int stepNum = 1;
	private Transition[] transitions = new Transition[100];
	private ElementShape[] bars;
	private StackPane[][] staticNodes;
	private StackPane[][] staticBars;
	private String[] explanation = new String[100];
	private int auto = 0;
	private double X;
	public String[] getExplanation() {
		return explanation;
	}

	private double Y;

	public ElementShape[] getElems() {
		return bars;
	}

	public Transition[] getTransitions() {
		return transitions;
	}

	public int[] getArr() {
		return arr;
	}

	public int[][] getSteps() {
		return steps;
	}

	public int[][] getMinIndex() {
		return minIndex;
	}

	public int getSize() {
		return size;
	}

	public int getStepNum() {
		return stepNum;
	}

	public SelectionSort(int[] arr, double X, double Y) {
		this.arr = arr;
		this.size = arr.length;
		this.steps = new int[size][size];
		this.steps[0] = arr.clone();
		this.minIndex = new int[size - 1][size];
		this.bars = new ElementShape[size];
		this.staticBars = new StackPane[100][size];
		this.staticNodes = new StackPane[100][size+1];

		this.X = X;
		this.Y = Y;
		for (int i = 0; i < size; i++) {
			bars[i] = new ElementShape(arr[i] * 5, Color.web("#ab93c9"),
					X - (20 * arr.length + 5 * (arr.length - 1)) / 2 + i * 25, Y + 250);
		}
	}
	
	public StackPane drawElement(int element, double X, double Y, Color c, Color c2) {
		ElementShape stack = new ElementShape(Integer.toString(element), X, Y, c, 14, c2);
		return stack;

	}

	public StackPane drawElement(int height, Color c, double X, double Y) {

		ElementShape stack = new ElementShape(height, c, X, Y);
		return stack;
	}
	
	public StackPane[] drawArray(int[] arr, int seperate, int minValue, int index, double X, double Y) {
		double arrayLength = 40 * (arr.length + 1) + 10 * (arr.length - 2);
		double startX = X - arrayLength / 2;
		double startY = Y - 20;
		StackPane[] currentArr;
		if (minValue != -1) {
			currentArr = new StackPane[size+1];
			currentArr[size] = drawElement(minValue, startX + 30 + index * 50, startY + 50, Color.web("#ffbea3"), Color.BLACK);

		}
		else {
			currentArr = new StackPane[size];
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
		
		double arrayLength = 20 * arr.length + 5 * (arr.length - 1);
		double startX = X - arrayLength / 2;
		double startY = Y + 250;
		StackPane[] currentArr = new StackPane[size];

		for (int i = 0; i < seperate; i++) {
			currentArr[i] = drawElement(arr[i] * 5, Color.web("#05141a"), startX + i * 25, startY);
		}
		for (int i = seperate; i < arr.length; i++) {
			currentArr[i] = drawElement(arr[i] * 5, Color.web("#ab93c9"), startX + i * 25, startY);
		}
		if (minIndex != -1) {
			currentArr[minIndex] = drawElement(arr[minIndex] * 5, Color.web("#50435d"), startX + minIndex * 25, startY);
		}
		if (index != -1 && minIndex != index) {
			currentArr[index] = drawElement(arr[index] * 5, Color.web("#ffbea3"), startX + index * 25, startY);
		}
		return currentArr;
	}

	FillTransition colorElement(ElementShape e, Color c) {

		FillTransition ft = new FillTransition();
		ft.setShape(e.getRectangle());
		ft.setToValue(c);
		ft.setDuration(Duration.millis(500));

		return ft;

	}

	ParallelTransition colorArray(ElementShape[] elems, int seperate, int index, int minIndex) {

		ParallelTransition pt = new ParallelTransition();

		for (int i = 0; i < seperate; i++) {
			pt.getChildren().add(colorElement(elems[i], Color.web("#05141a")));
		}
		for (int i = seperate; i < arr.length; i++) {
			pt.getChildren().add(colorElement(elems[i], Color.web("#ab93c9")));

		}
		if (minIndex != -1) {
			pt.getChildren().add(colorElement(elems[minIndex], Color.web("#50435d")));
		}
		if (index != -1 && minIndex != index) {
			pt.getChildren().add(colorElement(elems[index], Color.web("#ffbea3")));
		}
		return pt;
	}

	public void colorArray(ElementShape[] node, int seperate, int minValue, int index, int height) {
		ParallelTransition pt = new ParallelTransition();

		for (int i = 0; i < seperate; i++) {
			pt.getChildren().addAll(colorElement(node[i], Color.web("#05141a")), node[i].movingX(30));

		}
		for (int i = seperate; i < arr.length; i++) {
			pt.getChildren().add(colorElement(node[i], Color.web("#ffbea3")));

		}
		if (minValue != -1) {
			pt.getChildren().add(colorElement(node[minValue], Color.web("#ffbea3")));
		}

	}

	ParallelTransition swap(int i, int j, double dist) {
		ParallelTransition pt = new ParallelTransition();

		double distance = (j - i) * dist;

		pt.getChildren().addAll(bars[i].movingX(distance), bars[j].movingX(-distance));

		ElementShape tmp = bars[i];
		this.bars[i] = this.bars[j];
		this.bars[j] = tmp;

		return pt;
	}

	public void Sort() {
		int currentMinIndex;
		transitions[auto] = colorArray(this.bars, 0, -1, -1);
		staticBars[auto] = drawArray(arr, 0, -1,  X, Y, -1);
		staticNodes[auto] = drawArray(arr, 0, -1, -1, X, Y);
		explanation[auto] = "Start Selection Sort!";


		auto += 1;
		

		for (int i = 0; i < size - 1; i++) {
			currentMinIndex = i;
			minIndex[i][i] = currentMinIndex;
			transitions[auto] = colorArray(this.bars, i, -1, i);
			staticBars[auto] = drawArray(arr, i, -1,  X, Y, i);
			staticNodes[auto] = drawArray(arr, i, arr[i], i, X, Y);
			explanation[auto] = "Find the smallest value";



			auto += 1;

			for (int j = i + 1; j < size; j++) {
				transitions[auto] = colorArray(this.bars, i, j, currentMinIndex);
				staticBars[auto] = drawArray(arr, i, j, X, Y, currentMinIndex);
				staticNodes[auto] = drawArray(arr, i, arr[currentMinIndex], j, X, Y);
				explanation[auto] = "Find the smallest value";

				auto += 1;

				if (arr[j] < arr[currentMinIndex]) {
					currentMinIndex = j;
					stepNum += 1;
					transitions[auto] = colorArray(this.bars, i, j, currentMinIndex);
					staticBars[auto] = drawArray(arr, i, j, X, Y, currentMinIndex);
					staticNodes[auto] = drawArray(arr, i, arr[currentMinIndex], j, X, Y);
					explanation[auto] = "Found a smaller value!";

					auto += 1;
				}
				minIndex[i][j] = currentMinIndex;
				stepNum += 1;
			}

			int tmp = arr[currentMinIndex];
			arr[currentMinIndex] = arr[i];
			arr[i] = tmp;
			transitions[auto] = swap(i, currentMinIndex, 25);
			staticBars[auto] = drawArray(arr, i+1, -1, X, Y, -1);
			staticNodes[auto] = drawArray(arr, i + 1, -1, -1, X, Y);
			explanation[auto] = "Move the smallest value to the end of the sorted array";

			auto += 1;

			steps[i + 1] = arr.clone();
			stepNum += 1;
		}

		transitions[auto] = colorArray(this.bars, size - 1, size - 1, size - 1);
		staticBars[auto] = drawArray(arr, size - 1, size - 1, X, Y, size - 1);
		staticNodes[auto] = drawArray(arr, size - 1, arr[size - 1], size -1, X, Y);
		explanation[auto] = "There is only one element left!";

		auto += 1;
		transitions[auto] = colorArray(this.bars, size, -1, -1);
		staticBars[auto] = drawArray(arr, size, -1, X, Y, -1);
		staticNodes[auto] = drawArray(arr, size, -1, -1, X, Y);
		explanation[auto] = "Done sorting!";

		auto += 1;

	}

	public ElementShape[] getBars() {
		return bars;
	}

	public StackPane[][] getStaticNodes() {
		return staticNodes;
	}

	public StackPane[][] getStaticBars() {
		return staticBars;
	}

	public int getAuto() {
		return auto;
	}

}
