package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


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




//    public void setItem(Item item) {
//
//        this.item = item ;
//
//        if (item == null) {
//            name.setText(null);
//            price.setText(null);
//            my_image.setImage(null);
//        } else {
//            name.setText(item.getName());
//            price.setText(item.getPrice());
//            Image wayo = new Image(item.getUrl());
//            my_image.setImage(wayo);
//
//
//        }
//    }
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }
public void setItem(String name1,String price1,String image1 ) {
        name.setText(name1);
        price.setText(price1);
        Image wayo = new Image(image1);
        my_image.setImage(wayo);


}
    @FXML
    public void setMy_image(MouseEvent event) {

        AlertBox.display("Alert!","Do you want to add this item to cart ?");
       return;
    }
}
