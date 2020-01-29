package server;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Item1 implements Initializable {


    @FXML
    private TextField name;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField kind;
    @FXML
    private TextField color;
    @FXML
    private TextField size;
    @FXML
    private TextField price;
    @FXML
    private ImageView backHome;
    @FXML
    private StackPane anchory;
    private Item item;

    Stage window = new Stage();


    public void setItem(Item _item) { // Setting the client-object in ClientViewController
        this.item = _item;
        name.setText(this.item.getName());
        price.setText(Double.toString(this.item.getPrice()));
        kind.setText(this.item.getKind());
        color.setText(this.item.getColor());
        size.setText(this.item.getSize());


    }
           @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {


    }
    public void back(MouseEvent event) throws IOException {
        Stage primaryStage =Main.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("base.fxml"));
        // primaryStage.setTitle("Hello World");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        primaryStage.setScene(new Scene(root, width, height));
    }

}