package server;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import common.Commands;
import common.Item;
import common.ItemInOrder;
import common.Massage;
import common.Orders;
import common.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class PayPage implements Initializable {

	@FXML
	private DatePicker deliveryTime;

	@FXML
	private Button greeting;

	@FXML
	private TextField rNameF;

	@FXML
	private Label greetingLabel;

	@FXML
	private ToggleButton notme;

	@FXML
	private TextField rAddressF;

	@FXML
	private ChoiceBox<String> shipmentMethod;

	@FXML
	private ChoiceBox<Integer> min;

	@FXML
	private Label rAddress;

	@FXML
	private ChoiceBox<Integer> hour;

	@FXML
	private Label rName;

	@FXML
	private Label rPhone;
	@FXML
	private TextArea greetingBox;
	@FXML
	private TextField rPhoneF;
	@FXML
	private Button complete;

	@FXML
	private Label notmeL;
	private String greetingS = "";
	private boolean gFlag = false;
	private Item selected;
	private boolean toggleFlag = true;
	private java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
	private Stage thisStage;
	private LocalDate delTime = null;

	@FXML
	void greeting(ActionEvent event) {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GreetingCard.fxml"));
		try {
			Parent root = loader.load();
			GreetingCardC cvc = loader.getController();
			cvc.setPpc(this);
			cvc.setThisStage(primaryStage);
			primaryStage.setTitle("Greeting Card");
			primaryStage.setScene(new Scene(root, 600, 600));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void notme(ActionEvent event) {

		if (toggleFlag) {
			rAddress.setVisible(true);
			rAddressF.setVisible(true);
			rPhone.setVisible(true);
			rPhoneF.setVisible(true);
			rName.setVisible(true);
			rNameF.setVisible(true);
			toggleFlag = false;
		} else {
			rAddress.setVisible(false);
			rAddressF.setVisible(false);
			rPhone.setVisible(false);
			rPhoneF.setVisible(false);
			rName.setVisible(false);
			rNameF.setVisible(false);
			toggleFlag = true;

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		shipmentMethod.getItems().add("Self Pickup");
		shipmentMethod.getItems().add("Delivery");
		for (int i = 0; i < 24; i++)
			hour.getItems().add(i);
		for (int i = 0; i < 60; i++)
			min.getItems().add(i);
		greetingBox.setVisible(false);
		setDelTime(LocalDate.now());
		hour.setValue(12);
		min.setValue(0);
		shipmentMethod.setValue(shipmentMethod.getItems().get(0));
	}

	public Item getSelected() {
		return selected;
	}

	public void setSelected(Item selected) {
		this.selected = selected;
	}

	@FXML
	void complete(ActionEvent event) {
		// check if
		Timestamp del = new java.sql.Timestamp(System.currentTimeMillis());
		LocalTime delHour = LocalTime.of(hour.getValue(), min.getValue());
		LocalDateTime delLDT = LocalDateTime.of(delTime, delHour);
		Person reciver = Main.getPerson();
		del = Timestamp.valueOf(delLDT);
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(selected);
		ItemInOrder _item = new ItemInOrder(items, 0, reciver.getId());
		Orders newOrder = new Orders(reciver.getId(), now, del, shipmentMethod.getSelectionModel().getSelectedIndex(),
				reciver.getAddress(), reciver.getPhone_number(), reciver.getFirstName() + " " + reciver.getLastName(),
				del);
		if (!toggleFlag) {
			// this needs parsing!!!!!
			newOrder.setAddress(rAddressF.getText());
			newOrder.setReciverName(rNameF.getText());
			newOrder.setReciverPhone(Integer.parseInt(rPhoneF.getText()));
		}
		Main.send_toServer(new Massage("Orders", Commands.GETLASTID));
		Massage msg = Main.get_from_server();
		boolean idflag = msg.getCommand() != Commands.DBERROR;
		Main.send_toServer(new Massage("ItemInOrder", Commands.GETLASTID));
		Massage msg2 = Main.get_from_server();
		boolean iioIdflag = msg2.getCommand() != Commands.DBERROR;
		if (iioIdflag && idflag) {
			newOrder.setID((int) msg.getObject() + 1);
			_item.setID((int) msg2.getObject() + 1);
			_item.setOrderID(newOrder.getID());
			newOrder.setItemList(_item);
			if (gFlag)
				newOrder.setGreeting(getGreetingS());
			Main.send_toServer(new Massage(newOrder, Commands.ADD));
			Massage msg3 = Main.get_from_server();
			if((boolean)msg3.getObject()==true)
				AlertBox.display("Payment", "SUCCESS!");
			else
				AlertBox.display("Payment", "FAILURE! , Please try again.");;
			thisStage.close();
		}

	}

	public String getGreetingS() {
		return greetingS;
	}

	public void setGreetingS(String greetingS) {
		greetingBox.setText(greetingS);
		greetingBox.setVisible(true);
		this.greetingS = greetingS;
	}

	public boolean getgFlag() {
		return gFlag;
	}

	@FXML
	void datePick(ActionEvent event) {
		// make sure date is after today and now.
		setDelTime(deliveryTime.getValue());
	}

	public void setgFlag(boolean gFlag) {
		if (gFlag)
			greetingLabel.setText("Greeting added");

		this.gFlag = gFlag;
	}

	public LocalDate getDelTime() {
		return delTime;
	}

	public void setDelTime(LocalDate delTime) {
		this.delTime = delTime;
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}
}
