import java.sql.Date;

public class Complaint {
	 private Date date;
	 private  String text;
	 private int clientID;
	 private int status;
	 private  int orderID;
	 private int ID;
	 private static int counter=0;
	 public Complaint(Complaint complient)
	 {
		 date=complient.getDate();
		 text=complient.getText();
		 clientID=complient.getClientID();
		 status=complient.getStatus();
		 orderID=complient.getOrderID();
		 ID=complient.getID();
	 }
	 public Complaint(Date _date,String _text,int _clientID,int _status,int _orderID)
	 {
		 date=_date;
		 text=_text;
		 clientID=_clientID;
		 status=_status;
		 orderID=_orderID;
		 ID=++counter;
	 }
	 
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		status = status;
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
