package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.Commands;
import common.Massage;
import common.Orders;

public class ClientOrderC implements Initializable {
    @FXML
    private TableColumn<Orders, Date> date;

    @FXML
    private TableView<Orders> orderTable;


    @FXML
    private TableColumn<Orders, String> name;

    @FXML
    private TableColumn<Orders, String> id;

    @FXML
    private TableColumn<Orders, String> status;
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		date.setCellValueFactory(new PropertyValueFactory<>("Time"));
		name.setCellValueFactory(new PropertyValueFactory<>("ReciverName"));
		id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));
		orderTable.setPlaceholder(new Label("No Orders to Display"));
		orderTable.setItems(null);
		orderTable.setItems(get_list());
        orderTable.setRowFactory(tv -> {
            TableRow<Orders> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Orders rowData = row.getItem();
					Stage primaryStage = new Stage();
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("OrderPage.fxml"));
					try {
						Parent root = loader.load();
						OrderPageC cvc = loader.getController(); 
						cvc.setOrder(rowData);
						cvc.loadOrder();
						primaryStage.setTitle("Order ID - "+rowData.getID());
						primaryStage.setScene(new Scene(root, 600, 600));
						primaryStage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					}
            });
            return row ;
        });

	}
	public void refreshTable()
	{
		orderTable.setPlaceholder(new Label("No Orders to Display"));
		orderTable.setItems(get_list());

	}
	public ObservableList<Orders> get_list()
	{
		System.out.println("getlist");
		Main.send_toServer(new Massage (Main.getPerson().getId(), Commands.CLIENTORDERS));
		Massage msg = Main.get_from_server();
		ArrayList<Orders> o = new ArrayList<Orders>();
		if(msg.getCommand()!=Commands.DBERROR)
		 o =(ArrayList<Orders>) msg.getObject();
		ObservableList<Orders> order_list = FXCollections.observableArrayList();
		for(int i=0; i<o.size();i++)
		{
			System.out.println("Add to orderlist");
			order_list.add(o.get(i));
		}
		return order_list;
		
	}

}
