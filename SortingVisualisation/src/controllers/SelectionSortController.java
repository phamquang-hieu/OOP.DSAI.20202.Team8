package selectionsort;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class SelectionSortController {
	
	private int[] arr = {3, 1, 2, 5, 4};
	private int comparing = 0;
	private SelectionSort ss = new SelectionSort(arr);
	private int step = 0;
	private int size = arr.length;
	
	
//	public SelectionSortController(int[] arr) {
//		this.arr = arr.clone();
//		this.ss = new SelectionSort(arr);
//	}
	
	

    @FXML
    private ToggleGroup createArray;

    @FXML
    private TextField progressField;

    @FXML
    private Button btnNext;

    @FXML
    private ToggleGroup arraySize;

    @FXML
    private Button btnRun;
    
    @FXML
    private Pane arrayDisplayArea;
    

    @FXML
    void btnRunPressed(ActionEvent event) {
    	drawArray(arr, 0, -1, 0 ,arrayDisplayArea.getWidth()/2, arrayDisplayArea.getHeight()/2 - 40);
    	ss.Sort();
    }

    @FXML
    void btnNextPressed(ActionEvent event) {
    	arrayDisplayArea.getChildren().clear();
    	if (comparing<size-1) {
    		drawArray(ss.getSteps()[step], step, ss.getSteps()[step][ss.getMinIndex()[step][comparing]], comparing, arrayDisplayArea.getWidth()/2, arrayDisplayArea.getHeight()/2 - 40);
    		comparing += 1;    	
    	}
    	else if ((comparing == size -1) && step < size -1) {
    		drawArray(ss.getSteps()[step], step, ss.getSteps()[step][ss.getMinIndex()[step][comparing]], comparing, arrayDisplayArea.getWidth()/2, arrayDisplayArea.getHeight()/2 - 40);
    		step += 1;
    		comparing = step;
    	}
    	else if (step == size - 1){
    		drawArray(ss.getSteps()[step], step, -1, -1, arrayDisplayArea.getWidth()/2, arrayDisplayArea.getHeight()/2 - 40);
    		step += 1;
    	}
    	else {
    		drawArray(ss.getSteps()[step-1], step, -1, -1, arrayDisplayArea.getWidth()/2, arrayDisplayArea.getHeight()/2 - 40);
    	}
    }

    
    public void drawElement(int element, double X, double Y, Color c) {
    	Rectangle rectangle = new Rectangle(X, Y, 40, 40);
    	rectangle.setFill(c);
    	rectangle.setStroke(Color.BLACK);
    	rectangle.setArcWidth(20);
    	rectangle.setArcHeight(20);

    	
    	Label lb = new Label(Integer.toString(element));
    	
    	StackPane stack = new StackPane();
    	stack.getChildren().addAll(rectangle, lb);
    	stack.setLayoutX(X);
    	stack.setLayoutY(Y);
    	
    	arrayDisplayArea.getChildren().add(stack);
    	
    }
    
    
    public void drawArray(int[] arr, int seperate, int minValue, int index, double X, double Y) {
    	double arrayLength = 40*(arr.length + 1) + 10*(arr.length - 2); 
    	double startX = X - arrayLength/2;
    	double startY = Y - 20;
    	for (int i = 0; i < seperate; i++)
    	{
			drawElement(arr[i], startX + i*50, startY , Color.PINK);
    	}
    	for (int i = seperate; i < arr.length; i++)
    	{
			drawElement(arr[i], startX + 30 + i*50, startY , Color.WHITE);
    	}
    	if (minValue!=-1) {
    		drawElement(minValue, startX + 30 +index*50, startY + 50, Color.ALICEBLUE);
    	}
    	
    }
    
    

}
