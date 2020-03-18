package server;

import java.util.ArrayList;
import java.util.Calendar;

import common.Commands;
import common.Complaint;
import common.Massage;
import common.Orders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ComplaintC {

    @FXML
    private Label writeLabel;

    @FXML
    private Button addButton;

    @FXML
    private TextArea writeBox;

    private Orders order;
    
    @FXML
    void add(ActionEvent event) {
    	if(writeBox.getText().isBlank())
    	{
    		writeLabel.setText(writeLabel.getText()+"!");
    	}
    	else
    	{
    		java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

    		Complaint complaint = new Complaint(now,writeBox.getText(),order.getClientID() ,1, order.getID(), 1);
    		Main.send_toServer(new Massage (complaint, Commands.COMPLAIN));
    		Massage msg = Main.get_from_server();
    		if(msg.getCommand()!=Commands.DBERROR)
    	      AlertBox.display("Complaint","SUCCESS!");
    		
    	}

    }

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders orderID) {
		this.order = orderID;
	}
    
    
}

  





