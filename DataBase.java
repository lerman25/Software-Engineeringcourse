
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.*;

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
				String id = (rs.getString("ID"));
				catalog.add(new Item(Name, Price, Kind, Color, Size, id));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return catalog;

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

	public String add_to_DB(Object object) {

		try {
			String table = object.getClass().getName();
			if (this.exists_in_DB(object) > 1)
				return table + " " + object.toString() + " ID IN DB";
			Class cls = Person.class;
			boolean isAflag = cls.isInstance(object);
			boolean isntPerson = !(object.getClass().getName().equals(cls.getName()));
			if (isAflag && isntPerson) {
				Person p = new Person((Person) object);
				String output = add_to_DB(p);
				if (output.equals(p.getClass().getName() + " " + p.toString() + " ID IN DB")) {
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
			case "ChainManager" : 
			{
				ChainManager cm = (ChainManager) object;
				PreparedStatement stmt1 = conn
						.prepareStatement("INSERT INTO ChainManager(Username,Password,ID) VALUES (?, ?, ?)");
				stmt1.setString(1, cm.getUsername());
				stmt1.setString(2, cm.getPassword());
				stmt1.setInt(3, cm.getId());
				stmt1.executeUpdate();
			}
			}
			rs = this.get_TableResultSet(table);

			boolean id_flag2 = false;

			while (rs.next()) {
				String id = (rs.getString("ID"));
				if (id.equals(object.toString()))
					id_flag2 = true;
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
			String table = object.getClass().getName();
			Class cls = Person.class;
			boolean isAflag = cls.isInstance(object);
			boolean isntPerson = !(object.getClass().getName().equals(cls.getName()));
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

	public int exists_in_DB(Object object) {
		String table = object.getClass().getName();
		System.out.println("<<<<<<<<<" + table);
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

	private ResultSet get_TableResultSet(String table) throws SQLException {
		Statement stmt;
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		PreparedStatement prep_stmt = conn.prepareStatement("SELECT * FROM " + table);
		ResultSet rs = prep_stmt.executeQuery();
		return rs;
	}

	public void person_delete(String table) {
		try {
			PreparedStatement st = conn.prepareStatement("TRUNCATE " + table);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void finalize() throws SQLException {
		System.out.println("<<<DATABASE>> Closing Connection");
		conn.close();
	}
}