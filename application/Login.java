 package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
//import org.h2.util.New;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** this class controls the login windows that is shown in order to manage users */
public class Login implements Initializable {
 
  @FXML JFXTextField userName;
  @FXML JFXPasswordField password;
  @FXML Text password_text;
  @FXML AnchorPane ap;
  @FXML JFXButton login;
  @FXML JFXButton next;
  @FXML private JFXButton loginSignup;
  @FXML private JFXButton Back;
  @FXML private JFXTextField NewuserName;
  @FXML private Text password_text1;
  @FXML private JFXPasswordField NewPassword;
  @FXML private JFXPasswordField NewPassword1;
  @FXML private JFXButton signup;
  @FXML private Group grpSignup;
  @FXML private Group grpLogin;
  RequiredFieldValidator reqUserValidator = new RequiredFieldValidator();
  RequiredFieldValidator reqPasswordValidator = new RequiredFieldValidator();

  /**
   * Initializer method for the login window
   *
   * @param app
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
  	// TODO Auto-generated method stub
	  
	    password.setVisible(false);
	    login.setVisible(false);
	   userName
	        .textProperty()
	        .addListener(
	            (observable, oldValue, newValue) -> {
	              next.setDisable(false);
	            });

	    reqUserValidator.setMessage("User Name is Required");
	    reqPasswordValidator.setMessage("Password is Required");
	    userName.getValidators().add(reqUserValidator);
	    password.getValidators().add(reqPasswordValidator);
	    NewuserName.getValidators().add(reqUserValidator);
	    NewPassword.getValidators().add(reqPasswordValidator);
	    NewPassword1.getValidators().add(reqPasswordValidator);
	  
  }
  

  /**
   * login - responsible to authernticate the password of the user and validate password length and
   * redirecting to the menu window
   *
   * @param mouseEvent
   * @throws SQLException
   */
  @FXML
  public void login(MouseEvent mouseEvent) throws SQLException {
    if (userName.validate()) {
      if (password.validate()) {
        if ((DataBase.getInstance().checkLogin_user(userName.getText(),password.getText()) >= 0)) {
          Toast.toast("Logged In Successfully " + userName.getText(), Color.GREEN);
          
          Parent root;
          try {
              root = FXMLLoader.load(getClass().getClassLoader().getResource("Home.fxml"));
              Stage stage = new Stage();
              stage.setScene(new Scene(root, 1200, 800));
              stage.show();
              // Hide this current window (if this is what you want)
              ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
          }
          catch (IOException e) {
              e.printStackTrace();
          }
        } else {
          Toast.toast("Invalid Password", Color.RED);
        }
      }
    }
  }

  /**
   * next - handles the click on next in the login window which is responsible to show password
   * field to user and authenticate
   *
   * @param mouseEvent
   * @throws SQLException
   */
  @FXML
  public void next(MouseEvent mouseEvent) throws SQLException {
    if (userName.validate()) {
       if ((DataBase.getInstance().checkLogin_user(userName.getText()) >= 0)) {
        /** Registered User */
        next.setDisable(true);
        password.setVisible(true);
        login.setVisible(true);
      } else { // username does not exist
        Toast.toast("User does Not Exist", Color.RED);
      }
    }
  }

  /**
   * backToLogin - returns the user to login page and hides signup
   *
   * @param event
   */
  @FXML
  void backToLogin(MouseEvent event) {
    grpSignup.setVisible(false);
    grpLogin.setVisible(true);
    userName.setText(NewuserName.getText());
    NewuserName.clear();
    NewPassword.clear();
    NewPassword1.clear();
  }

  /**
   * navSignup - enable user to register/create a new account
   *
   * @param event
   */
  @FXML
  void navSignup(MouseEvent event) {
    grpLogin.setVisible(false);
    grpSignup.setVisible(true);
    userName.clear();
    password.clear();
  }

  /**
   * signupNewPlayer - handles adding a new player - clicking submit on signup window
   *
   * @param event
   * @throws SQLException
   */
/*  @FXML
  void signup(MouseEvent event) throws SQLException {
    if (NewuserName.validate()) {
      if((DataBase.getInstance().checkLogin_user(NewuserName.getText(),password.getText()) >= 0)) {
        // user already Exist
        Toast.toast("User Already Exists ", Color.RED);
      } else { // username do not exist
        if (NewPassword.validate()) {
          if (NewPassword1.validate()) {
            if (NewPassword.getText().equals(NewPassword1.getText())) {
              app.getDatabase().newPlayer(userName, NewPassword.getText());
              grpSignup.setVisible(false);
              grpLogin.setVisible(true);
              userName.setText(NewuserName.getText());
              NewuserName.clear();
              NewPassword.clear();
              NewPassword1.clear();

            } else { // passwords do not match
              Toast.toast("Passwords Do Not Match !", Color.RED);
            }
          }
        }
      }
    }
  }
  */
}
