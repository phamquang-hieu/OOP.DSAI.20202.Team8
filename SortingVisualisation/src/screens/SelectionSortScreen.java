package screens;
import controllers.SelectionSortController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SelectionSortScreen extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/screens/MergeSortScreen.fxml"));
		loader.setController(new SelectionSortController());
		
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Selection Sort");
		stage.setScene(scene);
		stage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


}