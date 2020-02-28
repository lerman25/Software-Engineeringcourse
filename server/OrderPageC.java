package server;


import java.net.URL;
import java.util.ResourceBundle;

import common.Orders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
public class OrderPageC implements Initializable {
	private Orders order;

	 @FXML
	    private TextField date;

	    @FXML
	    private TextField address;

	    @FXML
	    private TextField shipment;

	    @FXML
	    private TextField deliveryTime;

	    @FXML
	    private TextField orderID;

	    @FXML
	    private TextField phone;

	    @FXML
	    private ListView<?> itemList;

	    @FXML
	    private Button complain;

	    @FXML
	    private TextField reciver;

	    @FXML
	    private TextField totalCost;

	    @FXML
	    private TextField deliveryCost;

	    @FXML
	    private TextField status;

	    @FXML
	    void complain(ActionEvent event) {

	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	public void loadOrder()
	{
		date.setText(order.getTime().toString());
		address.setText(order.getAddress());
		shipment.setText("TBD");
		deliveryTime.setText(order.getDeliveryTime().toString());
		orderID.setText(Integer.toString(order.getID()));
		phone.setText(Integer.toString(order.getReciverPhone()));
		reciver.setText(order.getReciverName());
		deliveryCost.setText(Integer.toString(order.getDeliveryCost()));
		totalCost.setText(Integer.toString(order.getTotalCost()));
		status.setText(Integer.toString(order.getStatus()));


		

	}
	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}


}
