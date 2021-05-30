package controllers;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainScreenController {

	@FXML
	private Button btnQuit;

	@FXML
	private Button btnMergeSort;

	@FXML
	private Button btnShellSort;

	@FXML
	private Button btnHelp;

	@FXML
	private Button btnSelectionSort;

	@FXML
	void btnQuitPressed(ActionEvent event) {
		int x = JOptionPane.showConfirmDialog(null, "Are you sure?", "Quit", 
				JOptionPane.YES_NO_OPTION);
		if(x==1 || x==-1)
			event.consume();
		else {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		}	
	}

	@FXML
	void btnHelpPressed(ActionEvent event) {
		HelpMenu.showFrame();
	}

	@FXML
	void btnMergeSortPressed(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Screens/SortScreen.fxml"));
		loader.setController(new MergeSortController());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Merge Sort");
		stage.setScene(scene);
	}

	@FXML
	void btnShellSortPressed(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Screens/SortScreen.fxml"));
		loader.setController(new ShellSortController());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Shell Sort");
		stage.setScene(scene);
	}

	@FXML
	void btnSelectionSortPressed(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Screens/SortScreen.fxml"));
		loader.setController(new SelectionSortController());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Selection Sort");
		stage.setScene(scene);
	}

}