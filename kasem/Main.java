package kasem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;

import client.LClient;
import common.Massage;

public class Main extends Application {
	static LClient client;

    static private  Stage stage= null;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("EmployeeView.fxml"));
        primaryStage.setTitle("Employee");
//        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.setMaximized(true);
        Scene menuScene = new Scene(root, 1080, 720);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    static public Stage getStage(){
        return stage;
    }

    static public void send_toServer(Massage m)
    {
   	 try {
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
       	client = new LClient("127.0.0.1",5555);
       	try {
   			client.openConnection();
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
           launch(args);
       }
}
