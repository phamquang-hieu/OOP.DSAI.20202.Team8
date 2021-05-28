package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public abstract class SortScreenController {
	
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
    protected void closeMenuPressed(ActionEvent event) throws IOException {
    	Stage stage = (Stage) arrayDisplayArea.getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/Screens/MainScreen.fxml"));
    	loader.setController(new MainScreenController());
    	Parent root = loader.load();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);	
    }
    
    
	protected int getLength(RadioButton arraySize5, RadioButton arraySize6, RadioButton arraySize7, RadioButton arraySize8) {
    	int len=0;
    	if(arraySize5.isSelected()) {
    		len = 5;
    	}else if(arraySize6.isSelected()) {
    		len = 6 ;
    	} else if(arraySize7.isSelected()) {
    		len = 7;
    	} else if(arraySize8.isSelected()) {
    		len = 8;
    	}
    	return len;
    }
}