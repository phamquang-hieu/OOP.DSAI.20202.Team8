package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public abstract class ScreenController {
	
    @FXML
    ToggleGroup visualForm;
    
    @FXML
    RadioButton formNode;
    
    @FXML
    RadioButton formBar;
    
    @FXML
    TextField progressField;

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
    

//    @FXML
//    TextField currentStep;
    
	
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