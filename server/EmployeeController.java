package server;

import common.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//import server.FormValidation;
//import server.Main;
import server.Main;

import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
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
	String Latest = "";
	@FXML
	private TableView<Complaint> complaint_table;
    @FXML
    private TableColumn<Complaint, Timestamp> complaint_date;
	@FXML
	private TableColumn<Complaint, Integer> complaint_ClientID;
	@FXML
	private TableColumn<Complaint, Integer> complaint_orderID;
	@FXML
	private TableColumn<Complaint, String> complaint_status;
	@FXML
	private TableColumn<Complaint, Integer> complaint_complaintID;
	@FXML
	private TableColumn<Complaint, String> complaint_text;
	
	@FXML
	private Tab complaints;

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
	private ObservableList<Complaint> complaintsList;
	private ObservableList<Employee> data1;
	private Stage thisStage;
	int penCounter;
	int complaintPenCounter;

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		username.setText(Main.getPerson().getUsername());
		if (Main.getPermission().ordinal() < Permissions.SHOPMANAGER.ordinal())
			EditEmployees.setDisable(true);
		penCounter = 1;
		complaintPenCounter=1;
	}

	@FXML
	void renderCatalog() {
		if (!Latest.equals("CATALOG")) {
			System.out.println("Render Catalog!");
			item.setCellValueFactory(new PropertyValueFactory<>("Name"));
			itemPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
			items.setItems(null);
			items.setItems(get_ItemList());
			items.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			Latest = "CATALOG";
		}
	}

	@FXML
	void renderOrders() {
		if (!Latest.equals("ORDERS")) {
			Latest = "ORDERS";
			Massage msg = new Massage();
			msg.setCommand(Commands.GETORDERS);
			ordersList = FXCollections.observableArrayList();
			server.Main.send_toServer(msg);
			msg = server.Main.get_from_server();
			ArrayList<Orders> orders = new ArrayList<Orders>();
			if (msg.getCommand() != Commands.DBERROR)
				orders = (ArrayList<Orders>) msg.getObject();
			boolean pendingF = false;
			for (int i = 0; i < orders.size(); i++) {
				if (orders.get(i).getStatus() == OStatus.PENDING)
					pendingF = true;
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
			// this for some weird reason ,must be last! don't move it or double click
			// feature won't work
			if (pendingF && penCounter == 1) {
				AlertBox.display("Pending Orders", "There are pending orders!");
				pendingF = false;
				penCounter = 0;
			}
		}

	}

	public ObservableList<Item> get_ItemList() {
		Massage msg = new Massage();
		msg.setCommand(Commands.GETCATALOG);
		server.Main.send_toServer(msg);
		msg = server.Main.get_from_server();
		ArrayList<Item> itemList = new ArrayList<Item>();
		if (msg.getCommand() == Commands.GETCATALOG)
			itemList = (ArrayList<Item>) msg.getObject();
		else
			Main.loadError();
		ObservableList<Item> item_list = FXCollections.observableArrayList();
		for (int i = 0; i < itemList.size(); i++) {
			item_list.add(itemList.get(i));
		}
		return item_list;

	}
	public ObservableList<Complaint> get_ComplaintList() {
		Massage msg = new Massage();
		msg.setCommand(Commands.GETCOMPLAINTS);
		server.Main.send_toServer(msg);
		msg = server.Main.get_from_server();
		ArrayList<Complaint> itemList = new ArrayList<Complaint>();
		if (msg.getCommand() == Commands.GETCOMPLAINTS)
			itemList = (ArrayList<Complaint>) msg.getObject();
		else
			Main.loadError();
		ObservableList<Complaint> item_list = FXCollections.observableArrayList();
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
    void renderComplaints() {
		if (!Latest.equals("COMPLAINT")) {

			Latest = "COMPLAINT";

		    complaint_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		    complaint_ClientID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
 			complaint_orderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
 			complaint_status.setCellValueFactory(new PropertyValueFactory<>("status"));

 			complaint_complaintID.setCellValueFactory(new PropertyValueFactory<>("ID"));
 			complaint_text.setCellValueFactory(new PropertyValueFactory<>("text"));
 			complaint_table.setItems(null);
 			ObservableList<Complaint> complist =get_ComplaintList();
 			complaint_table.setItems(get_ComplaintList());
 			complaint_table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			boolean pendingF = false;

			for (int i = 0; i < complist.size(); i++) {
				if (complist.get(i).getStatus() == CStatus.PENDING)
					 pendingF = true;
			}
			if (pendingF && penCounter == 1) {
				AlertBox.display("Pending Orders", "There are pending orders!");
				pendingF = false;
				penCounter = 0;
			}

 			
		}

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
			primaryStage.showAndWait();
			;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Latest="";
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
			Latest="";
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
		Latest="";
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
		Main.send_toServer(new Massage(clientID, Commands.GETPERSON));
		Massage msg = Main.get_from_server();
		if (msg.getCommand() != Commands.DBERROR) {
			Person c = (Person) msg.getObject();
			// check if client is okay... it should be okay but check for errors....
			Mailer mail = new Mailer(c.getMail());
			if (mail != null) {
				mail.sendMail("Your order to: " + selected.getReciverName()
						+ " was delivered!, for any problem that may rise ,feel free to contact us.");
				AlertBox.display("Mail", "SUCCESS!");
			} else
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
		Latest="";
		Main.send_toServer(new Massage(selected, Commands.UPDATE));
		Massage msg = Main.get_from_server();
		if (msg.getCommand() != Commands.DBERROR) {
			AlertBox.display("Status Change", "SUCCESS!");
		}
		renderOrders();

	}
	@FXML
	void complaint_accepted(ActionEvent event) {
		Complaint selected = complaint_table.getSelectionModel().getSelectedItem();
		System.out.println("SELEC ID"+selected.getStatus());
		selected.setStatus(CStatus.ACCEPTED);
		Latest="";
		Main.send_toServer(new Massage(selected, Commands.UPDATE));
		Massage msg = Main.get_from_server();
		if (msg.getCommand() == Commands.UPDATE) {
			AlertBox.display("Status Change", "SUCCESS!");
		}
		renderComplaints();

	}
	@FXML
	void complaint_rejected(ActionEvent event) {
		Complaint selected = complaint_table.getSelectionModel().getSelectedItem();
		System.out.println("SELEC ID"+selected.getStatus());
		selected.setStatus(CStatus.REJECTED);
		Latest="";
		Main.send_toServer(new Massage(selected, Commands.UPDATE));
		Massage msg = Main.get_from_server();
		if (msg.getCommand() == Commands.UPDATE) {
			AlertBox.display("Status Change", "SUCCESS!");
		}
		renderComplaints();

	}
	@FXML
	void complaint_mail(ActionEvent event) {
		Complaint selected = complaint_table.getSelectionModel().getSelectedItem();
		// check if order is selected...
		int clientID = selected.getClientID();
		Main.send_toServer(new Massage(clientID, Commands.GETPERSON));
		Massage msg = Main.get_from_server();
		if (msg.getCommand() == Commands.GETPERSON) {
			Person c = (Person) msg.getObject();
			// check if client is okay... it should be okay but check for errors....
			Mailer mail = new Mailer(c.getMail());
			if (mail != null) {
				mail.sendMail("Your Complient Status has been changed! Order ID: " + selected.getOrderID()+" new status is: "+selected.getStatus().toString()
						);
				AlertBox.display("Mail", "SUCCESS!");
			} else
				AlertBox.display("Mail", "ERROR!");

		}

	}
//	@FXML
//	void render_Complaints(ActionEvent event) {
//		System.out.println("RENDRING COMPLINTS!!!!");
//		if (!Latest.equals("COMPLAINTS")) {
//			Latest = "COMPLAINTS";
//			System.out.println("Render Complaints");
//			Massage masg = new Massage(true, Commands.GETCOMPLAINTS);
//			complaintsList = FXCollections.observableArrayList();
//
//			Main.send_toServer(masg);
//			masg = Main.get_from_server();
//			ArrayList<Complaint> complaints = new ArrayList<Complaint>();
//			if (masg.getCommand() == Commands.GETCOMPLAINTS)
//				complaints = (ArrayList<Complaint>) masg.getObject();
//			else
//				Main.loadError();
//			for (int i = 0; i < complaints.size(); i++) {
//
//				complaintsList.add(complaints.get(i));
//			}
//			complaint_complaintID.setCellValueFactory(new PropertyValueFactory<>("ID"));
//			complaint_ClientID.setCellValueFactory(new PropertyValueFactory<>("ClientID"));
//			complaint_orderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
//			complaint_date.setCellValueFactory(new PropertyValueFactory<>("date"));
//			complaint_text.setCellValueFactory(new PropertyValueFactory<>("text"));
//			complaint_status.setCellValueFactory(new PropertyValueFactory<>("status"));
//			complaint_table.setItems(null);
//			complaint_table.setItems(complaintsList);
//		}
//	}
//	@FXML
//	void renderComplaints(ActionEvent event) {
////		System.out.println("RENDRING COMPLINTS!!!!");
////		if (!Latest.equals("COMPLAINTS")) {
////			Latest = "COMPLAINTS";
////			System.out.println("Render Complaints");
////			Massage masg = new Massage(true, Commands.GETCOMPLAINTS);
////			complaintsList = FXCollections.observableArrayList();
////
////			Main.send_toServer(masg);
////			masg = Main.get_from_server();
////			ArrayList<Complaint> complaints = new ArrayList<Complaint>();
////			if (masg.getCommand() == Commands.GETCOMPLAINTS)
////				complaints = (ArrayList<Complaint>) masg.getObject();
////			else
////				Main.loadError();
////			for (int i = 0; i < complaints.size(); i++) {
////
////				complaintsList.add(complaints.get(i));
////			}
////
////		}
//	}

	@FXML
	void do_nothing(ActionEvent event) {

	}

}
