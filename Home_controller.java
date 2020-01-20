
import java.awt.Image;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author m.r taya
 *
 */
import Entity.*;
public class Home_controller {

			private static  Home_controller _instance;
		
			private Home_controller() {
	}
			public static Home_controller getInstance() {
				if (_instance == null)
					_instance = new Home_controller();
				return _instance;
			}
			
			
			// ******************************************** get all
			// items********************************************

			String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Nemo_ships;user=sas;password=123123147";
			public ArrayList<Item> getCustomers() {
				ArrayList<Item> results = new ArrayList<Item>();
				try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {
					String SQL = "{Call ALLcustomers}";
					ResultSet rs = stmt.executeQuery(SQL);

					while (rs.next()) {
						int i = 1;
						results.add(new Item(rs.getString(i++),rs.getDouble(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++),rs.getString(i++)));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return results;
			}

			// ********************************************insert
			// customers********************************************

			public void insItem(String _name,double  _Price, String	_Kind, String	_Color, String  _Size,String     _id ) {
			
				try (Connection con = DriverManager.getConnection(connectionUrl); Statement st = con.createStatement();) {
			
					int i = 1;
					CallableStatement st1 = con.prepareCall("{Call querycustomers (?,?,?,?,?,?,?,?)}");
					st1.setString(i++,  _name);
					st1.setDouble(i++, _Price);
					st1.setString(i++, _Kind);
					st1.setString(i++, _Color);
					st1.setString(i++, _Size);
					st1.setString(i++, _id);
					st1.setString(i++, "insert");
					st1.execute();
			
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}
			
			}
			/// ************************************************* delete Item
			/// ************************************************
			public void delCustomer(String intID) {

				try (Connection con = DriverManager.getConnection(connectionUrl); Statement st = con.createStatement();) {

					int i = 1;
					CallableStatement st1 = con.prepareCall("{Call delcustomer (?)}");
					st1.setString(i++, intID);
					st1.execute();

				} catch (Exception e) {
					System.out.print(e.getMessage());
				}

			}
}
