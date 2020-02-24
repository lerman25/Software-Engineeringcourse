package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import client.LClient;
import common.Client;
import common.Massage;

public class Main extends Application {
	static LClient client;
	private static Client _client;
    static private  Stage stage= null;
    @Override
    public void start(Stage primaryStage) throws Exception{
      stage =primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setTitle("Lilac");

       stage.getIcons().add(new Image(getClass().getResourceAsStream("logo.jpg")));

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        stage.setScene(new Scene(root, width, height));
       stage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
            try {
				client.closeConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            // Save file
        });
        stage.show();

    }
 static public Stage getStage(){
        return stage;
    }

 static public void send_toServer(Massage m)
 {
	 try {
	 if(!client.isConnected())
		 {
			 System.out.println("Opening connection");
			 client.openConnection();
		 }
		client.sendToServer(m);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
 }
 static  Massage get_from_server()
 {		
	 while(client.isnull())
	 {
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
    	set_client(null);
    	client = new LClient("127.0.0.1",5555);
    	try {
			client.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        launch(args);
    }
	public static Client get_client() {
		return _client;
	}
	public static void set_client(Client _client) {
		Main._client = _client;
	}
}
