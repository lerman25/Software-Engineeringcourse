package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class GreetingCardC {

    @FXML
    private Label writeLabel;

    @FXML
    private Button addButton;

    @FXML
    private TextArea writeBox;

    
    
    @FXML
    void add(ActionEvent event) {
    	if(writeBox.getText().isBlank())
    	{
    		writeLabel.setText(writeLabel.getText()+"!");
    	}
    	else
    	{
    		//send to server writeBox.getText();
    		// OR add to order or send back to PayPage or something like that
    	}

    }
    
    
}

  





