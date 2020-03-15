package common;

import java.io.Serializable;
import java.sql.Date;

public class GreetingCard implements Serializable {
	private String text;
	private int clientID;
	private int orderID;
	private int ID;

	public GreetingCard(GreetingCard complient) {
		text = complient.getText();
		clientID = complient.getClientID();
		orderID = complient.getOrderID();
		ID = complient.getID();
	}

	public GreetingCard(Date _date, String _text, int _clientID, int _status, int _orderID, int id) {
		text = _text;
		clientID = _clientID;
		orderID = _orderID;
		ID = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String toString() {
		return String.valueOf(this.ID);
	}

}
