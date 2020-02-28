package server;

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
import server.Main;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
    @FXML
    Tab renderOrders;
    @FXML
    TableView<Employee> tableEmployee;
    @FXML
    TableColumn<Employee,String> EmployeeName;
    @FXML
    TableColumn<Employee,String> EmployeeRank;
    @FXML
    Button renderEmployees;


    private ObservableList<Orders> data;
    private  ObservableList<Employee> data1;
    DataBase DbConnection;
    @Override
    public void initialize(URL location,ResourceBundle resource){
//      username.setText("Kasim Gadban");
    }
    @FXML
     void renderOrders(){
    	Massage msg = new Massage();
        msg.setCommand(Commands.GETORDERS);
        data=FXCollections.observableArrayList();
        server.Main.send_toServer(msg);
        msg = server.Main.get_from_server();
        ArrayList<Orders> orders = (ArrayList<Orders>) msg.getObject();
        for(int i=0;i<orders.size();i++)
        data.add(orders.get(i));
        //        orderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        clientID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        clientAdress.setCellValueFactory(new PropertyValueFactory<>("clientAdress"));
        clientPhone.setCellValueFactory(new PropertyValueFactory<>("clientPhone"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        deliveryTime.setCellValueFactory(new PropertyValueFactory<>("delivery deliveryTime"));
        deliveryCost.setCellValueFactory(new PropertyValueFactory<>("deliveryCost"));
        totalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        tableOrders.setItems(null);
        tableOrders.setItems(data);

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
    void renderEmployees(){
     /*   try{
            System.out.println("render Employees to table from DB");
            DbConnection = DataBase.getInstance();
            data1 = FXCollections.observableArrayList();
            ResultSet rs = DbConnection.get_TableResultSet("Employee");
            while(rs.next()){
        System.out.println (rs.getInt (2) +" ----- "+ rs.getString (3) +" ----- "+ rs.getString (4) +"- --- "+ rs.getString (5));
                data1.add(
                        new Employee(rs.getInt(1),rs.getString(0),rs.getString(3),
                        rs.getInt(4),rs.getString(5),rs.getInt(6),
                        rs.getString(7),rs.getInt(8),rs.getString(9),
                        rs.getString(10),rs.getString(12),rs.getString(13)));
            }
        } catch (SQLException ex){
            System.err.println("ERROR" + ex);
        }*/
        EmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        EmployeeRank.setCellValueFactory(new PropertyValueFactory<>("EmployeeRank"));

        tableEmployee.setItems(null);
        tableEmployee.setItems(data1);
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
