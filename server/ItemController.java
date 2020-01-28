package server;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class ItemController {

    private ItemGUI item;
   @FXML
   TextField name;
   @FXML
    TextField price;
   @FXML
    ImageView my_image;




    public void setItem(ItemGUI item) {

        this.item = item ;

        if (item == null) {
            name.setText(null);
            price.setText(null);
            my_image.setImage(null);
        } else {
            name.setText(item.getName());
            price.setText(item.getPrice());
            Image wayo = new Image(item.getUrl());
            my_image.setImage(wayo);


        }
    }
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }
}
