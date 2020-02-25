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
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import client.LClient;
import common.Client;
import common.Commands;
import common.Massage;

public class Main extends Application {
	static LClient client;
	private static Client _client=null;
	static private Stage stage = null;
    @FXML // fx:id="text"
    private Text text; // Value injected by FXMLLoader

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage.setTitle("Lilac");

		stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.jpg")));

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		stage.setScene(new Scene(root, width, height));
		stage.setOnCloseRequest(event -> {
			System.out.println("exiting");
			System.out.println("Stage is closing");
			if(_client!=null)
			{
				send_toServer(new Massage(_client.getUsername(),Commands.LOGOUT));
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
		});
		stage.show();

	}

	static public Stage getStage() {
		return stage;
	}

	public static void loadError() {		
		   Parent root;
	        try {
	            root = FXMLLoader.load(Main.class.getClassLoader().getResource("ServerError.fxml"));
	            Stage stage = new Stage();
	            stage.setTitle("My New Stage Title");
	            stage.setScene(new Scene(root, 450, 450));
	            stage.show();
	            // Hide this current window (if this is what you want)
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
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
				System.out.println("he");
				loadError();

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

		return client.getReturnMassage();

	}

	public static void main(String[] args) {
		boolean launchf=true;
		set_client(null);
		client = new LClient("127.0.0.1", 5555);
		try {
			client.openConnection();
		} catch (IOException e) {
			loadError();
			launchf=false;
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("problem");

		}
		if(launchf)
		launch(args);
	}

	public static Client get_client() {
		return _client;
	}

	public static void set_client(Client _client) {
		Main._client = _client;
	}
}