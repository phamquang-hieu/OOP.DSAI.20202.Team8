package algorithms;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import shapes.ElementShape;



public class SelectionSort {
	private int[] arr;
	private int[][] steps;
	private int[][] minIndex;
	private int size;
	private int stepNum = 1;
	private Transition[] transitions = new Transition[100];
	private ElementShape[] elems;
	private int auto = 0;
	
	
	
	public ElementShape[] getElems() {
		return elems;
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

	public SelectionSort(int[] arr) {
		this.arr = arr;
		this.size = arr.length;
		this.steps = new int[size][size];
		this.steps[0] = arr.clone();
		this.minIndex = new int[size-1][size];
		this.elems = new ElementShape[size];
		for (int i = 0; i< size; i++) {
			elems[i] = new ElementShape(arr[i]*5, Color.PINK, 400 + i*25, 450);
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

        
    	for (int i = 0; i < seperate; i++)
    	{
			pt.getChildren().add(colorElement(elems[i], Color.web("#05141a")));
    	}
    	for (int i = seperate; i < arr.length; i++)
    	{
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
    
    ParallelTransition swap(int i, int j) {
        ParallelTransition pt = new ParallelTransition();

        int distance = (j - i)*25;

        pt.getChildren().addAll(elems[i].movingX(distance), elems[j].movingX(-distance));

        ElementShape tmp = elems[i];
        this.elems[i] = this.elems[j];
        this.elems[j] = tmp;

        return pt;
      }
    
	public void Sort() {
		int currentMinIndex;
		transitions[0] = colorArray(this.elems,0, -1, -1);
		auto += 1;

		
		for (int i = 0; i < size-1; i ++) {
			currentMinIndex = i;
			minIndex[i][i] = currentMinIndex;
			transitions[auto] = colorArray(this.elems, i, -1, i);
			auto += 1;

			
			for (int j = i+1; j < size; j++) {
				transitions[auto] = colorArray(this.elems, i, j, currentMinIndex);
				auto += 1;
				if (arr[j] < arr[currentMinIndex]) {
					currentMinIndex = j;
					stepNum += 1;
					transitions[auto] = colorArray(this.elems, i, j, currentMinIndex);
					auto += 1;
				}
				minIndex[i][j] = currentMinIndex;
				stepNum += 1;
			}

			int tmp = arr[currentMinIndex];
			arr[currentMinIndex] = arr[i];
			arr[i] = tmp;
			transitions[auto] = swap(i, currentMinIndex);
			auto += 1;
			
			steps[i+1]=arr.clone();
			stepNum += 1;
		}
		
		transitions[auto] = colorArray(this.elems, size - 1, size - 1, size-1);
		auto += 1;
		transitions[auto] = colorArray(this.elems, size, -1, -1);
		auto += 1;
		
	}
	
	

	
}
