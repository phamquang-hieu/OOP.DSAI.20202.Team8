package screens;

import javax.swing.JOptionPane;

import controllers.MainScreenController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class MainScreen extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/screens/MainScreen.fxml"));
		loader.setController(new MainScreenController());
		
		
		stage.setOnCloseRequest(null);
		
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Sorting Visualization");
		stage.setScene(scene);
		Window w = scene.getWindow();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				int x = JOptionPane.showConfirmDialog(null, "Are you sure?", "Quit", 
						JOptionPane.YES_NO_OPTION);
				if(x==1 || x ==-1)
					e.consume();
					
			}
		});
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
