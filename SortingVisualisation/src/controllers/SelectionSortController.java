package controllers;

import java.util.Arrays;

import javax.swing.JOptionPane;

import algorithms.SelectionSort;
import datastructure.Array;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class SelectionSortController extends ScreenController{
	
	private Array Arr;
	private int[] arr;
	private int comparing = 0;
	private SelectionSort ss;
	private int step = 0;
	private int size;
	private boolean changeMin;
	private double X;
	private double Y;
	private boolean done;
	
	
    
    void randomArray() {
    	arrayDisplayArea.getChildren().clear();
    	this.Arr = new Array(getLength(arraySize5, arraySize6, arraySize7, arraySize8));
    	this.arr = Arrays.copyOf(this.Arr.data, this.Arr.getLength());
    }
    
 
    @FXML
    void buttonRunPressed(ActionEvent event) throws Exception{
    	try {
			if (randomizeMode.isSelected()) {
				randomArray();
			} else {
				arrayDisplayArea.getChildren().clear();
				this.Arr = new Array(textFieldArray.getText());
		    	this.arr = Arrays.copyOf(this.Arr.data, this.Arr.getLength());
			}
			progressField.setText("Start Selection Sort!");
			comparing = 0;
			step = 0;
			this.size = this.Arr.getLength();
			this.X = arrayDisplayArea.getWidth()/2;
			this.Y = arrayDisplayArea.getHeight()/2 - 40;
	    	arrayDisplayArea.getChildren().clear();
	    	if (formNode.isSelected()) {
	    		drawArray(arr, 0, -1, 0 ,X, Y);
	    	}
	    	else {
	    		drawArray(arr, 0, -1, X, Y, -1);
	    	}
	    	ss = new SelectionSort(this.arr);
	    	ss.Sort();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
    }

    @FXML
    void btnNextPressed(ActionEvent event) {
    	arrayDisplayArea.getChildren().clear();
    	
    	if (changeMin) {
    		progressField.setText("Update the smallest value");
    		if (formNode.isSelected()) {
        		drawArray(ss.getSteps()[step], step, ss.getSteps()[step][ss.getMinIndex()[step][comparing]], comparing, X, Y);    			
    		}
    		else {
    			drawArray(ss.getSteps()[step], step, comparing, X, Y, comparing);
    		}
    		if ((comparing == size -1) && step < size -1) {
    			step += 1;
    			comparing = step;
    		}
    		else {
    			comparing += 1;
    		}
    		changeMin=false;
    	}
    	
    	
    	

    	
    	else if (comparing<size-1) {
    		if (comparing > step) {
        		if (formNode.isSelected()) {
        			drawArray(ss.getSteps()[step], step, ss.getSteps()[step][ss.getMinIndex()[step][comparing-1]], comparing, X, Y);
        		}
        		else {
        			drawArray(ss.getSteps()[step], step, comparing, X, Y, ss.getMinIndex()[step][comparing-1]);
        		}
        		if (ss.getMinIndex()[step][comparing-1] != ss.getMinIndex()[step][comparing]) {
        			progressField.setText("Found a smaller value!");
        			changeMin=true;
        		}
        		else {
        			progressField.setText("Compare to the smallest value");
        			comparing += 1;
        		}
    		}
    		else {
    			if (comparing != 0) {
    				progressField.setText("Move the smallest element to the last position of the sorted array. Continue with the unsorted array!");
    			}
    			else {
    				progressField.setText("Start finding the smallest element");
    			}
    			if (formNode.isSelected()) {
    				drawArray(ss.getSteps()[step], step, ss.getSteps()[step][ss.getMinIndex()[step][comparing]], comparing, X, Y);
    			}
    			else {
    				drawArray(ss.getSteps()[step], step, comparing, X, Y, ss.getMinIndex()[step][comparing]);
    			}
	    		comparing += 1;
    		}
    	}
    	

    	
    	else if ((comparing == size -1) && step < size -1) {
			if (formNode.isSelected()) {
				drawArray(ss.getSteps()[step], step, ss.getSteps()[step][ss.getMinIndex()[step][comparing-1]], comparing, X, Y);
			}
			else {
				drawArray(ss.getSteps()[step], step, comparing, X, Y, ss.getMinIndex()[step][comparing-1]);
			}
    		if (ss.getMinIndex()[step][comparing-1] != ss.getMinIndex()[step][comparing]) {
    			progressField.setText("Found a smaller value!");
    			changeMin=true;
    		}
    		else {
    			progressField.setText("Compare to the smallest value");
    			step += 1;
    			comparing = step;
    		}
    		
    	}
    	

    	
    	else if (step == size - 1){
			progressField.setText("There is one value left!");
			if (formNode.isSelected()) {
				drawArray(ss.getSteps()[step], step, -1, -1, X, Y);
			}
			else {
				drawArray(ss.getSteps()[step], step, -1, X, Y, -1);
			}
    		step += 1;
    	}
    	else {
			progressField.setText("Done sorting!");
			if (formNode.isSelected()) {
				drawArray(ss.getSteps()[step-1], step, -1, -1, X, Y);
			}
			else {
	    		drawArray(ss.getSteps()[step-1], step, -1, X, Y, -1);
			}
    	}
    }
    

    
    @FXML
    void btnAutoPressed(ActionEvent event) throws InterruptedException {
    	while (!done) {
    		btnNextPressed(event);
//    		Platform.runLater(null);
    	}
    }

    
    public void drawElement(int element, double X, double Y, Color c) {
    	Rectangle rectangle = new Rectangle(X, Y, 40, 40);
    	rectangle.setFill(c);
    	rectangle.setStroke(c);
    	rectangle.setArcWidth(20);
    	rectangle.setArcHeight(20);

    	
    	Label lb = new Label(Integer.toString(element));
    	
    	StackPane stack = new StackPane();
    	stack.getChildren().addAll(rectangle, lb);
    	stack.setLayoutX(X);
    	stack.setLayoutY(Y);
    	
    	arrayDisplayArea.getChildren().add(stack);
    	
    }
    
    public void drawElement(int height, Color c, double X, double Y) {
    	Rectangle rectangle = new Rectangle(X, Y-height, 20, height);
    	rectangle.setFill(c);
    	rectangle.setStroke(c);
    	rectangle.setArcWidth(5);
    	rectangle.setArcHeight(5);
    	
    	rectangle.setLayoutX(X);
    	rectangle.setLayoutX(Y);

    	
    	arrayDisplayArea.getChildren().add(rectangle);
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
			drawElement(arr[i], startX + 30 + i*50, startY , Color.ALICEBLUE);
    	}
    	if (minValue!=-1) {
    		drawElement(minValue, startX + 30 +index*50, startY + 50, Color.ALICEBLUE);
    	}
    	
    }
    
    public void drawArray(int[] arr, int seperate, int index, double X, double Y, int minIndex) {
    	double arrayLength = 20*arr.length + 5*(arr.length - 1); 
    	double startX = X - arrayLength - 300;
    	double startY = Y + 250;
    	for (int i = 0; i < seperate; i++)
    	{
			drawElement(arr[i]*5, Color.PINK, startX + i*25, startY);
    	}
    	for (int i = seperate; i < arr.length; i++)
    	{
			drawElement(arr[i]*5, Color.LIGHTBLUE, startX + i*25, startY);
    	}
    	if (minIndex != -1) {
			drawElement(arr[minIndex]*5, Color.LIGHTCORAL, startX + minIndex*25, startY);
    	}
    	if (index != -1 && minIndex != index) {
    		drawElement(arr[index]*5, Color.STEELBLUE, startX + index*25, startY);
    	}
    }
    
//    public void animation(int element, double X, double Y, Color c, double targetX, double targetY) {
//    	Rectangle rectangle = new Rectangle(X, Y, 40, 40);
//    	rectangle.setFill(c);
//    	rectangle.setStroke(Color.BLACK);
//    	rectangle.setArcWidth(20);
//    	rectangle.setArcHeight(20);
//
//    	
//    	Label lb = new Label(Integer.toString(element));
//    	
//    	StackPane stack = new StackPane();
//    	stack.getChildren().addAll(rectangle, lb);
//    	stack.setLayoutX(X);
//    	stack.setLayoutY(Y);
//   
//    	
//    	Path path = new Path();
//    	path.getElements().add(new MoveTo(targetX,targetY));
//    	path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
//    	path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));    	
//    	PathTransition pathTransition = new PathTransition();
//    	pathTransition.setDuration(Duration.millis(10000));
//    	pathTransition.setPath(path);
//    	pathTransition.setNode(stack);
//    	pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//    	pathTransition.setCycleCount(Timeline.INDEFINITE);
//    	pathTransition.setAutoReverse(true);
//    	pathTransition.play();
//    }
    
    

}