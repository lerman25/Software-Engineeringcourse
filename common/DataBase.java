package common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.mysql.cj.jdbc.Blob;

import java.awt.image.BufferedImage;
import java.io.*;
//

public class DataBase {
	private static DataBase _instance;

//static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	// update USER, PASS and DB URL according to credentials provided by the
	// website:
	// https://remotemysql.com/
	// in future move these hard coded strings into separated config file or even
	// better env variables
	static private final String DB = "SmFGAHPAE1";
//	static private final String DB_URL = "https://remotemysql.com/phpmyadmin/" + DB + "?useSSL=false";
	static private final String DB_URL = "jdbc:mysql://remotemysql.com/" + DB + "?useSSL=false";

	static private final String USER = "SmFGAHPAE1";
	static private final String PASS = "PrfUzkZFEX";
	static private Connection conn;

	private DataBase() {
	}

	public static DataBase getInstance() {
		if (_instance == null) {
			_instance = new DataBase();
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("<<<DATABASE>> CONNECTED TO DB");
		}
		return _instance;
	}

	public ArrayList<Item> get_flowers() {

		ArrayList<Item> catalog = new ArrayList<Item>();
		try {
			ResultSet rs = this.get_TableResultSet("Item");

			while (rs.next()) {
				String Name = rs.getString("Name");
				double Price = rs.getInt("Price");
				String Kind = rs.getString("Kind");
				String Color = rs.getString("Color");
				String Size = (rs.getString("Size"));
				int id = (rs.getInt("ID"));
				System.out.println(id);
				Item newitem = new Item(Name, Price, Kind, Color, Size, String.valueOf(id));
				catalog.add(newitem);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return catalog;

	}
	

	public ArrayList<Item> get_flowers(String criteria, String wanted) {

		ArrayList<Item> catalog = new ArrayList<Item>();
		try {
			ResultSet rs = this.get_TableResultSet("Item");

			while (rs.next()) {
				String Name = rs.getString("Name");
				double Price = rs.getInt("Price");
				String Kind = rs.getString("Kind");
				String Color = rs.getString("Color");
				String Size = (rs.getString("Size"));
				String id = (rs.getString("ID"));
				if (criteria.equals("Price")) {
					if (rs.getInt(criteria) == Integer.parseInt(wanted)) {
						Item newitem = new Item(Name, Price, Kind, Color, Size, id);
						catalog.add(newitem);
					}

				} else {
					if (rs.getString(criteria).equals(wanted)) {
						Item newitem = new Item(Name, Price, Kind, Color, Size, id);
						catalog.add(newitem);
					}
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return catalog;

	}
	public ArrayList<Orders> get_orders() {
		ArrayList<Orders> orders = new ArrayList<Orders>();
		try {
			ResultSet rs = this.get_TableResultSet("Orders");

			while (rs.next()) {
				int id = rs.getInt("ID");
				int clientid= rs.getInt("ClientID");
				Date time = rs.getDate("Time");
				Date orderdate = rs.getDate("OrderDate");
				Date deliverytime = rs.getDate("DeliveryTime");
				int shipment = rs.getInt("Shipment_Method");
				String address = rs.getString("Address");
				int receiverPone = rs.getInt("ReciverPhone");
				String recivername = rs.getString("ReciverName");
               Orders order = new Orders(clientid,time,orderdate,shipment,address,receiverPone,recivername,deliverytime);
               orders.add(order);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return orders;
	}
	public ArrayList<Complaint> get_complaints() {

		ArrayList<Complaint> complaints = new ArrayList<Complaint>();
		try {
			ResultSet rs = this.get_TableResultSet("Complaint");

			while (rs.next()) {
				Date date = rs.getDate("Date");
				String text = rs.getString("TextField");
				int clientID = rs.getInt("ClientID");
				int Status = rs.getInt("Status");
				int OrderID = (rs.getInt("OrderID"));
				int cid = (rs.getInt("ID"));
				Complaint complaint = new Complaint(date, text, clientID, Status, OrderID, cid);
				complaints.add(complaint);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return complaints;

	}

	public ArrayList<Complaint> get_complaints(String criteria, String wanted) {

		ArrayList<Complaint> complaints = new ArrayList<Complaint>();
		try {
			ResultSet rs = this.get_TableResultSet("Complaint");

			while (rs.next()) {
				Date date = rs.getDate("Date");
				String text = rs.getString("TextField");
				int clientID = rs.getInt("ClientID");
				int Status = rs.getInt("Status");
				int OrderID = (rs.getInt("OrderID"));
				int cid = (rs.getInt("ID"));
				if (criteria.equals("ClientID")) {
					if (rs.getInt(criteria) == Integer.parseInt(wanted)) {
						Complaint complaint = new Complaint(date, text, clientID, Status, OrderID, cid);

						complaints.add(complaint);
					}

				} else {
					if (rs.getString(criteria).equals(wanted)) {
						Complaint complaint = new Complaint(date, text, clientID, Status, OrderID, cid);
						complaints.add(complaint);

					}
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return complaints;

	}

	public Person get_person(int id) {
		Person person = null;
		ResultSet rs;
		try {
			rs = this.get_TableResultSet("Person");

			while (rs.next()) {
				int _id = rs.getInt("ID");
				if (id == _id) {
					String firstname = rs.getString("FirstName");
					String lastname = rs.getString("LastName");
					String mail = rs.getString("EMail");
					int phone = rs.getInt("PhoneNumber");
					String credit = rs.getString("CreditCard");
					int age = rs.getInt("Age");
					String gender = rs.getString("Gender");
					String address = rs.getString("Address");
					String username = rs.getString("Username");
					String password = rs.getString("Password");
					person = new Person(firstname, lastname, _id, mail, phone, credit, age, gender, address, username,
							password);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;

	}

	public ArrayList<Employee> get_employees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			ResultSet rs = this.get_TableResultSet("Employee");

			while (rs.next()) {
				int id = rs.getInt("ID");
				Person person = get_person(id);
				int branchid = rs.getInt("BranchID");
				int rank = rs.getInt("Rank");
				employees.add(new Employee(branchid, person.getUsername(),person.getPassword(),person));
				
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return employees;
	}
	public Client get_client(int id) {
		Client client=null;

			ResultSet rs;
			try {
				rs = this.get_TableResultSet("Client");
			
			while (rs.next()) {
				int _id = rs.getInt("ID");
				if(_id==id)
				{
					String user = rs.getString("Username");
					String password = rs.getString("Password");
					client=new Client(user,password,get_person(_id));
					System.out.println("CLient");
				}
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return client;
	}

	public int checkLogin_user(String username, String password) {
		ResultSet rs;
		try {
			rs = get_TableResultSet("Person");

			while (rs.next()) {
				String _password = rs.getString("Password");
				String _username = rs.getString("Username");
				if (_username.equals(username)) {
					if (_password.equals(password)) {
						return rs.getInt("ID");
					} else {
						System.out.println("Wrong Password");
						return 0;
					}
				}

			}
			System.out.println("No Such Username");
			return -1;
		} catch (SQLException e) {
			System.out.println("DB ERROR");
			e.printStackTrace();
			return -2;

		}

	}

	public int checkLogin_user(String username) {
		ResultSet rs;
		try {
			rs = get_TableResultSet("Person");

			while (rs.next()) {
				String _username = rs.getString("Username");
				if (_username.equals(username)) {
					return rs.getInt("ID");

				}

			}
			System.out.println("No Such Username");
			return -1;
		} catch (SQLException e) {
			System.out.println("DB ERROR");
			e.printStackTrace();
			return -2;

		}

	}

	public String add_to_DB(Object object) {

		try {
			String table = object.getClass().getSimpleName();
			if (this.exists_in_DB(object) > 0)//
				return table + " " + object.toString() + " ID IN DB";
			Class cls = Person.class;
			boolean isAflag = cls.isInstance(object);
			boolean isntPerson = !(object.getClass().getSimpleName().equals(cls.getSimpleName()));
			if (isAflag && isntPerson) {
				Person p = new Person((Person) object);
				String output = add_to_DB(p);
				if (output.equals(p.getClass().getSimpleName() + " " + p.toString() + " ID IN DB")) {
					return (object.toString() + " Is in Person");
				}
				System.out.println(output);

			}

			ResultSet rs = this.get_TableResultSet(table);

			boolean id_flag = false;

			switch (table) {
			case "Item": {
				Item item = (Item) object;
				PreparedStatement stmt1 = conn.prepareStatement(
						"INSERT INTO Item(Name, Price, Kind,Color,Size,ID,Image) VALUES (?, ?, ?,?,?,?,?)");
				stmt1.setString(1, item.getName());
				stmt1.setInt(2, (int) item.getPrice());
				stmt1.setString(3, item.getKind());
				stmt1.setString(4, item.getColor());
				stmt1.setInt(5, 11);
				stmt1.setInt(6, Integer.valueOf(item.getId()));
				stmt1.setString(7, "NOTYET");
				stmt1.executeUpdate();
				break;
			}
			case "Person": {
				Person person = (Person) object;
				PreparedStatement stmt1 = conn.prepareStatement(
						"INSERT INTO Person(FirstName,LastName,ID,Email,PhoneNumber,CreditCard,Age,Gender,Address,Username,Password) VALUES (?, ?, ?,?,?,?,?,?,?,?,?)");
				stmt1.setString(1, person.getFirstName());
				stmt1.setString(2, person.getLastName());
				stmt1.setInt(3, person.getId());
				stmt1.setString(4, person.getMail());
				stmt1.setInt(5, person.getPhone_number());
				stmt1.setString(6, person.getCredit_card());
				stmt1.setInt(7, person.getAge());
				stmt1.setString(8, person.getGender());
				stmt1.setString(9, person.getAddress());
				stmt1.setString(10, person.getUsername());
				stmt1.setString(11, person.getPassword());
				stmt1.executeUpdate();
				break;
			}
			case "Client": {
				Client client = (Client) object;
				PreparedStatement stmt1 = conn
						.prepareStatement("INSERT INTO Client(Username,Password,ID) VALUES (?, ?, ?)");
				stmt1.setString(1, client.getUsername());
				stmt1.setString(2, client.getPassword());
				stmt1.setInt(3, client.getId());
				stmt1.executeUpdate();
				stmt1.executeUpdate();

				break;
			}
			case "Employee": {
				Employee employee = (Employee) object;
				PreparedStatement stmt1 = conn.prepareStatement(
						"INSERT INTO Employee(`Username`, `Password`, `BranchID`, `Rank`, `ID` ) VALUES (?, ?, ?, ?, ?)");
				stmt1.setString(1, employee.getUsername());
				stmt1.setString(2, employee.getPassword());
				stmt1.setInt(3, employee.getBranchID());
				stmt1.setInt(4, employee.getRank());
				stmt1.setInt(5, employee.getId());
				stmt1.executeUpdate();
				break;
			}
			case "ShopManager": {
				ShopManager shopManager = (ShopManager) object;
				PreparedStatement stmt1 = conn.prepareStatement(
						"INSERT INTO ShopManager(`Username`, `Password`, `BranchID`, `ID` ) VALUES (?, ?, ?, ?)");
				stmt1.setString(1, shopManager.getUsername());
				stmt1.setString(2, shopManager.getPassword());
				stmt1.setInt(3, shopManager.getBranchID());
				stmt1.setInt(4, shopManager.getId());
				stmt1.executeUpdate();
				break;

			}
			case "ChainManager": {
				ChainManager cm = (ChainManager) object;
				PreparedStatement stmt1 = conn
						.prepareStatement("INSERT INTO ChainManager(Username,Password,ID) VALUES (?, ?, ?)");
				stmt1.setString(1, cm.getUsername());
				stmt1.setString(2, cm.getPassword());
				stmt1.setInt(3, cm.getId());
				stmt1.executeUpdate();
				break;
			}
			case "Orders": {
				System.out.println("order");
				Orders order = (Orders) object;
				PreparedStatement stmt1 = conn.prepareStatement(
						"INSERT INTO Orders(`ID`, `ClientID`, `Time`, `OrderDate`, `Shipment_Method`, `Address`, `ReciverPhone`, `ReciverName`, `DeliveryTime`, `DeliveryCost`, `TotalCost`) VALUES(?, ?, ?,?,?,?,?,?,?,?,?)");
				stmt1.setInt(1, order.getID());
				stmt1.setInt(2, order.getClientID());
				stmt1.setDate(3, order.getTime());
				stmt1.setDate(4, order.getOrderDate());
				stmt1.setInt(5, order.getShipment_Method());
				stmt1.setString(6, order.getAddress());
				stmt1.setInt(7, order.getReciverPhone());
				stmt1.setString(8, order.getReciverName());
				stmt1.setDate(9, order.getDeliveryTime());
				stmt1.setInt(10, order.getDeliveryCost());
				stmt1.setInt(11, order.getItemList().getSumOfitems() + order.getDeliveryCost());
				System.out.println(add_to_DB(order.getItemList()));
				stmt1.executeUpdate();
				break;
			}
			case "ItemInOrder": {
				ItemInOrder items = (ItemInOrder) object;
				PreparedStatement stmt1 = conn.prepareStatement(
						"INSERT INTO ItemInOrder(`ItemID`, `Amount`, `OrderID`, `ClientID`, `ID`) VALUES (?, ?, ?,?,?)");
				ArrayList<Item> itemList = new ArrayList(items.getItemList());
				stmt1.setInt(3, items.getOrderID());
				stmt1.setInt(4, items.getClientID());
				stmt1.setInt(5, items.getID());
				for (int i = 0; i < itemList.size(); i++) {
					int amount = 1;
					Item current = itemList.get(i);
					for (int j = itemList.size() - 1; j > i; j--) {
						if (itemList.get(j).getId() == current.getId()) {
							amount++;
							itemList.remove(j);
						}
					}
					stmt1.setInt(1, Integer.valueOf(current.getId()));
					stmt1.setInt(2, amount);
					stmt1.executeUpdate();

				}
				break;

			}
			case "Complaint": {
				Complaint complaint = (Complaint) object;
				PreparedStatement stmt1 = conn.prepareStatement(
						"INSERT INTO Complaint(`Date`, `TextField`, `ClientID`, `Status`, `OrderID`, `ID`) VALUES (?, ?, ?,?,?,?)");
				stmt1.setDate(1, complaint.getDate());
				stmt1.setString(2, complaint.getText());
				stmt1.setInt(3, complaint.getClientID());
				stmt1.setInt(4, complaint.getStatus());
				stmt1.setInt(5, complaint.getOrderID());
				stmt1.setInt(6, complaint.getID());
				stmt1.executeUpdate();
				break;

			}

			}

			if (this.exists_in_DB(object) > 0)
				return table + " " + object.toString() + " Added to DB";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "FAIL-UNKNOWN";

	}

	public String delete_from_DB(Object object) {
		try {
			String table = object.getClass().getSimpleName();
			if (table.equals("Orders")) {
				Orders order = (Orders) object;
				System.out.println(delete_from_DB(order.getItemList()));
			}
			Class cls = Person.class;
			boolean isAflag = cls.isInstance(object);
			boolean isntPerson = !(object.getClass().getSimpleName().equals(cls.getSimpleName()));
			ResultSet rs = this.get_TableResultSet(table);
			if (isAflag && isntPerson) {
				Person p = new Person((Person) object);
				String out = delete_from_DB(p);
				if (out == "FAIL-NO-ID IN")
					return out;
				PreparedStatement st = conn.prepareStatement("DELETE FROM " + table + " WHERE Username = ?");
				st.setString(1, object.toString());
				st.executeUpdate();
				if (this.exists_in_DB(object) == 0)
					return table + " " + object.toString() + " DELETED FROM DB";
			} else {
				boolean id_flag = false;
				while (rs.next()) {
					String id = (rs.getString("ID"));
					if (id.equals(object.toString())) {
						id_flag = true;
						PreparedStatement st = conn.prepareStatement("DELETE FROM " + table + " WHERE ID = ?");
						st.setInt(1, Integer.valueOf(object.toString()));
						st.executeUpdate();
					}
				}
				if (!id_flag)
					return "FAIL-NO-ID IN";
				if (this.exists_in_DB(object) == 0)
					return table + " " + object.toString() + " DELETED FROM DB";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "UNKNOWN-FAIL";

	}

	public int update_in_DB(Object object) {
		delete_from_DB(object);
		add_to_DB(object);
		return 1;
	}

	public int exists_in_DB(Object object) {
		String table = object.getClass().getSimpleName();
		try {
			Class cls = Person.class;
			boolean isAccountflag = cls.isInstance(object);
			if (isAccountflag) {
				ResultSet rs = this.get_TableResultSet("Person");
				Person p = (Person) object;
				while (rs.next()) {
					String username = (rs.getString("Username"));
					if (username.equals(p.getUsername()))
						return 2;
				}
			}
			ResultSet rs = this.get_TableResultSet(table);
			while (rs.next()) {
				String id = (rs.getString("ID"));
				if (id.equals(object.toString()))
					return 1;
			}
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public int add_image_to_item(int itemID, BufferedImage imm) {
		byte[] immAsBytes;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// use another encoding if JPG is innappropriate for you
		try {
			ImageIO.write(imm, "jpg", baos);

			baos.flush();
			immAsBytes = baos.toByteArray();
			baos.close();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE Item set `Image`=(?) WHERE `ID` = (?)");
			ByteArrayInputStream bais = new ByteArrayInputStream(immAsBytes);
			pstmt.setBinaryStream(1, bais, immAsBytes.length);
			pstmt.setInt(2, itemID);
			pstmt.executeUpdate();
			pstmt.close();
			return 1;
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public BufferedImage get_imageDB(int itemID) {
		Statement stmt;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			PreparedStatement prep_stmt = conn.prepareStatement("SELECT Image FROM Item where id = (?)");
			prep_stmt.setInt(1, itemID);
			ResultSet rs = get_TableResultSet("Item");
			while (rs.next()) {
				String id = (rs.getString("ID"));
				if (id.equals(String.valueOf(itemID)))
					break;
			}
			Blob immAsBlob = (Blob) rs.getBlob("Image");
			byte[] immAsBytes = immAsBlob.getBytes(1, (int) immAsBlob.length());
			InputStream in = new ByteArrayInputStream(immAsBytes);
			BufferedImage imgFromDb = ImageIO.read(in);
			return imgFromDb;

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	public byte[] get_imageDBasByte(int itemID) {
		Statement stmt;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			PreparedStatement prep_stmt = conn.prepareStatement("SELECT Image FROM Item where id = (?)");
			prep_stmt.setInt(1, itemID);
			ResultSet rs = get_TableResultSet("Item");
			while (rs.next()) {
				String id = (rs.getString("ID"));
				if (id.equals(String.valueOf(itemID)))
					break;
			}
			Blob immAsBlob = (Blob) rs.getBlob("Image");
			byte[] immAsBytes = immAsBlob.getBytes(1, (int) immAsBlob.length());
			return immAsBytes;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	private ResultSet get_TableResultSet(String table) throws SQLException {
		Statement stmt;
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM " + table);
		ResultSet rs = prep_stmt.executeQuery();
		return rs;
	}

	private ResultSet get_TableResultSet(String table, String criteria) throws SQLException {
		Statement stmt;
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		PreparedStatement prep_stmt = conn.prepareStatement("SELECT " + criteria + "FROM " + table);
		ResultSet rs = prep_stmt.executeQuery();
		return rs;
	}

	public void table_delete(String table) {
		try {
			PreparedStatement st = conn.prepareStatement("TRUNCATE " + table);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void finalize() throws SQLException {
		System.out.println("<<<DATABASE>> Closing Connection");
		conn.close();
	}
}
