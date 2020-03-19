package server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ServerSceneController {
	private Server server;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button stop;

	@FXML
	private Button start;

	@FXML
	void initialize() {
		assert stop != null : "fx:id=\"stop\" was not injected: check your FXML file 'ServerScene.fxml'.";
		assert start != null : "fx:id=\"start\" was not injected: check your FXML file 'ServerScene.fxml'.";
		start.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					MainServer.myServer.listen();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		stop.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					MainServer.myServer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				;

			}
		});

	}
}
