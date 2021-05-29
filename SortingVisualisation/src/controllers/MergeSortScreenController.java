package controllers;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import shapes.ElementShape;
import shapes.TestElementShape;

import java.util.Arrays;

import javax.swing.JOptionPane;

import algorithms.MergeSort;
import datastructure.Array;

public class MergeSortScreenController extends SortScreenController{
	private Array arr;
	private int[] cloneArr;
	private double firstLine;
	private int stepNum = 0;
	private int numStep;
	private double steps[][];
	private String[] instructions;
	private SequentialTransition sq;
	
	@FXML
	public void initialize() {
		instructions = new String[6];
		instructions[0] = "Split the selected array (as evenly as possible)";
		instructions[1] = "Select the left sub-array and ready to merge";
		instructions[2] = "Select the right sub-array and ready to merge";
		instructions[3] = "Select the minimum of the two selected values \nto put into the sorted array";
		instructions[4] = "All the elements from the left array has been taken, \ncopy all values from the right array to the sorted array";
		instructions[5] = "All the elements from the right array has been taken, \ncopy all values from the left array to the sorted array";
		progressField.setEditable(false);
		progressField.setFont(new Font("Arial", 15));
		this.sq = new SequentialTransition();
	}
	
    void randomArray() {
    	this.arr = new Array(getLength(arraySize5, arraySize6, arraySize7, arraySize8));
    }
    
    @FXML
    void buttonRunPressed(ActionEvent event) throws Exception {
    	try {
			if (randomizeMode.isSelected())
				randomArray();
			else {
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
    void btnBackPressed(ActionEvent event) {
    	arrayDisplayArea.getChildren().clear();
		this.progressField.clear();	
    	int tmp = this.stepNum;
    	if(tmp==0) tmp = this.numStep+1;
    	for(this.stepNum =0; stepNum < tmp-1; stepNum++) {
    		displayStep(stepNum, 0);
    	}
    }
    
    @FXML
   	void btnNextPressed(ActionEvent event) {
	   if(this.arr == null || this.arr.getLength()==0)
		   return;       // no element initiated
	   if(this.stepNum == this.numStep) {
		   showLastView();
		   return;
	   }
	  
	   displayStep(this.stepNum, 0);
	   this.stepNum++;
    }
    
    @FXML
    void btnAutoPressed(ActionEvent event) {
    	this.stepNum = 0;
    	sq.getChildren().clear();
    	this.arrayDisplayArea.getChildren().clear();
    	for(; this.stepNum < this.numStep; this.stepNum++) {
    		displayStep(this.stepNum, 1);
    	}
    	sq.play();
    }
    
    @FXML
    void btnResetPressed(ActionEvent event) {
    	resetView();
    }
    void resetView() {
    	arrayDisplayArea.getChildren().clear();
		this.progressField.clear();	
		this.firstLine = 50;
		drawAnArray(cloneArr, this.arrayDisplayArea.getWidth() / 2, firstLine, Color.YELLOWGREEN, 0, "");
		this.stepNum = 0;
    }
    
    @FXML
    void btnSkipPressed(ActionEvent event) {
    	showLastView();
    }
    
    void showLastView() {
    	arrayDisplayArea.getChildren().clear();
    	this.firstLine = 50;
    	drawAnArray(this.arr.data, this.arrayDisplayArea.getWidth() / 2, firstLine, Color.YELLOWGREEN, 0, "");
    	this.progressField.setText("Done Sorting!");
    	this.stepNum = 0;
    }
    
    private Color getColor(double x) {
    	Color c = Color.YELLOWGREEN;
    	if(x ==0) c = Color.YELLOWGREEN;
    	else if (x==1) c = Color.RED;
    	else if (x==2) c = Color.BLUEVIOLET;
    	else if(x==3) c = Color.WHITE;
    	else if(x==4) c = Color.YELLOW;
    	return c;
    }
    
    private void displayStep(int stepNum, int flag) {
    	Color c= getColor(steps[4][stepNum]);
    	
    	String instruction = this.instructions[(int) steps[5][stepNum]];
    	if(steps[0][stepNum] == -1) {
    		// merge phase
    		sq.getChildren().add(drawAnElement((int) steps[1][stepNum], steps[2][stepNum], steps[3][stepNum], c, 21, flag, instruction));
    	}
    	else {
    		// divide phase
    		if(steps[4][stepNum]!=3) {
    			sq.getChildren().add(drawAnArray(Arrays.copyOfRange(cloneArr, (int) steps[0][stepNum], 
    								(int) steps[1][stepNum]+1), 
    								steps[2][stepNum], steps[3][stepNum], 
    								c, 
    								flag, 
    								instruction));
    			
    			if(c == Color.YELLOWGREEN) {
        			sq.getChildren().add(
        					drawAnArray(Arrays.copyOfRange(cloneArr, (int) steps[0][stepNum+1], 
        							(int) steps[1][stepNum+1]+1), steps[2][stepNum+1], 
        							steps[3][stepNum+1], 
        							c, 
        							flag, 
        							instruction));
        			this.stepNum +=1;
    			}
    		}
    		else {
    			int len = (int) (steps[1][stepNum] - steps[0][stepNum] + 1);
    			for(int i= 0; i < (len); ++i)
    				sq.getChildren().add(drawAnElement(-1, steps[2][stepNum] + (i - len/2)*50, steps[3][stepNum], Color.WHITE, 21, flag, instruction));
    		}
    	}
    	if(flag == 0) progressField.setText(instruction);
    }
       
    public FadeTransition drawAnElement(int x, double X, double Y, Color c, int Font, int flag, String instruction) {
    	String s;
    	if(x!=-1) s = Integer.toString(x);    		
    	else s = "";
    	
    	TestElementShape stack = new TestElementShape(s, X, Y, c, 21, Color.BLACK, instruction, progressField);
    	
    	arrayDisplayArea.getChildren().add(stack);
    	if(flag==1) {
    		stack.setVisible(false);
    	}
    	return stack.display();
    }
    
    public ParallelTransition drawAnArray(int[] subarr, double midX, double startY, Color c, int flag, String instruction) {
    	ParallelTransition pt = new ParallelTransition();
    	for(int i = 0; i < (subarr.length); i++)
    	{
			pt.getChildren().add(drawAnElement(subarr[i], midX + (i - subarr.length/2)*50, startY, c, 21, flag, instruction));
    	}
		return pt;	
    }
}