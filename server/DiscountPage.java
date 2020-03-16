package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import common.*;

public class DiscountPage {

	@FXML
	private Button confirm;

	@FXML
	private TableColumn<Item, String> item;

	@FXML
	private TableColumn<Item, Integer> price;

	@FXML
	private Label label;
	@FXML
	private TableView<Item> items;

	@FXML
	private TextField percent;

	@FXML
	void confirm(ActionEvent event) {
		if ((percent.getText().length() > 0) && percent.getText().toString().matches("\\d+")) {
			if (Integer.parseInt(percent.getText()) > 0) {
			if (items.getSelectionModel().getSelectedItems().size() < 1) {
				label.setText("Please select items");
			} else {
				ObservableList<Item> selected = items.getSelectionModel().getSelectedItems();
				setDiscount(selected,Integer.parseInt(percent.getText()));
		        AlertBox.display("Discount Setting","SUCCESS!");

			}
			} else {
				label.setText("Please choose an integer bigger then 0");
			}
		}
		else
		{
			label.setText("Please choose an integer percent");

			}

	}

	public void loadTable() {

		item.setCellValueFactory(new PropertyValueFactory<>("Name"));
		price.setCellValueFactory(new PropertyValueFactory<>("Price"));
		items.setItems(null);
		items.setItems(get_list());
		items.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	public ObservableList<Item> get_list() {
		Massage msg = new Massage();
		msg.setCommand(Commands.GETCATALOG);
		server.Main.send_toServer(msg);
		msg = server.Main.get_from_server();
		ArrayList<Item> itemList = new ArrayList<Item>();
		if (msg.getCommand() != Commands.DBERROR)
			itemList = (ArrayList<Item>) msg.getObject();
		ObservableList<Item> item_list = FXCollections.observableArrayList();
		for (int i = 0; i < itemList.size(); i++) {
			item_list.add(itemList.get(i));
		}
		return item_list;

	}

	public void setDiscount(ObservableList<Item> selected, int percentage) {
        AlertBox.display("Discount Setting","Not Yet Implemented!");

	}
}
