package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import shapes.ElementShape;

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
		instructions[1] = "Select the left sub-array and ready to merge";
		instructions[2] = "Select the right sub-array and ready to merge";
		instructions[3] = "Select the minimum of the two selected values to put into the sorted array";
		instructions[4] = "All the elements from the left array has been taken, copy all values from the right array into the sorted array";
		instructions[5] = "All the elements from the left array has been taken, copy all values from the right array into the sorted array";
		progressField.setEditable(false);
		progressField.setFont(new Font("Arial", 20));
	}
	
    void randomArray() {
    	this.arr = new Array(getLength(arraySize5, arraySize6, arraySize7, arraySize8));
    }
    
    @FXML
    void buttonRunPressed(ActionEvent event) throws Exception {
    	try {
			if (randomizeMode.isSelected()) {
				randomArray();
			} else {
				arrayDisplayArea.getChildren().clear();
				this.arr = new Array(textFieldArray.getText());
			}
			cloneArr = Arrays.copyOf(this.arr.data, this.arr.getLength());
			resetView();
			
			MergeSort ms = new MergeSort(this.arr.data);
			ms.merge_sort(0, this.arr.getLength() - 1, 0, this.arrayDisplayArea.getWidth(), firstLine);
			this.steps = ms.getSteps();
			this.numStep = ms.getNumStep();
			this.stepNum = 0;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
			e.printStackTrace();
		}
    }
    @FXML
    void btnAutoPressed(ActionEvent event) {
    	
    }
    
    @FXML
    void btnBackPressed(ActionEvent event) {
    	arrayDisplayArea.getChildren().clear();
    	int tmp = this.stepNum;
    	if(tmp==1) {
    		resetView();
    		return;
    	}
    	for(this.stepNum =0; stepNum < tmp-1; stepNum++) {
    		displayStep(stepNum);
    	}
    }
    
    @FXML
   	void btnNextPressed(ActionEvent event) {
	   if(this.arr.getLength()==0)
		   return;       // no element initiated
	   if(this.stepNum == this.numStep) showLastView();
	   if(this.stepNum > this.numStep) {
		   resetView();
		   return;
	   }
	   
	   displayStep(this.stepNum);
	   this.stepNum++;
    }
    @FXML
    void btnResetPressed(ActionEvent event) {
    	resetView();
    }
    void resetView() {
    	arrayDisplayArea.getChildren().clear();
    	this.firstLine = 50;
		drawAnArray(cloneArr, this.arrayDisplayArea.getWidth() / 2, firstLine, Color.YELLOWGREEN);
		this.stepNum = 0;
    }
    
    @FXML
    void btnSkipPressed(ActionEvent event) {
    	showLastView();
    }
    
    void showLastView() {
    	arrayDisplayArea.getChildren().clear();
    	this.firstLine = 50;
    	drawAnArray(this.arr.data, this.arrayDisplayArea.getWidth() / 2, firstLine, Color.YELLOWGREEN);
    }
    
    private void displayStep(int stepNum) {
    	Color c= Color.YELLOWGREEN;
    	if(steps[4][stepNum]==0) c = Color.YELLOWGREEN;
    	else if (steps[4][stepNum]==1) c = Color.RED;
    	else if (steps[4][stepNum]==2) c = Color.BLUEVIOLET;
    	else if(steps[4][stepNum]==3) c = Color.WHITE;
    	else if(steps[4][stepNum]==4) c = Color.YELLOW;
    	
    	if(steps[0][stepNum] == -1) {
    		// merge phase
    		drawAnElement((int) steps[1][stepNum], steps[2][stepNum], steps[3][stepNum], c);
    	}
    	else {
    		// divide phase
    		if(steps[4][stepNum]!=3) {
    			drawAnArray(Arrays.copyOfRange(cloneArr, (int) steps[0][stepNum], (int) steps[1][stepNum]+1), steps[2][stepNum], steps[3][stepNum], c);
    		}
    		else {
    			int len = (int) (steps[1][stepNum] - steps[0][stepNum] + 1);
    			for(int i= 0; i < (len); ++i)
    				drawAnElement(-1, steps[2][stepNum] + (i - len/2)*50, steps[3][stepNum], Color.WHITE);
    		}
    	}
    	progressField.setText(this.instructions[(int) steps[5][stepNum]]);
    }
       
    public void drawAnElement(int x, double X, double Y, Color c) {
    	String s;
    	if(x!=-1) s = Integer.toString(x);    		
    	else s = "";
    	
    	ElementShape stack = new ElementShape(s, X, Y, c);
   
    	arrayDisplayArea.getChildren().add(stack);
    }
    
    public void drawAnArray(int[] subarr, double midX, double startY, Color c) {
    	for(int i = 0; i < (subarr.length); i++)
    	{
			drawAnElement(subarr[i], midX + (i - subarr.length/2)*50, startY, c);
    	}	
    }
}