import java.util.ArrayList;

public class ItemInOrder {
	private ArrayList<Item> itemList;
	private int OrderID;
	private int ClientID;
	private int ID;
	private static int count = 0;

	public ItemInOrder(ItemInOrder iio) {
		itemList = new ArrayList<Item>(iio.getItemList());
		OrderID = iio.getOrderID();
		ClientID = iio.getClientID();
		ID = iio.getID();
	}

	public ItemInOrder(ArrayList<Item> itemList, int _orderID, int _clientID) {
		itemList = new ArrayList<Item>(itemList);
		OrderID = _orderID;
		ClientID = _clientID;
		ID = ++count;
	}

	public ItemInOrder(int _orderID, int _clientID) {
		itemList = new ArrayList<Item>();
		OrderID = _orderID;
		ClientID = _clientID;
		ID = ++count;
	}

	public int addToList(Item item) {
		itemList.add(item);
		return itemList.size() - 1;
	}

	public int removeFromList(Item item) {
		int last = itemList.lastIndexOf(item);
		itemList.remove(last);
		if (last == -1)
			return -1;
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
}
