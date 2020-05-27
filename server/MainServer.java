package server;

import java.io.IOException;
import java.net.URL;

import client.LClient;
import common.Commands;
import common.Massage;
import common.Person;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainServer extends Application {

	public static Server myServer;

	@Override
	public void start(Stage primaryStage) {

		myServer = new Server(5555);
		Thread unSleep = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				{
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("DataBase ping");
				myServer.getDataBase().checkLogin_user("admin");
				}
				// TODO Auto-generated method stub
				
			}
		});
		
		unSleep.start();
//		 Runtime.getRuntime().addShutdownHook(new Thread() 
//		    { 
//		      public void run() 
//		      { 
//		        System.out.println("Shutdown Hook is running !"); 
//		        myServer.stopListening();
//		      } 
//		    }); 
		URL url = getClass().getResource("ServerScene.fxml");
		try {
			AnchorPane pane = FXMLLoader.load(url);

			Scene scene = new Scene(pane, 300, 275);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Server");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("server.jpg")));

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					System.out.println("Server Shutting Down");
					myServer.sendToAllClients(new Massage(true, Commands.SHUTDOWN));
					try {
						myServer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Platform.exit(); // this is for when you close the main stage the whole program will close
					System.exit(0);
				}
			});
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		launch();

	}

}
