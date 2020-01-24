package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage)throws Exception {
		
        
        	 Parent root;
             root = FXMLLoader.load(getClass().getClassLoader().getResource("1.fxml"));
             Stage stage = new Stage();
             stage.setScene(new Scene(root, 800, 800));
             stage.show();
             // Hide this current window (if this is what you want)
             
      
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
