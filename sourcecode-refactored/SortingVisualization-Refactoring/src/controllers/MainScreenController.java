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
	void btnQuitPressed(ActionEvent event) {
		int x = JOptionPane.showConfirmDialog(null, "Are you sure?", "Quit", JOptionPane.YES_NO_OPTION);
		if (x == 1 || x == -1)
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
	void btnSortPressed(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/Screens/SortScreen.fxml"));
		loader.setController(new SortController(((Button) event.getSource()).getText()));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setTitle(((Button) event.getSource()).getText());
		stage.setScene(scene);
	}

}