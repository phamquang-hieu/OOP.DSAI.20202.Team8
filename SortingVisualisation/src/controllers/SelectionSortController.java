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
	private int comparing = 0;
	private SelectionSort ss;
	private int step = 0;
	private int size;
	private int changeMin = 0;
	private double X;
	private double Y;
	private int[] originalArray;
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
			this.originalArray = arr.clone();
			stepShow.setTextFill(Color.WHITE);
			comparing = 0;
			step = 0;
			this.size = this.Arr.getLength();
			this.X = arrayDisplayArea.getWidth() / 2;
			this.Y = arrayDisplayArea.getHeight() / 2 - 40;
			arrayDisplayArea.getChildren().clear();
			if (formNode.isSelected()) {
				drawArray(arr, 0, -1, 0, X, Y);
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
				drawArray(arr, 0, -1, X, Y, -1);
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
			ss = new SelectionSort(this.arr, X, Y);
			ss.Sort();
			progressField.setText("Start Selection Sort!");
			stepShow.setText("" + stepNum + "/" + (ss.getStepNum() + 1));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	void btnNextPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();

		// case when we have a sub-step updating min;
		if (stepNum <= ss.getStepNum()) {
			stepNum += 1;
			stepShow.setText("" + stepNum + "/" + (ss.getStepNum() + 1));
			stepShow.setTextFill(Color.WHITE);

			if (changeMin == 1) {
				progressField.setText("Update the smallest value");
				if (formNode.isSelected()) {
					drawArray(ss.getSteps()[step], step, ss.getSteps()[step][ss.getMinIndex()[step][comparing]],
							comparing, X, Y);
				} else {
					drawArray(ss.getSteps()[step], step, comparing, X, Y, comparing);
				}
				if ((comparing == size - 1) && step < size - 1) {
					step += 1;
					comparing = step;
				} else {
					comparing += 1;
				}
				changeMin = 0;
			}

			// normal case
			else if (comparing < size - 1 && step < size) {
				if (comparing > step) {
					if (formNode.isSelected()) {
						drawArray(ss.getSteps()[step], step, ss.getSteps()[step][ss.getMinIndex()[step][comparing - 1]],
								comparing, X, Y);
					} else {
						drawArray(ss.getSteps()[step], step, comparing, X, Y, ss.getMinIndex()[step][comparing - 1]);
					}
					if (ss.getMinIndex()[step][comparing - 1] != ss.getMinIndex()[step][comparing]) {
						progressField.setText("Found a smaller value!");
						changeMin = 1;
					} else {
						progressField.setText("Compare to the smallest value");
						comparing += 1;
					}
				}

				else {
					if (comparing != 0) {
						progressField.setText("Move the smallest element to the last position of the sorted array."
								+ "\nContinue with the unsorted array!");
					} else {
						progressField.setText("Start finding the smallest element");
					}
					if (formNode.isSelected()) {
						drawArray(ss.getSteps()[step], step, ss.getSteps()[step][ss.getMinIndex()[step][comparing]],
								comparing, X, Y);
					} else {
						drawArray(ss.getSteps()[step], step, comparing, X, Y, ss.getMinIndex()[step][comparing]);
					}
					comparing += 1;
				}
			}

			else if ((comparing == size - 1) && step < size - 1) {
				if (formNode.isSelected()) {
					drawArray(ss.getSteps()[step], step, ss.getSteps()[step][ss.getMinIndex()[step][comparing - 1]],
							comparing, X, Y);
				} else {
					drawArray(ss.getSteps()[step], step, comparing, X, Y, ss.getMinIndex()[step][comparing - 1]);
				}
				if (ss.getMinIndex()[step][comparing - 1] != ss.getMinIndex()[step][comparing]) {
					progressField.setText("Found a smaller value!");
					changeMin = 1;
				} else {
					progressField.setText("Compare to the smallest value");
					step += 1;
					comparing = step;
				}

			}

			else if (step == size - 1) {
				progressField.setText("There is one value left!");
				if (formNode.isSelected()) {
					drawArray(ss.getSteps()[step], step, -1, -1, X, Y);
				} else {
					drawArray(ss.getSteps()[step], step, -1, X, Y, -1);
				}
				step += 1;
			} else {
				progressField.setText("Done sorting!");
				if (formNode.isSelected()) {
					drawArray(ss.getSteps()[step - 1], step, -1, -1, X, Y);
				} else {
					drawArray(ss.getSteps()[step - 1], step, -1, X, Y, -1);
				}
			}
		}

		else {
			progressField.setText("Done sorting!");
			if (formNode.isSelected()) {
				drawArray(ss.getSteps()[step - 1], step, -1, -1, X, Y);
			} else {
				drawArray(ss.getSteps()[step - 1], step, -1, X, Y, -1);
			}
		}
	}

	@FXML
	void btnBackPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();
		int tmp = this.stepNum;
		comparing = 0;
		step = 0;
		this.stepNum = 0;
		if (tmp <= 1) {
			if (formNode.isSelected()) {
				drawArray(this.originalArray, 0, -1, 0, X, Y);
			} else {
				drawArray(this.originalArray, 0, -1, X, Y, -1);
			}
			progressField.setText("Start Selection Sort!");
		}
		for (int i = 0; i < tmp - 1; i++) {
			btnNextPressed(event);
		}
	}

	@FXML
	void btnResetPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();

		stepNum = 0;
		comparing = 0;
		step = 0;
		stepShow.setText("" + stepNum + "/" + (ss.getStepNum() + 1));
		stepShow.setTextFill(Color.WHITE);

		if (formNode.isSelected()) {
			drawArray(this.originalArray, 0, -1, 0, X, Y);
		} else {
			drawArray(this.originalArray, 0, -1, X, Y, -1);
		}
		progressField.setText("Start Selection Sort!");
	}

	@FXML
	void btnSkipPressed(ActionEvent event) {
		arrayDisplayArea.getChildren().clear();
		progressField.setText("Done sorting!");
		step = size;
		comparing = size - 1;
		stepNum = this.ss.getStepNum() + 1;
		stepShow.setText("" + stepNum + "/" + (ss.getStepNum() + 1));
		stepShow.setTextFill(Color.WHITE);
		if (formNode.isSelected()) {
			drawArray(ss.getSteps()[step - 1], step, -1, -1, X, Y);
		} else {
			drawArray(ss.getSteps()[step - 1], step, -1, X, Y, -1);
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

	public void drawElement(int element, double X, double Y, Color c, Color c2) {
		ElementShape stack = new ElementShape(Integer.toString(element), X, Y, c, 14, c2);

		arrayDisplayArea.getChildren().add(stack);

	}

	public void drawElement(int height, Color c, double X, double Y) {

		ElementShape stack = new ElementShape(height, c, X, Y);
		arrayDisplayArea.getChildren().add(stack);
	}

	public void drawArray(int[] arr, int seperate, int minValue, int index, double X, double Y) {
		double arrayLength = 40 * (arr.length + 1) + 10 * (arr.length - 2);
		double startX = X - arrayLength / 2;
		double startY = Y - 20;
		for (int i = 0; i < seperate; i++) {
			drawElement(arr[i], startX + i * 50, startY, Color.web("#05141a"), Color.WHITE);
		}
		for (int i = seperate; i < arr.length; i++) {
			drawElement(arr[i], startX + 30 + i * 50, startY, Color.web("#ffbea3"), Color.BLACK);
		}
		if (minValue != -1) {
			drawElement(minValue, startX + 30 + index * 50, startY + 50, Color.web("#ffbea3"), Color.BLACK);
		}

	}

	public void drawArray(int[] arr, int seperate, int index, double X, double Y, int minIndex) {
		double arrayLength = 20 * arr.length + 5 * (arr.length - 1);
		double startX = X - arrayLength / 2;
		double startY = Y + 250;
		for (int i = 0; i < seperate; i++) {
			drawElement(arr[i] * 5, Color.web("#05141a"), startX + i * 25, startY);
		}
		for (int i = seperate; i < arr.length; i++) {
			drawElement(arr[i] * 5, Color.web("#ab93c9"), startX + i * 25, startY);
		}
		if (minIndex != -1) {
			drawElement(arr[minIndex] * 5, Color.web("#50435d"), startX + minIndex * 25, startY);
		}
		if (index != -1 && minIndex != index) {
			drawElement(arr[index] * 5, Color.web("#ffbea3"), startX + index * 25, startY);
		}
	}

}