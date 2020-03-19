package server;

import common.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//import server.FormValidation;
//import server.Main;
import server.Main;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
	@FXML
	Label username;
	@FXML
	Button logout;
	@FXML
	Button EditEmployees;
	@FXML
	AnchorPane EmployeeListAnchor;
	@FXML
	TableView<Orders> tableOrders;
	@FXML
	TableColumn<Orders, Integer> orderID;
	@FXML
	TableColumn<Orders, Integer> clientID;
	@FXML
	TableColumn<Orders, Timestamp> orderDate;
	@FXML
	TableColumn<Orders, String> clientAdress;
	@FXML
	TableColumn<Orders, Integer> clientPhone;
	@FXML
	TableColumn<Orders, String> clientName;
	@FXML
	TableColumn<Orders, Timestamp> deliveryTime;
	@FXML
	TableColumn<Orders, Integer> deliveryCost;
	@FXML
	TableColumn<Orders, Integer> totalCost;
	@FXML
	TableColumn<Orders, String> status;
	@FXML
	Tab renderOrders;

	@FXML
	private Button itemAdd;
	@FXML
	private Button delivered;

	@FXML
	private Button sale;

	@FXML
	private Button itemRemove;
	@FXML
	private TableColumn<Item, Integer> itemPrice;
	@FXML
	private TableView<Item> items;

	@FXML
	private TableColumn<Item, String> item;

	@FXML
	private Tab catalog;

	@FXML
	private Button mail;
    @FXML
    private Button pending;


	private ObservableList<Orders> ordersList;
	private ObservableList<Employee> data1;
	private Stage thisStage;
	int penCounter;

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		username.setText(Main.getPerson().getUsername());
		if (Main.getPermission().ordinal() < Permissions.SHOPMANAGER.ordinal())
			EditEmployees.setDisable(true);
		penCounter=1;
	}

	@FXML
	void renderCatalog() {
		System.out.println("Render Catalog!");
		item.setCellValueFactory(new PropertyValueFactory<>("Name"));
		itemPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		items.setItems(null);
		items.setItems(get_ItemList());
		items.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}

	@FXML
	void renderOrders() {
		Massage msg = new Massage();
		msg.setCommand(Commands.GETORDERS);
		ordersList = FXCollections.observableArrayList();
		server.Main.send_toServer(msg);
		msg = server.Main.get_from_server();
		ArrayList<Orders> orders = new ArrayList<Orders>();
		if (msg.getCommand() != Commands.DBERROR)
			orders = (ArrayList<Orders>) msg.getObject();
		boolean pendingF = false;
		for (int i = 0; i < orders.size(); i++)
		{
			if(orders.get(i).getStatus()==OStatus.PENDING)
				pendingF=true;
			ordersList.add(orders.get(i));
		}
		orderID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		clientID.setCellValueFactory(new PropertyValueFactory<>("ClientID"));
		orderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
		clientAdress.setCellValueFactory(new PropertyValueFactory<>("Address"));
		clientPhone.setCellValueFactory(new PropertyValueFactory<>("ReciverPhone"));
		clientName.setCellValueFactory(new PropertyValueFactory<>("ReciverName"));
		deliveryTime.setCellValueFactory(new PropertyValueFactory<>("DeliveryTime"));
		deliveryCost.setCellValueFactory(new PropertyValueFactory<>("DeliveryCost"));
		totalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));
		System.out.println("hey");
		tableOrders.setItems(null);
		tableOrders.setItems(ordersList);

		tableOrders.setRowFactory(tv -> {
			TableRow<Orders> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Orders rowData = row.getItem();
					Stage primaryStage = new Stage();
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("OrderPage.fxml"));
					try {
						Parent root = loader.load();
						OrderPageC cvc = loader.getController();
						cvc.setOrder(rowData);
						cvc.loadOrder();
						cvc.nonClientVis();
						primaryStage.setTitle("Order ID - " + rowData.getID());
						primaryStage.setScene(new Scene(root, 600, 600));
						primaryStage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			
			return row;
		});
		//this for some weird reason ,must be last! don't move it or double click feature won't work
		if(pendingF&&penCounter==1)
		{
			AlertBox.display("Pending Orders", "There are pending orders!");
			pendingF=false;
			penCounter=0;
		}

	}

	public ObservableList<Item> get_ItemList() {
		Massage msg = new Massage();
		msg.setCommand(Commands.GETCATALOG);
		server.Main.send_toServer(msg);
		msg = server.Main.get_from_server();
		ArrayList<Item> itemList = new ArrayList<Item>();
		if (msg.getCommand() ==Commands.GETCATALOG)
			itemList = (ArrayList<Item>) msg.getObject();
		else
			Main.loadError();
		ObservableList<Item> item_list = FXCollections.observableArrayList();
		for (int i = 0; i < itemList.size(); i++) {
			item_list.add(itemList.get(i));
		}
		return item_list;

	}

	@FXML
	void LogOut(ActionEvent event) throws IOException {
		Main.restart();
		thisStage.close();

	}

	@FXML
	void openEmployeesList(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("UserList.fxml"));
		try {
			Parent root = loader.load();
			UserListC cvc = loader.getController();
			cvc.refreshTable();
			primaryStage.setTitle("User List");
			primaryStage.setScene(new Scene(root, 600, 600));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@FXML
//	public void goBack(javafx.event.ActionEvent actionEvent) throws IOException {
//		Stage primaryStage = Main.getStage();
//		Parent parent = FXMLLoader.load(getClass().getResource("EmployeeView.fxml"));
//		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//		int width = gd.getDisplayMode().getWidth();
//		int height = gd.getDisplayMode().getHeight();
//		primaryStage.setScene(new Scene(parent, width, height));
//		primaryStage.show();
//	}

	@FXML
	void addItem(ActionEvent event) {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AddItem.fxml"));
		try {
			Parent root = loader.load();
			AddItemC cvc = loader.getController();
			cvc.setThisStage(primaryStage);
			primaryStage.setTitle("User List");
			primaryStage.setScene(new Scene(root, 600, 600));
			primaryStage.showAndWait();;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		renderCatalog();
        thisStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
               try {
					AlertBox.display("CATALOG UPDATE", "Please re-enter the system for catalog Update!");
				Main.restart();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
        });  

	}

	@FXML
	void removeItem(ActionEvent event) {
		if (items.getSelectionModel().getSelectedItems().size() > 0) {
			Item selected = items.getSelectionModel().getSelectedItem();
			Main.send_toServer(new Massage(selected, Commands.DELETE));
			Massage msg = Main.get_from_server();
			if (msg.getCommand() != Commands.DBERROR) {
				AlertBox.display("Item Remove", "SUCCESS!");
			}
			renderCatalog();
	        thisStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
	            public void handle(WindowEvent we) {
	               try {
						AlertBox.display("CATALOG UPDATE", "Please re-enter the system for catalog Update!");
					Main.restart();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            }
	        });  

		}
	}

	@FXML
	void delivered(ActionEvent event) {
		Orders selected = tableOrders.getSelectionModel().getSelectedItem();
		selected.setStatus(OStatus.DELIVERED);
		Main.send_toServer(new Massage(selected, Commands.UPDATE));
		Massage msg = Main.get_from_server();
		if (msg.getCommand() != Commands.DBERROR) {
			AlertBox.display("Status Change", "SUCCESS!");
		}
		renderOrders();

	}

	@FXML
	void sale(ActionEvent event) {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DiscountPage.fxml"));
		try {
			Parent root = loader.load();
			DiscountPage cvc = loader.getController();
			cvc.loadTable();
			primaryStage.setTitle("Sale - ");
			primaryStage.setScene(new Scene(root, 600, 600));
			primaryStage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@FXML
	void mail(ActionEvent event) {
		Orders selected = tableOrders.getSelectionModel().getSelectedItem();
		// check if order is selected...
		int clientID = selected.getClientID();
		Main.send_toServer(new Massage(clientID, Commands.GETCLIENT));
		Massage msg = Main.get_from_server();
		if (msg.getCommand() != Commands.DBERROR) {
			Client c = (Client) msg.getObject();
			// check if client is okay... it should be okay but check for errors....
			Mailer mail = new Mailer(c.getMail());
			if(mail!=null)
			{
			mail.sendMail("Your order to: "+selected.getReciverName()+ " was delivered!, for any problem that may rise ,feel free to contact us.");
			AlertBox.display("Mail", "SUCCESS!");
			}
			else
				AlertBox.display("Mail", "ERROR!");

		}

	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}
    @FXML
    void pending(ActionEvent event) {
		Orders selected = tableOrders.getSelectionModel().getSelectedItem();
		selected.setStatus(OStatus.ACCEPTED);
		Main.send_toServer(new Massage(selected, Commands.UPDATE));
		Massage msg = Main.get_from_server();
		if (msg.getCommand() != Commands.DBERROR) {
			AlertBox.display("Status Change", "SUCCESS!");
		}
		renderOrders();

    }
}
