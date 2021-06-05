package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import algorithms.*;
import algorithms.SortAlgorithm;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SortController implements Initializable {
	String sortType;
	SortAlgorithm sort;
	boolean resetFlag = false;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progressField.setEditable(false);
		stepShow.setTextFill(Color.WHITE);
		if (sortType.equals("Shell Sort")) {
			sortedLabel.setVisible(true);
			sortedNode.setVisible(true);
			unsortedLabel.setVisible(true);
			unsortedNode.setVisible(true);
			selectingLabel.setVisible(true);
			selectingNode.setVisible(true);
		}
		if (sortType.equals("Merge Sort")) {
			formBar.setVisible(false);
		}
	}

	public SortController(String sortType) {
		this.sortType = sortType;
	}

	@FXML
	ToggleGroup visualForm;

	@FXML
	RadioButton formNode;

	@FXML
	RadioButton formBar;

	@FXML
	TextArea progressField;

	@FXML
	ToggleGroup arraySize;

	@FXML
	TextField textFieldArray;

	@FXML
	RadioButton randomizeMode;

	@FXML
	ToggleGroup createArray;

	@FXML
	RadioButton arraySize7;

	@FXML
	RadioButton arraySize6;

	@FXML
	RadioButton arraySize8;

	@FXML
	RadioButton arraySize5;

	@FXML
	RadioButton customizeMode;

	@FXML
	Pane arrayDisplayArea;

	@FXML
	Menu menuBack;

	@FXML
	Menu menuHelp;

	@FXML
	Label sortedLabel;

	@FXML
	Label unsortedLabel;

	@FXML
	Rectangle sortedNode;

	@FXML
	Rectangle unsortedNode;

	@FXML
	Label sortedName;

	@FXML
	Label smallestName;

	@FXML
	Label unsortedName;

	@FXML
	Label currentName;

	@FXML
	Rectangle sortedBar;

	@FXML
	Rectangle smallestBar;

	@FXML
	Rectangle unsortedBar;

	@FXML
	Rectangle currentBar;

	@FXML
	Pane notePane;

	@FXML
	Label stepShow;

	@FXML
	Button btnSkip;

	@FXML
	Button btnBack;

	@FXML
	Button btnNext;

	@FXML
	Button btnReset;

	@FXML
	Button btnAuto;

	@FXML
	Label selectingLabel;

	@FXML
	Rectangle selectingNode;

	@FXML
	protected void backMenuPressed(ActionEvent event) throws IOException {
		Stage stage = (Stage) arrayDisplayArea.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Screens/MainScreen.fxml"));
		loader.setController(new MainScreenController());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Sorting Visualization");
		stage.setScene(scene);
	}

	@FXML
	protected void helpMenuPressed(ActionEvent event) {
		HelpMenu.showFrame();
	}

	@FXML
	void buttonRunPressed(ActionEvent event) throws Exception {
		int n = 0;
		int[] arr;
		try {
			if (randomizeMode.isSelected()) {
				n = getLength();
				Random rand = new Random();
				arr = new int[n];
				for (int i = 0; i < n; i++) {
					arr[i] = rand.nextInt(80);
				}
			} else {
				String s = textFieldArray.getText().trim();
				if (s.isEmpty()) {
					throw new NullPointerException("Please input 5->8 element or switch to randomize mode");
				}
				String[] arrString = s.split(",");
				arr = new int[arrString.length];
				for (n = 0; n < arrString.length; ++n) {
					try {
						arr[n] = Integer.parseInt(arrString[n].trim());
						if (arr[n] < 0)
							throw new NumberFormatException("Array element have to be positive!");
					} catch (NumberFormatException e) {
						throw e;
					}
				}
				if (n > 8 || n < 5) {
					throw new Exception("Your array length: " + n + ". Please input 5->8 element only");
				}
			}
			if (sortType.equals("Shell Sort")) {
				sort = new ShellSort(arr, arrayDisplayArea, progressField, formNode.isSelected() ? "Node" : "Bar");
			} else if (sortType.equals("Merge Sort")) {
				sort = new MergeSort(arr, arrayDisplayArea, progressField, formNode.isSelected() ? "Node" : "Bar");
			} else if (sortType.equals("Selection Sort")) {
				sort = new SelectionSort(arr, arrayDisplayArea, progressField, formNode.isSelected() ? "Node" : "Bar");
			}
			stepShow.setText("" + sort.getCurSteps() + "/" + sort.getNumSteps());
			sort.displayStartScreen();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	void btnNextPressed(ActionEvent event) {
		sort.nextStep();
		stepShow.setText("" + sort.getCurSteps() + "/" + sort.getNumSteps());
	}

	@FXML
	void btnBackPressed(ActionEvent event) {
		sort.previousStep();
		stepShow.setText("" + sort.getCurSteps() + "/" + sort.getNumSteps());

	}

	@FXML
	void btnResetPressed(ActionEvent event) {
		sort.reset();
		stepShow.setText("" + sort.getCurSteps() + "/" + sort.getNumSteps());
		resetFlag = true;
	}

	@FXML
	void btnSkipPressed(ActionEvent event) {
		sort.displayFinishScreen();
		stepShow.setText("" + sort.getCurSteps() + "/" + sort.getNumSteps());
	}

	@FXML
	void buttonAutoPressed(ActionEvent event) {
		if (sort.getCurSteps() == sort.getNumSteps()) {
			sort.displayFinishScreen();
			return;
		}
		if(resetFlag) {
			resetFlag = false;
			return;
		}
		btnNextPressed(new ActionEvent());
		Circle s = new Circle(0, 0, 3);
		arrayDisplayArea.getChildren().add(s);
		s.setFill(Color.TRANSPARENT);
		s.setStroke(Color.TRANSPARENT);


		Path path = new Path();
		path.getElements().add(new MoveTo(0, 0));
		path.getElements().add(new LineTo(arrayDisplayArea.getWidth(), 0));

		PathTransition delay = new PathTransition();
		delay.setDelay(Duration.seconds(0));
		delay.setDuration(Duration.seconds(0.5));
		delay.setNode(s);
		delay.setPath(path);
		delay.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent actionEvent) {
				buttonAutoPressed(new ActionEvent());
			}
		});
		delay.play();
	}

	protected int getLength() {
		if (arraySize5.isSelected())
			return 5;
		if (arraySize6.isSelected())
			return 6;
		if (arraySize7.isSelected())
			return 7;
		if (arraySize8.isSelected())
			return 8;
		return 5;
	}

}