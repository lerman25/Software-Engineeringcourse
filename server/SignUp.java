package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.Client;
import common.Commands;
import common.Massage;

public class SignUp implements Initializable {
    @FXML
    TextField userName;
    @FXML
    TextField password;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField idNumber;
    @FXML
    TextField eMail;
    @FXML
    Button signUp;
    @FXML Label for_username;
    @FXML Label for_password;
    @FXML Label for_firstname;
    @FXML Label for_lastname;
    @FXML Label for_id;
    @FXML Label for_email;
    @FXML AnchorPane page;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
     //

    }
    public void signup(ActionEvent event) throws IOException {

        boolean nonuser= FormValidation.textFieldNotEmpty(userName,for_username,"Username is required!!");
        boolean nopasswprd= FormValidation.textFieldNotEmpty(password,for_password,"Password is required!!");
        boolean nofirst= FormValidation.textFieldNotEmpty(firstName,for_firstname,"First Name is required!!");
        boolean nolast= FormValidation.textFieldNotEmpty(lastName,for_lastname,"Last Name is required!!");
        boolean noid= FormValidation.textFieldNotEmpty(idNumber,for_id,"ID-Number is required!!");
        boolean noemail= FormValidation.textFieldNotEmpty(eMail,for_email,"E-Mail is required!!");
        if(nonuser&&nopasswprd&&noemail&&nofirst&&noid&&nolast){
            if(userName.getText().equals("soreka")) {
            //                                                               change the if case to a data base method
                for_username.setText("This user already used ");

            }
            else{
				String _firstname =firstName.getText();
				String _lastname = lastName.getText();
				String _mail = eMail.getText();
				int _phone = 0; // to add
				String _credit = "-1"; // add
				int _age = 0; // add
				String _gender = "NULL gender"; //add
				String _address = "NULL address" ;//add
				String _username = userName.getText();
				String _password = password.getText();
				Massage msg = new Massage("Person",Commands.GETLASTID);
				server.Main.send_toServer(msg);
				msg = server.Main.get_from_server();
				int id = (int) msg.getObject();
				Client client = new Client(_firstname, _lastname, id+1, _mail, _phone, _credit, _age, _gender, _address, _username, _password);
				msg = new Massage(client,Commands.ADD);
				server.Main.send_toServer(msg);
				System.out.println("added");
                Stage primaryStage =Main.getStage();
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                // primaryStage.setTitle("Hello World");
                GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                int width = gd.getDisplayMode().getWidth();
                int height = gd.getDisplayMode().getHeight();
                primaryStage.setScene(new Scene(root, width, height));

            }

        }
        return;
    }
}
