package screens;

import controllers.ShellSortController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShellSortScreen extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/screens/SortScreen.fxml"));
		loader.setController(new ShellSortController());

		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Shell Sort");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}