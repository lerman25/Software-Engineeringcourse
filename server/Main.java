package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    static private  Stage stage= null;
    @Override
    public void start(Stage primaryStage) throws Exception{
      stage =primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
       // primaryStage.setTitle("Hello World");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        stage.setScene(new Scene(root, width, height));
        stage.show();
    }
 static public Stage getStage(){
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
