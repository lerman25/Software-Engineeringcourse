package server;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.Commands;
import common.Item;
import common.ItemInOrder;
import common.Massage;
import common.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
	    private TableView<Item> itemList;

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
	    private TableColumn<Item, String> itemName;
	    @FXML
	    private TableColumn<Item, Double> price;

	    @FXML
	    private Button cancel;


	    @FXML
	    void cancel(ActionEvent event) {
	    	//start cancellation process.
	    }
	    @FXML
	    void complain(ActionEvent event) {
	    	//need to think what parameters to send
	    	Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Complain.fxml"));
			try {
				Parent root = loader.load();
				ComplaintC cvc = loader.getController(); 
				cvc.setOrder(order);
				primaryStage.setTitle("Complain - Order ID - "+order.getID());
				primaryStage.setScene(new Scene(root, 600, 600));
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	public void loadOrder()
	{
		date.setText(order.getTime().toString());
		address.setText(order.getAddress());
		shipment.setText("TBD"); // maybe enum?
		deliveryTime.setText(order.getDeliveryTime().toString());
		orderID.setText(Integer.toString(order.getID()));
		phone.setText(Integer.toString(order.getReciverPhone()));
		reciver.setText(order.getReciverName());
		deliveryCost.setText(Integer.toString(order.getDeliveryCost()));
		totalCost.setText(Integer.toString(order.getTotalCost()));
		status.setText(order.getStatus().name());
		itemName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		price.setCellValueFactory(new PropertyValueFactory<>("Price"));
		itemList.setItems(null);
		itemList.setItems(get_list());
		itemList.getSelectionModel().setSelectionMode(
			    SelectionMode.MULTIPLE
			);



		

	}
	public ObservableList<Item> get_list()
	{
		Main.send_toServer(new Massage(order.getID(),Commands.GETITEMSORDER));
		Massage msg = server.Main.get_from_server();
		ItemInOrder iio = new ItemInOrder(-1, -1);
		if(msg.getCommand()!=Commands.DBERROR)
		 iio = (ItemInOrder)msg.getObject();
		ArrayList<Item> o = new ArrayList<Item>();
		if(iio!=null)
		 o =iio.getItemList();
		ObservableList<Item> item_list = FXCollections.observableArrayList();
		for(int i=0; i<o.size();i++)
		{
			item_list.add(o.get(i));
		}
		return item_list;
		
	}
	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}


}
