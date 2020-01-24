package application;


import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage)throws Exception {
		
		URL url = getClass().getResource("Login.fxml");
			AnchorPane pane = FXMLLoader.load(url);
		
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Server");
		primaryStage.show();
             // Hide this current window (if this is what you want)
             
      
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
