package selectionsort;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SelectionSortScreen extends Application{
	
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass()
				.getResource("/selectionsort/SelectionSortScreen.fxml"));
		
		Scene scene = new Scene(root);
		stage.setTitle("Selection Sort Screen");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
