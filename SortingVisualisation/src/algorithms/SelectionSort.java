package algorithms;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
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
	private Transition[] movements = new Transition[100];
	private ElementShape[] bars;
	private ElementShape[] nodes;
	private int auto = 0;

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
		this.nodes = new ElementShape[size];
		for (int i = 0; i < size; i++) {
			bars[i] = new ElementShape(arr[i] * 5, Color.web("#ab93c9"),
					X - (20 * arr.length + 5 * (arr.length - 1)) / 2 + i * 25, Y + 250);
			nodes[i] = new ElementShape(arr[i], Color.web("#ffbea3"),
					X - (40 * (arr.length + 1) + 10 * (arr.length - 2)) / 2 + i * 25, Y - 20);
		}
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
		transitions[0] = colorArray(this.bars, 0, -1, -1);
		auto += 1;

		for (int i = 0; i < size - 1; i++) {
			currentMinIndex = i;
			minIndex[i][i] = currentMinIndex;
			transitions[auto] = colorArray(this.bars, i, -1, i);
			auto += 1;

			for (int j = i + 1; j < size; j++) {
				transitions[auto] = colorArray(this.bars, i, j, currentMinIndex);
				auto += 1;
				if (arr[j] < arr[currentMinIndex]) {
					currentMinIndex = j;
					stepNum += 1;
					transitions[auto] = colorArray(this.bars, i, j, currentMinIndex);
					auto += 1;
				}
				minIndex[i][j] = currentMinIndex;
				stepNum += 1;
			}

			int tmp = arr[currentMinIndex];
			arr[currentMinIndex] = arr[i];
			arr[i] = tmp;
			transitions[auto] = swap(i, currentMinIndex, 25);
			auto += 1;

			steps[i + 1] = arr.clone();
			stepNum += 1;
		}

		transitions[auto] = colorArray(this.bars, size - 1, size - 1, size - 1);
		auto += 1;
		transitions[auto] = colorArray(this.bars, size, -1, -1);
		auto += 1;

	}

}
