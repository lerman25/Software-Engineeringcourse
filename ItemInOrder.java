import java.util.ArrayList;

public class ItemInOrder {
	private ArrayList<Item> itemList;
	private int OrderID;
	private int ClientID;
	public ItemInOrder(ItemInOrder iio)
	{
		itemList=new ArrayList<Item>(iio.getItemList());
		OrderID=iio.getOrderID();
		ClientID=iio.getClientID();
	}
	public ItemInOrder(ArrayList<Item> itemList , int _orderID,int _clientID)
	{
		itemList=new ArrayList<Item>(itemList);
		OrderID=_orderID;
		ClientID=_clientID;
	}
	public ItemInOrder(int _orderID,int _clientID)
	{
		itemList=new ArrayList<Item>();
		OrderID=_orderID;
		ClientID=_clientID;
	}
	public int addToList(Item item)
	{
		itemList.add(item);
		return itemList.size()-1;
	}
	public int removeFromList(Item item)
	{
		int last = itemList.lastIndexOf(item);
		itemList.remove(last);
		if(last==-1)
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

}
