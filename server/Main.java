package server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.scenario.effect.impl.prism.PrImage;

import client.LClient;
import common.Client;
import common.Commands;
import common.Massage;
import common.Orders;
import common.Person;

public class Main extends Application {
	static LClient client;
	private static Permissions permission = Permissions.GUEST;
	private static Person person=null;
	static private Stage stage = null;
	private static String resource = "Login.fxml";
	static Parent errorRoot = null;
    @FXML // fx:id="text"
    private Text text; // Value injected by FXMLLoader

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			client.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			loadError();
			e.printStackTrace();
			System.out.println("problem");

		}
		permission=Permissions.GUEST;
		stage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(resource));
		Parent root = loader.load();
//		DiscountPage cvc = loader.getController();
////		client.sendToServer(new Massage ( 1, Commands.CLIENTORDERS));
////		Massage msg = this.get_from_server();
////		ArrayList<Orders> o =(ArrayList<Orders>) msg.getObject();
////		cvc.setOrders(o);
//		cvc.loadTable();
		errorRoot= FXMLLoader.load(getClass().getResource("ServerErrorWindow.fxml"));

		stage.setTitle("Lilac");

		stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.jpg")));

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		stage.setScene(new Scene(root, width, height));
		stage.setResizable(true);
		stage.setOnCloseRequest(event -> {
			System.out.println("exiting");
			System.out.println("Stage is closing");
			if(person!=null)
			{
				send_toServer(new Massage(person.getUsername(),Commands.LOGOUT));
			}
			try {
				Thread.sleep(300);
				client.closeConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Save file
 catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Platform.exit();
	        System.exit(0);	
		});
		stage.show();

	}

	static public Stage getStage() {
		return stage;
	}

	public static void loadError() {	
			AlertBox.display("Server Error","Server Error - program will close!");
	        Platform.exit();
	        System.exit(0);		
	        // Hide this current window (if this is what you want)
	        //need to replace this with event handler maybe..
	}

	static public void send_toServer(Massage m) {
		if (client.isServerFlag() == false) {
			loadError();
		} else {
			try {
				if (!client.isConnected()) {
					System.out.println("Opening connection");
					client.openConnection();
				}
				client.sendToServer(m);
			} catch (IOException e) {

			// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	static Massage get_from_server() {
	
		while (client.isnull()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Massage rmsg = client.getReturnMassage();
		if(rmsg.getCommand()==Commands.SHUTDOWN)
			loadError();
//		if(rmsg.getCommand()==Commands.DBERROR)
//		{
//			try {
//				errorRoot= FXMLLoader.load(Main.class.getResource("ServerErrorWindow.fxml"));
//				/*ClientOrderC cvc = loader.getController();
//				client.sendToServer(new Massage ( 1, Commands.CLIENTORDERS));
//				Massage msg = this.get_from_server();
//				ArrayList<Orders> o =(ArrayList<Orders>) msg.getObject();
//				cvc.setOrders(o);
//				cvc.refreshTable();*/
//
//				GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//				int width = gd.getDisplayMode().getWidth();
//				int height = gd.getDisplayMode().getHeight();
//				stage.setScene(new Scene(errorRoot, width, height));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//		}
		return rmsg;

	}

	public static void main(String[] args) {
		boolean launchf=true;
		setPerson(null);
		client = new LClient("127.0.0.1", 5555);
		launch(args);
	}



	public static void restart() throws IOException
	{
		send_toServer(new Massage(person.getUsername(),Commands.LOGOUT));
		setPerson(null);
		System.out.println("New Main is loading...");
		try {
			client.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			loadError();
			e.printStackTrace();
			System.out.println("problem");

		}
		permission=Permissions.GUEST;
		stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(resource));
		Parent root = loader.load();
//		DiscountPage cvc = loader.getController();
////		client.sendToServer(new Massage ( 1, Commands.CLIENTORDERS));
////		Massage msg = this.get_from_server();
////		ArrayList<Orders> o =(ArrayList<Orders>) msg.getObject();
////		cvc.setOrders(o);
//		cvc.loadTable();
		errorRoot= FXMLLoader.load(Main.class.getResource("ServerErrorWindow.fxml"));

		stage.setTitle("Lilac");

		stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.jpg")));

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		stage.setScene(new Scene(root, width, height));
		stage.setResizable(true);
		stage.setOnCloseRequest(event -> {
			System.out.println("exiting");
			System.out.println("Stage is closing");
			if(person!=null)
			{
				send_toServer(new Massage(person.getUsername(),Commands.LOGOUT));
			}
			try {
				Thread.sleep(300);
				client.closeConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Save file
 catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Platform.exit();
	        System.exit(0);	
		});
		stage.show();

	}

	public static Person getPerson() {
		return person;
	}

	public static void setPerson(Person person) {
		Main.person = person;
	}

	public static Permissions getPermission() {
		return permission;
	}

	public static void setPermission(Permissions permission) {
		Main.permission = permission;
	}

}