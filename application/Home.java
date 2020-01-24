package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Home implements Initializable{
	@FXML
	private ToggleGroup menu= new ToggleGroup();
	@FXML
	private GridPane grid;
	@FXML
	private ToggleButton employee;
	@FXML
	private Button fuck;
	@FXML
	private TabPane tabpane;
	@FXML
	private Tab home;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//home.setToggleGroup(menu);
		employee.setToggleGroup(menu);
		
		}
	
	
 
	public void tabPane(MouseEvent event) {
		
			System.out.println("i'm in");

				try {
					grid.add(render("1"), 0, 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//menu.selectToggle(home);
				
			}
	
	
	private Node render(String name) throws Exception  {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(name + ".fxml"));
		return loader.load();
	} catch (Exception exe) {
		throw new RuntimeException(exe);
	}

	
		
	}
	
	
	
}
	
		
	


