package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button next;
    @FXML
    Button login;
    @FXML
    Button signup;
    @FXML
    Label for_username;
    @FXML
    Label for_password;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


         password.setVisible(false);
        login.setVisible(false);
        signup.setVisible(false);

        username
                .textProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            next.setDisable(false);
                        });

    }
    public void  next(ActionEvent event){
        boolean nouser= FormValidation.textFieldNotEmpty(username,for_username,"Username is required!!");
        if(nouser){
            if(username.getText().equals("soreka")) {
                password.setVisible(true);
                login.setVisible(true);
                username.setVisible(false);
                next.setVisible(false);
                for_username.setVisible(false);
            }else{
                if(!(username.getText().isEmpty()))
                {
                    for_username.setText("This Username dose not Exist!");
                }
            }
            }
        return;
    }
    public void  login(ActionEvent event){
        boolean nouser= FormValidation.textFieldNotEmpty(password,for_password,"Password is required!!");
        if(nouser){
            if(password.getText().equals("soreka")){
                for_password.setTextFill(Color.web("black"));
                for_password.setText("Welcome to Lilac <3 ^_^ .!");


            }else{
                if(!(password.getText().isEmpty()))
                {
                    for_password.setText("Incorrect Password,try again!");

                }}}
        return;
    }

}
