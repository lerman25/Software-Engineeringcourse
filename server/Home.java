package server;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.Commands;
import common.Item;
import common.Massage;
import common.Orders;

public class Home implements Initializable {
    @FXML
    GridPane catalog;
    @FXML
    TextField name;
    @FXML
    TextField price;
    @FXML
    ImageView my_image;
    @FXML Pane features;
    @FXML ImageView boquet;
    @FXML
    ImageView previous;

    static  int page=0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        previous.setVisible(false);
        //get all items to fill the list of the gridpane
        Massage msg = new Massage();
        msg.setCommand(Commands.GETORDERS);
        kasem.Main.send_toServer(msg);
        msg = kasem.Main.get_from_server();
        ArrayList<Item> orders = (ArrayList<Item>) msg.getObject();
        //
        try {
          //for grid from 0,0 to 2,1
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("Item.fxml"));
            catalog.add(loader.load(),2,0);
            ItemController controller= loader.getController();
            //setItem arraylist<count> |count  1 to 6 or 0 to 5
            controller.setItem("flower!","66","sample/index.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
  //  public void nextpage
        }
    }

