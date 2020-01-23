package server;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainServer extends Application {
	
	static Server myServer;
	@Override
	public void start(Stage primaryStage) {
		myServer= new Server(5555);
		URL url = getClass().getResource("ServerScene.fxml");
		try {
			AnchorPane pane = FXMLLoader.load(url);
		
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Server");
		primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main() {
		launch();
	}

}
