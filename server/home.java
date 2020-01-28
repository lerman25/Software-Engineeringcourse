package server;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class home implements Initializable {
    @FXML
    GridPane catalog;
    @FXML
    TextField name;
    @FXML
    TextField price;
    @FXML
    ImageView my_image;



    @FXML
    private ListView<ItemController> searchList ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        }
    }

