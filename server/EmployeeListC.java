package server;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.Commands;
import common.Employee;
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

public class EmployeeListC implements Initializable {

    @FXML
    private AnchorPane EmployeeListAnchor;

    @FXML
    private Button add;

    @FXML
    private Button goBack;

    @FXML
    private TableColumn<Employee, String> EmployeeName;

    @FXML
    private Button remove;

    @FXML
    private TableView<Employee> tableEmployee;

    @FXML
    private TableColumn<Employee, Integer> EmployeeRank;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EmployeeName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		EmployeeRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
		tableEmployee.setPlaceholder(new Label("No Employees to Display"));
		tableEmployee.setItems(null);
		tableEmployee.setItems(get_list());
		tableEmployee.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

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

    }

    @FXML
    void remove(ActionEvent event) {
    	if(tableEmployee.getSelectionModel().getSelectedItems().size() > 0)
    	{
    		Employee selected = tableEmployee.getSelectionModel().getSelectedItem();
    		Main.send_toServer(new Massage (selected,Commands.DELETE));
    		Massage msg = Main.get_from_server();
    		if(msg.getCommand()!=Commands.DBERROR) 
    		{
    			// INSERT SUCSESS PROMPT
    		}
			refreshTable();
			// need to see how to refresh catalog in main stage....
    	}

    }
    public void refreshTable()
    {
    	tableEmployee.setPlaceholder(new Label("No Employees to Display"));
    	tableEmployee.setItems(get_list());
		tableEmployee.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }
	public ObservableList<Employee> get_list()
	{
		Main.send_toServer(new Massage (Main.getPerson().getId(), Commands.GETEMPLOYEES));
		Massage msg = Main.get_from_server();
		ArrayList<Employee> o = new ArrayList<Employee>();
		if(msg.getCommand()!=Commands.DBERROR)
		 o =(ArrayList<Employee>) msg.getObject();
		ObservableList<Employee> emp_list = FXCollections.observableArrayList();
		for(int i=0; i<o.size();i++)
		{
			emp_list.add(o.get(i));
		}
		return emp_list;
		
	}


}
