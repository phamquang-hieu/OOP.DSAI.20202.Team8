package controllers;

import java.io.IOException;

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
    	return;
    }

    @FXML
    void btnHelpPressed(ActionEvent event) {
    	return;
    }

    @FXML
    void btnMergeSortPressed(ActionEvent event) throws IOException {
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/Screens/SortScreen.fxml"));
    	loader.setController(new MergeSortScreenController());
    	Parent root = loader.load();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);	
    }


    @FXML
    void btnShellSortPressed(ActionEvent event) {

    }


    @FXML
    void btnSelectionSortPressed(ActionEvent event) throws IOException {
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/Screens/SortScreen.fxml"));
    	loader.setController(new SelectionSortController());
    	Parent root = loader.load();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    }

}