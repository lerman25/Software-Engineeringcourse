package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import common.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import common.Client;
import common.Item;

public class PayPage  implements Initializable {
	 private Item item;
	@FXML
    TextField name1;
    @FXML
    TextField creditCard;
    @FXML
    Button payFinal;
    @FXML
    Button backToHome;
    Person client ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	client = server.Main.getPerson();
    	if(client !=null)
    	{
    	name1.setText(client.getFirstName()+" "+client.getLastName());
    	creditCard.setText(client.getCredit_card());
    	}
    	else
    		System.out.println("Client is null");
        }
    public void buyB(MouseEvent event) throws IOException {
    	ArrayList<Item> items = new ArrayList<Item>();
    	System.out.println(item.getName());
    	items.add(item);
    	ItemInOrder _item = new ItemInOrder(items,0,client.getId());
    	java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    	Orders order = new Orders(client.getId(), date, date,1,client.getAddress(), client.getPhone_number(), client.getFirstName()+" "+client.getLastName(),date);
    	order.setStatus(OStatus.ACCEPTED);
    	_item.setOrderID(order.getID());
    	order.setItemList(_item);
    	Main.send_toServer(new Massage(order,Commands.ADD));
        AlertBox.display("Payment","SUCCESS!");
    }
    public void backHome(MouseEvent event) throws IOException {
        Stage primaryStage =Main.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("base.fxml"));
        // primaryStage.setTitle("Hello World");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        primaryStage.setScene(new Scene(root, width, height));
    }
	public void setItem(Item _item) {
		// TODO Auto-generated method stub
		  this.item = _item;
	      
		
	}
}