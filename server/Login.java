package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.DataBase;

public class Login implements Initializable {
    @FXML
    AnchorPane anchorer;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button next;
    @FXML
    Button skip;
    @FXML
    Button login;
    @FXML
    Button signup;
    @FXML
    Label for_username;
    @FXML
    Label for_password;

	DataBase mydb ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	mydb=DataBase.getInstance();
    	DataBase mydb = DataBase.getInstance();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
       
        
        anchorer.setPrefHeight(height);
        anchorer.setMinHeight(height);
        anchorer.setMaxHeight(height);
        anchorer.setPrefWidth(width);
        anchorer.setMinWidth(width);
        anchorer.setMaxWidth(width);
        password.setVisible(false);
        login.setVisible(false);
        signup.setVisible(false);
       /* username.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
               next(e);
            }
        });*/
        for_username.setText("please enter your Username:");
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
            if(mydb.checkLogin_user(username.getText())>0) {
                password.setVisible(true);
                login.setVisible(true);
                next.setVisible(false);
                for_password.setText("Enter your Password");
                for_username.setVisible(false);
            }else{
                if(!(username.getText().isEmpty()))
                {
                    for_username.setText("Username dosen't Exist!");
                }
            }
            }
        return;
    }
    public void  skip(ActionEvent event) throws IOException {
        Stage primaryStage =Main.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("base.fxml"));
        // primaryStage.setTitle("Hello World");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        primaryStage.setScene(new Scene(root, width, height));
    }


    public void  login(ActionEvent event) throws IOException {
        boolean nouser= FormValidation.textFieldNotEmpty(password,for_password,"Password is required!!");
        if(nouser){
            if(mydb.checkLogin_user(username.getText(),password.getText())>0){
                for_password.setTextFill(Color.web("black"));
                for_password.setText("Welcome to Lilac <3 ^_^ .!");
               //  AnchorPane newanchor = FXMLLoader.load(getClass().getResource("sample.fxml"));
              //   anchorer.getChildren().setAll(newanchor);
                // primaryStage.setTitle("Hello World");
                Stage primaryStage =Main.getStage();
                Parent root = FXMLLoader.load(getClass().getResource("base.fxml"));
                // primaryStage.setTitle("Hello World");
                GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                int width = gd.getDisplayMode().getWidth();
                int height = gd.getDisplayMode().getHeight();
                primaryStage.setScene(new Scene(root, width, height));




            }else{
                if(!(password.getText().isEmpty()))
                {
                    for_password.setText("Incorrect Password!!");

                }}}
        return;
    }

}
