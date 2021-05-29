package controllers;

import java.util.Arrays;

import javax.swing.JOptionPane;

import algorithms.SelectionSort;
import datastructure.Array;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import shapes.ElementShape;

public class SelectionSortController extends SortScreenController {

	private Array Arr;
	private int[] arr;
	private SelectionSort ss;
	private double X;
	private double Y;
	private int stepNum = 0;

	void randomArray() {
		arrayDisplayArea.getChildren().clear();
		this.Arr = new Array(getLength());
		this.arr = Arrays.copyOf(this.Arr.data, this.Arr.getLength());
	}

	@FXML
	void buttonRunPressed(ActionEvent event) throws Exception {
		try {
			if (randomizeMode.isSelected()) {
				randomArray();
			} else {
				arrayDisplayArea.getChildren().clear();
				this.Arr = new Array(textFieldArray.getText());
				this.arr = Arrays.copyOf(this.Arr.data, this.Arr.getLength());
			}
			stepShow.setTextFill(Color.WHITE);
			this.X = arrayDisplayArea.getWidth() / 2;
			this.Y = arrayDisplayArea.getHeight() / 2 - 40;
			arrayDisplayArea.getChildren().clear();
			ss = new SelectionSort(this.arr, X, Y);
			ss.Sort();
			if (formNode.isSelected()) {
				arrayDisplayArea.getChildren().addAll(ss.getStaticNodes()[0]);
				sortedLabel.setVisible(true);
				unsortedLabel.setVisible(true);
				sortedNode.setVisible(true);
				unsortedNode.setVisible(true);
				sortedName.setVisible(false);
				unsortedName.setVisible(false);
				smallestName.setVisible(false);
				currentName.setVisible(false);
				sortedBar.setVisible(false);
				unsortedBar.setVisible(false);
				smallestBar.setVisible(false);
				currentBar.setVisible(false);
			}

			else {
				arrayDisplayArea.getChildren().addAll(ss.getStaticBars()[0]);
				sortedLabel.setVisible(false);
				unsortedLabel.setVisible(false);
				sortedNode.setVisible(false);
				unsortedNode.setVisible(false);
				sortedName.setVisible(true);
				unsortedName.setVisible(true);
				smallestName.setVisible(true);
				currentName.setVisible(true);
				sortedBar.setVisible(true);
				unsortedBar.setVisible(true);
				smallestBar.setVisible(true);
				currentBar.setVisible(true);

			}

			progressField.setText("Start Selection Sort!");
			stepNum = 0;
			stepShow.setText("" + (stepNum ) + "/" + (ss.getAuto()-1));
			stepNum += 1;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	void btnNextPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();
		stepShow.setText("" + (stepNum) + "/" + (ss.getAuto()-1));
		progressField.setText(ss.getExplanation()[stepNum]);

		
		if (stepNum < ss.getAuto()) {
			if (formNode.isSelected()) {
				arrayDisplayArea.getChildren().addAll(ss.getStaticNodes()[stepNum]);
			} 
			else {
				arrayDisplayArea.getChildren().addAll(ss.getStaticBars()[stepNum]);
			}
			stepNum += 1;

		}
		else {
			stepShow.setText("" + (ss.getAuto() -1) + "/" + (ss.getAuto()-1));

			if (formNode.isSelected()) {
				arrayDisplayArea.getChildren().addAll(ss.getStaticNodes()[ss.getAuto()-1]);
			} 
			else {
				arrayDisplayArea.getChildren().addAll(ss.getStaticBars()[ss.getAuto()-1]);
			}
		}
	}

	@FXML
	void btnBackPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();
		if (stepNum <= 1) {
			stepShow.setText("" + (0) + "/" + (ss.getAuto()-1));
			stepNum = 0;
			if (formNode.isSelected()) {
				arrayDisplayArea.getChildren().addAll(ss.getStaticNodes()[0]);
			} 
			else {
				arrayDisplayArea.getChildren().addAll(ss.getStaticBars()[0]);
			}
			progressField.setText("Start Selection Sort!");
		}
		else {
			if (formNode.isSelected()) {
				arrayDisplayArea.getChildren().addAll(ss.getStaticNodes()[stepNum - 2]);
			} 
			else {
				arrayDisplayArea.getChildren().addAll(ss.getStaticBars()[stepNum - 2]);
			}
			progressField.setText(ss.getExplanation()[stepNum-2]);

			stepNum -= 1;
			stepShow.setText("" + (stepNum - 1) + "/" + (ss.getAuto()-1));

		}
	}

	@FXML
	void btnResetPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();

		stepNum = 1;
		stepShow.setText("" + 0 + "/" + (ss.getAuto()-1));
		stepShow.setTextFill(Color.WHITE);

		if (formNode.isSelected()) {
			arrayDisplayArea.getChildren().addAll(ss.getStaticNodes()[0]);
		} 
		else {
			arrayDisplayArea.getChildren().addAll(ss.getStaticBars()[0]);
		}
		progressField.setText("Start Selection Sort!");
	}

	@FXML
	void btnSkipPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();
		progressField.setText("Done sorting!");
		stepNum = this.ss.getAuto();
		stepShow.setText("" + (ss.getAuto()-1) + "/" + (ss.getAuto()-1));
		stepShow.setTextFill(Color.WHITE);
		if (formNode.isSelected()) {
			arrayDisplayArea.getChildren().addAll(ss.getStaticNodes()[stepNum-1]);
		} 
		else {
			arrayDisplayArea.getChildren().addAll(ss.getStaticBars()[stepNum-1]);
		}
	}

	@FXML
	void buttonAutoPressed(ActionEvent event) throws InterruptedException {
		arrayDisplayArea.getChildren().clear();
		sortedLabel.setVisible(false);
		unsortedLabel.setVisible(false);
		sortedNode.setVisible(false);
		unsortedNode.setVisible(false);
		sortedName.setVisible(true);
		unsortedName.setVisible(true);
		smallestName.setVisible(true);
		currentName.setVisible(true);
		sortedBar.setVisible(true);
		unsortedBar.setVisible(true);
		smallestBar.setVisible(true);
		currentBar.setVisible(true);

		arrayDisplayArea.getChildren().addAll(ss.getElems());
		stepShow.setText("");
		progressField.setText("");

		SequentialTransition sq = new SequentialTransition();

		for (int i = 0; i < 100; i++) {
			if (ss.getTransitions()[i] != null) {
				sq.getChildren().add(ss.getTransitions()[i]);
			}

		}

		sq.play();
	}

}