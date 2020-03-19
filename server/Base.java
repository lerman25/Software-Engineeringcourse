package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.Client;
import common.Commands;
import common.Massage;
import common.Person;

public class Base implements Initializable {

	private int id = 0;

	@FXML
	private Text userLabel;

	@FXML
	private Button personal;
	@FXML
	Button login;
	@FXML
	Button signup;
	@FXML
	TabPane pane;
	@FXML
	Tab home;

	@FXML
	private Button manager;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void login(ActionEvent event) throws IOException {
		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		// primaryStage.setTitle("Hello World");
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		primaryStage.setScene(new Scene(root, width, height));
	}

	@FXML
	void manager(ActionEvent event) {
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("EmployeeView.fxml"));
		try {
			Parent root = loader.load();
			EmployeeController cvc = loader.getController();
			cvc.setThisStage(primaryStage);
			primaryStage.setScene(new Scene(root, 400, 400));

			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void signup(ActionEvent event) throws IOException {
		Stage primaryStage = Main.getStage();
		Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		// primaryStage.setTitle("Hello World");
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		primaryStage.setScene(new Scene(root, width, height));
	}

	@FXML
	void personal(ActionEvent event) {
		System.out.println("omer");
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ClientOrders.fxml"));
		try {
			Parent root = loader.load();
			ClientOrderC cvc = loader.getController();
			primaryStage.setScene(new Scene(root, 400, 400));
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadView() {
		Permissions p = Main.getPermission();
		if (p == Permissions.GUEST) {
			personal.setVisible(false);
			userLabel.setVisible(false);
			manager.setVisible(false);
		} else {
			personal.setVisible(true);
			userLabel.setVisible(true);
			login.setVisible(false);
			signup.setVisible(false);
			manager.setVisible(false);

			if (p == Permissions.EMPLOYEE)
				;
			manager.setVisible(true);
			if (p == Permissions.SHOPMANAGER)
				manager.setVisible(true);
			if (p == Permissions.ADMIN) {
				login.setVisible(true);
				signup.setVisible(true);
				manager.setVisible(true);
			}

			userLabel.setText("Welcom " + Main.getPerson().getUsername());
		}

	}



}
