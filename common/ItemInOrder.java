package common;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemInOrder implements Serializable {
	private ArrayList<Item> itemList;
	private int OrderID;
	private int ClientID;
	private int ID;
	private static int count = 0;
	private int sumOfitems = 0;

	public ItemInOrder(ItemInOrder iio) {
		itemList = new ArrayList<Item>(iio.getItemList());
		OrderID = iio.getOrderID();
		ClientID = iio.getClientID();
		ID = iio.getID();
		sumOfitems = iio.getSumOfitems();
	}

	public ItemInOrder(ArrayList<Item> _itemList, int _orderID, int _clientID) {
		itemList = new ArrayList<Item>(_itemList);
		OrderID = _orderID;
		ClientID = _clientID;
		ID = ++count;
		sumOfitems = getTotalPrice(_itemList);
	}

	public ItemInOrder(int _orderID, int _clientID) {
		itemList = new ArrayList<Item>();
		OrderID = _orderID;
		ClientID = _clientID;
		ID = ++count;
	}

	public int addToList(Item item) {
		itemList.add(item);
		sumOfitems = getTotalPrice();
		return itemList.size() - 1;
	}

	public int removeFromList(Item item) {
		int last = itemList.lastIndexOf(item);
		itemList.remove(last);
		if (last == -1)
			return -1;
		sumOfitems = getTotalPrice();

		return itemList.lastIndexOf(item);
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public int getClientID() {
		return ClientID;
	}

	public void setClientID(int clientID) {
		ClientID = clientID;
	}

	public int getTotalPrice() {
		int price = 0;
		for (int i = 0; i < this.itemList.size(); i++) {
			price += this.itemList.get(i).getPrice();
		}
		return price;
	}

	public int getTotalPrice(ArrayList<Item> items) {
		int price = 0;
		for (int i = 0; i < items.size(); i++) {
			price += items.get(i).getPrice();
		}
		return price;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
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

	public int getSumOfitems() {
		sumOfitems = getTotalPrice();
		return sumOfitems;
	}

	public void setSumOfitems(int sumOfitems) {
		this.sumOfitems = sumOfitems;
	}
}
