package server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.Item;
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
	private TextField rPhoneF;
    @FXML
    private Button complete;

	private Item selected;
	private boolean toggleFlag = true;

	@FXML
	void greeting(ActionEvent event) {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GreetingCard.fxml"));
		try {
			Parent root = loader.load();
			GreetingCardC cvc = loader.getController();
			primaryStage.setTitle("Greeting Card");
			primaryStage.setScene(new Scene(root, 600, 600));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//if greeting not empty
		greetingLabel.setText("Greeting added");

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
			toggleFlag=false;
		} else {
			rAddress.setVisible(false);
			rAddressF.setVisible(false);
			rPhone.setVisible(false);
			rPhoneF.setVisible(false);
			rName.setVisible(false);
			rNameF.setVisible(false);
			toggleFlag=true;

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

	}

	public Item getSelected() {
		return selected;
	}

	public void setSelected(Item selected) {
		this.selected = selected;
	}
    @FXML
    void complete(ActionEvent event) {

    }
}
