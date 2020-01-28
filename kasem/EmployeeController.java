package kasem;

import common.Commands;
import common.DataBase;
import common.Massage;
import common.Orders;
import common.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import server.FormValidation;
//import server.Main;



import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable{
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
    TableColumn<Orders,String> orderID;
    @FXML
    TableColumn<Orders,String> clientID;
    @FXML
    TableColumn<Orders,String> orderDate;
    @FXML
    TableColumn<Orders,String> clientAdress;
    @FXML
    TableColumn<Orders,String> clientPhone;
    @FXML
    TableColumn<Orders,String> clientName;
    @FXML
    TableColumn<Orders,String> deliveryTime;
    @FXML
    TableColumn<Orders,String> deliveryCost;
    @FXML
    TableColumn<Orders,String> totalCost;

    private ObservableList<Orders> data;
    DataBase DbConnection;
    @Override
    public void initialize(URL location,ResourceBundle resource){
            DbConnection = DataBase.getInstance();
            DataBase DbConnection = DataBase.getInstance();
        System.out.println();
//      username.setText("Kasim Gadban");
        renderOrders();
    }

    private void renderOrders(){
        System.out.println("render orders to table from DB");
        data = FXCollections.observableArrayList();
    	Massage msg = new Massage();
    	msg.setCommand(Commands.GETEMPLOYEES);
    	msg =kasem.Main.get_from_server();
    	ArrayList<Orders> orders = (ArrayList<Orders>) msg.getObject();

        orderID.setCellValueFactory(new PropertyValueFactory<>("OID"));
        clientID.setCellValueFactory(new PropertyValueFactory<>("CID"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        clientAdress.setCellValueFactory(new PropertyValueFactory<>("Adress"));
        clientPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        deliveryTime.setCellValueFactory(new PropertyValueFactory<>("delivery time"));
        deliveryCost.setCellValueFactory(new PropertyValueFactory<>("delivery cost"));
        totalCost.setCellValueFactory(new PropertyValueFactory<>("Total cost"));

        tableOrders.setItems(null);
        tableOrders.setItems(data);
        System.out.println(data);
    }
    @FXML
        void  LogOut(ActionEvent event) throws IOException{
            Stage primaryStage =Main.getStage();
            URL url = getClass().getResource("Login.fxml");
            Parent root = FXMLLoader.load(url);
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();
            primaryStage.setScene(new Scene(root, width, height));
            primaryStage.show();
        }

    @FXML
    void openEmployeesList(ActionEvent event) throws IOException {
        Stage primaryStage =Main.getStage();
        URL url = getClass().getResource("EmployeeListView.fxml");
        Parent root = FXMLLoader.load(url);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }

    @FXML
    public void goBack(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage primaryStage = Main.getStage();
        Parent parent = FXMLLoader.load(getClass().getResource("EmployeeView.fxml"));
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        primaryStage.setScene(new Scene(parent, width, height));
        primaryStage.show();
    }


}
