package item;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ItemController implements Initializable {

	
	@FXML
	private TextField  name;
	@FXML
	private TextField explaination ;
	@FXML
	private ImageView   imageview;
	@FXML
	private TextField   kind;
	@FXML
	private TextField  color;
	@FXML
	private TextField  size;
	@FXML
	private TextField  ID;
	@FXML
	private TextField  price;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//imageview.setImage("");
	}
	
public void enterName(ActionEvent  event) {
	 
	name.setText("my_flower");
}
public void enterExplain(ActionEvent  event) {
	 
	explaination.setText("my_explaination");
}
public void enterKind(ActionEvent  event) {
	 
	kind.setText("my_kind");
}
public void enterColor(ActionEvent  event) {
	 
	color.setText("my_color");
}
public void enterSize(ActionEvent  event) {
	 
	size.setText("my_size");
}
public void enterId(ActionEvent  event) {
	 
	ID.setText("my_id");
}
public void enterPrice(ActionEvent  event) {
	 
	price.setText("my_price");
}
}