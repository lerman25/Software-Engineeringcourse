package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PayPage  implements Initializable {
    @FXML
    TextField name1;
    @FXML
    TextField creditCard;
    @FXML
    Button payFinal;
    @FXML
    Button backToHome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        }
    public void buyB(MouseEvent event) throws IOException {

        AlertBox.display("Payment","SUCCESS!");
    }
    public void backHome(MouseEvent event) throws IOException {
        Stage primaryStage =Main.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("base.fxml"));
        // primaryStage.setTitle("Hello World");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        primaryStage.setScene(new Scene(root, width, height));
    }
}