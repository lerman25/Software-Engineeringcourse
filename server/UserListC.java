package server;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.Commands;
import common.Person;
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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserListC implements Initializable {

	@FXML
	private AnchorPane PersonListAnchor;

	@FXML
	private Button add;

	@FXML
	private Button goBack;

	@FXML
	private TableColumn<Person, String> personName;

	@FXML
	private TableColumn<Person, Integer> personID;

	@FXML
	private Button remove;

	@FXML
	private TableView<Person> tablePerson;
	@FXML
	private TableColumn<Person, Permissions> personPermission;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		personName.setCellValueFactory(new PropertyValueFactory<>("username"));
		personID.setCellValueFactory(new PropertyValueFactory<>("id"));
		personPermission.setCellValueFactory(new PropertyValueFactory<>("permission"));
		tablePerson.setPlaceholder(new Label("No Persons to Display"));
		tablePerson.setItems(null);
		tablePerson.setItems(get_list());
		tablePerson.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

//		tableEmployee.setRowFactory(tv -> {
//            TableRow<Orders> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
//                    Orders rowData = row.getItem();
//					Stage primaryStage = new Stage();
//					FXMLLoader loader = new FXMLLoader();
//					loader.setLocation(getClass().getResource("OrderPage.fxml"));
//					try {
//						Parent root = loader.load();
//						OrderPageC cvc = loader.getController(); 
//						cvc.setOrder(rowData);
//						cvc.loadOrder();
//						primaryStage.setTitle("Order ID - "+rowData.getID());
//						primaryStage.setScene(new Scene(root, 600, 600));
//						primaryStage.show();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					}
//            });
//            return row ;
//        });

	}

	@FXML
	void goBack(ActionEvent event) {

	}

	@FXML
	void add(ActionEvent event) {
		Stage primaryStage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			int height = gd.getDisplayMode().getHeight();
			primaryStage.setScene(new Scene(root, width, height));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// primaryStage.setTitle("Hello World");

		// signupform.

	}

	@FXML
	void update(ActionEvent event) {
		if (tablePerson.getSelectionModel().getSelectedItems().size() > 0) {
			Person selected = tablePerson.getSelectionModel().getSelectedItem();
			if (selected.getPermission().ordinal() >= Main.getPermission().ordinal()) {
				AlertBox.display("User Update",
						"You dont have the permission to change a : " + selected.getPermission().toString());

			} else {
				Stage primaryStage = new Stage();
				Parent root;
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("SignUp.fxml"));
				try {
					root = loader.load();
					GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
					int width = gd.getDisplayMode().getWidth();
					int height = gd.getDisplayMode().getHeight();
					SignUp cvc = loader.getController();
					cvc.renderPerson(selected);
					primaryStage.setTitle("Update");
					primaryStage.setScene(new Scene(root, width, height));
					primaryStage.show();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// primaryStage.setTitle("Hello World");

				// signupform.
			}
		}
	}

	@FXML
	void remove(ActionEvent event) {
		if (tablePerson.getSelectionModel().getSelectedItems().size() > 0) {
			Person selected = tablePerson.getSelectionModel().getSelectedItem();
			if (selected.getPermission().ordinal() >= Main.getPermission().ordinal()) {
				AlertBox.display("User Remove",
						"You dont have the permission to remove a : " + selected.getPermission().toString());

			} else {
				Main.send_toServer(new Massage(selected, Commands.DELETE));
				Massage msg = Main.get_from_server();
				if (msg.getCommand() != Commands.DBERROR) {
					AlertBox.display("User Remove", "SUCCESS!");
				}
				refreshTable();
				// need to see how to refresh catalog in main stage....
			}
		}

	}

	public void refreshTable() {
		tablePerson.setPlaceholder(new Label("No Persons to Display"));
		tablePerson.setItems(get_list());
		tablePerson.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	}

	public ObservableList<Person> get_list() {
		Main.send_toServer(new Massage(Main.getPerson().getId(), Commands.GETPERSONS));
		Massage msg = Main.get_from_server();
		ArrayList<Person> o = new ArrayList<Person>();
		if (msg.getCommand() != Commands.DBERROR)
			o = (ArrayList<Person>) msg.getObject();
		ObservableList<Person> emp_list = FXCollections.observableArrayList();
		for (int i = 0; i < o.size(); i++) {
			emp_list.add(o.get(i));
		}
		return emp_list;

	}

}
