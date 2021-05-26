package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.Arrays;

import javax.swing.JOptionPane;

import algorithms.MergeSort;
import datastructure.Array;

public class MergeSortScreenController extends ScreenController{
	private Array arr;
	private int[] cloneArr;
	private double firstLine;
	private int stepNum = 0;
	private int numStep;
	private double steps[][];
	private String[] instructions;
	
	@FXML
	public void initialize() {
		instructions = new String[6];
		instructions[0] = "Split the selected array (as evenly as possible)";
		instructions[1] = "Select the left array and ready to merge";
		instructions[2] = "Select the right array and ready to merge";
		instructions[3] = "Select the minimum of the two selected \n (rightmost red) values to put into the sorted array";
		instructions[4] = "All the elements from the left array has been taken, \n copy all values from the right array into the sorted array";
		instructions[5] = "All the elements from the left array has been taken, \n copy all values from the right array into the sorted array";
		progressField.setEditable(false);
		progressField.setFont(new Font("Arial", 20));
	}
	
    void randomArray() {
    	arrayDisplayArea.getChildren().clear();
    	//System.out.println("real Length: " + getLength(arraySize5, arraySize6, arraySize7, arraySize8));
    	this.arr = new Array(getLength(arraySize5, arraySize6, arraySize7, arraySize8));
    	this.firstLine = 50;	
    }
    
    @FXML
    void buttonRunPressed(ActionEvent event) throws Exception {
    	try {
			if (randomizeMode.isSelected()) {
				
				randomArray();
			} else {
				arrayDisplayArea.getChildren().clear();
				this.arr = new Array(textFieldArray.getText());
				this.firstLine = 50;
			}
			cloneArr = Arrays.copyOf(this.arr.data, this.arr.getLength());
			drawAnArray(cloneArr, this.arrayDisplayArea.getWidth() / 2, firstLine, Color.YELLOWGREEN);
			
			MergeSort ms = new MergeSort(this.arr.data);
			ms.merge_sort(0, this.arr.getLength() - 1, 0, this.arrayDisplayArea.getWidth(), firstLine);
			this.steps = ms.getSteps();
			this.numStep = ms.getNumStep();
			this.stepNum = 0;
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
    }
    
    @FXML
   	void btnNextPressed(ActionEvent event) {
	   if(this.arr.getLength()==0)
		   return;       // no element initiated
	   if(this.stepNum >= this.numStep)
		   return;
	   
	   displayStep(this.stepNum);
	   this.stepNum++;
    }
    
    
    private void displayStep(int stepNum) {
    	Color c= Color.YELLOWGREEN;
    	if(steps[4][stepNum]==0) c = Color.YELLOWGREEN;
    	else if (steps[4][stepNum]==1) c = Color.RED;
    	else if (steps[4][stepNum]==2) c = Color.BLUEVIOLET;
    	else if(steps[4][stepNum]==3) c = Color.WHITE;
    	else if(steps[4][stepNum]==4) c = Color.YELLOW;
    	
    	if(steps[0][stepNum] == -1) {
    		drawAnElement((int) steps[1][stepNum], steps[2][stepNum], steps[3][stepNum], c);
    	}
    	else {
    		if(steps[4][stepNum]!=3) {
    			drawAnArray(Arrays.copyOfRange(cloneArr, (int) steps[0][stepNum], (int) steps[1][stepNum]+1), steps[2][stepNum], steps[3][stepNum], c);
    		}
    		else {
    			int len = (int) (steps[1][stepNum] - steps[0][stepNum] + 1);
    			for(int i= 0; i < (len); ++i) {
    				StackPane stack = new StackPane();
    				Circle cir = new Circle(20);
    				cir.setFill(Color.WHITE);
    				stack.getChildren().add(cir);
    				stack.setLayoutX(steps[2][stepNum] + (i - len/2)*50);
    				stack.setLayoutY(steps[3][stepNum]);
    				this.arrayDisplayArea.getChildren().add(stack);
    			}
    		}
    	}
    	progressField.setText(this.instructions[(int) steps[5][stepNum]]);
    }
    
    
    public void drawAnElement(int x, double X, double Y, Color c) {
    	String s = Integer.toString(x);
    	Label lb = new Label(s);
		lb.setFont(new Font("Arial", 24));
		Circle circle = new Circle(20);
    	circle.setFill(c);
    	StackPane stack = new StackPane();
    	stack.getChildren().addAll(circle, lb);
    	stack.setLayoutX(X);
    	stack.setLayoutY(Y);
   
    	arrayDisplayArea.getChildren().add(stack);
    }
    
    public void drawAnArray(int[] subarr, double midX, double startY, Color c) {
    	
    	for(int i = 0; i < (subarr.length); i++)
    	{
			drawAnElement(subarr[i], midX + (i - subarr.length/2)*50, startY, c);
    	}	
    }
}