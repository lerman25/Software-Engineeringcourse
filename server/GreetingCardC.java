package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class GreetingCardC {

    @FXML
    private Label writeLabel;

    @FXML
    private Button addButton;

    @FXML
    private TextArea writeBox;

	private Stage thisStage;

    private PayPage ppc = null;
    
    
    @FXML
    void add(ActionEvent event) {
    	if(writeBox.getText().isBlank())
    	{
    		writeLabel.setText(writeLabel.getText()+"!");
    	}
    	else
    	{
    		ppc.setgFlag(true);
    		ppc.setGreetingS(writeBox.getText());
            AlertBox.display("Greeting Add","SUCCESS!");
            thisStage.close();
    		//send to server writeBox.getText();
    		// OR add to order or send back to PayPage or something like that
    	}

    }


	public PayPage getPpc() {
		return ppc;
	}


	public void setPpc(PayPage ppc) {
		this.ppc = ppc;
	}


	public Stage getThisStage() {
		return thisStage;
	}


	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}
    
    
}

  





