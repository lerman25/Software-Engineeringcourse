package server;

import java.util.ArrayList;
import java.util.Calendar;

import common.CStatus;
import common.Commands;
import common.Complaint;
import common.Massage;
import common.Orders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ComplaintC {

	@FXML
	private Label writeLabel;

	@FXML
	private Button addButton;

	@FXML
	private TextArea writeBox;

	private Orders order;
	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}

	private Stage thisStage;


	@FXML
	void add(ActionEvent event) {
		if (writeBox.getText().isBlank()) {
			writeLabel.setText(writeLabel.getText() + "!");
		} else {
			java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
			Main.send_toServer(new Massage("Orders", Commands.GETLASTID));
			Massage msg1 = Main.get_from_server();
			Complaint complaint = new Complaint(now, writeBox.getText(), order.getClientID(),CStatus.PENDING, order.getID(), 1);
			Main.send_toServer(new Massage(complaint, Commands.ADD));
			Massage msg = Main.get_from_server();
			if ( msg.getCommand()!=Commands.DBERROR )
				AlertBox.display("Complaint", "SUCCESS!");
			thisStage.close();


		}

	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders orderID) {
		this.order = orderID;
	}

}
