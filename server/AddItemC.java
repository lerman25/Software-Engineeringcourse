package server;

import java.net.URL;
import java.util.ResourceBundle;

import common.Commands;
import common.Employee;
import common.Item;
import common.Massage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AddItemC implements Initializable {

    @FXML
    private ColorPicker color;

    @FXML
    private ChoiceBox<Size> size;

    @FXML
    private TextField price;

    @FXML
    private TextField kind;

    @FXML
    private TextField name;

    @FXML
    private Button save;

    @FXML
    private Label errorLabel;

    @FXML
    void save(ActionEvent event) {
        Color selected=	color.getValue();
        // check text fields before... 
        Item newitem = new Item(name.getText(),Integer.parseInt(price.getText()),kind.getText(),selected.toString(),size.getSelectionModel().getSelectedItem());
		Main.send_toServer(new Massage ("Item",Commands.GETLASTID));
		Massage msg = Main.get_from_server();
        newitem.setId(String.valueOf((int)msg.getObject()+1));
        //sucsess prompt
		Main.send_toServer(new Massage (newitem,Commands.ADD));
		msg = Main.get_from_server();
		if(msg.getCommand()!=Commands.DBERROR) 
		{
			// INSERT SUCSESS PROMPT
		}
        
    }

    @FXML
    void color(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		size.getItems().addAll(Size.values());
	
	}

}
