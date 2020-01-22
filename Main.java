import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	DataBase myDB= DataBase.getInstance();
		myDB.person_delete("Item");

			Item it = new Item("Omer",11,"OMER","OMER","11","12");
			System.out.println(myDB.add_to_DB(it));
			System.out.println(myDB.add_to_DB(new Item("Omer",11,"OMER","OMER","11","12")));
			System.out.println(myDB.add_to_DB(new Item("Omer",11,"OMER","OMER","11","13")));
			System.out.println(myDB.add_to_DB(new Item("Omer",11,"OMER","OMER","11","14")));
			Client c = new Client("Omer","Lerman",19,"@gmail",054,"5426",22,"Male","haifa","omerlerman2","test");
			Employee em = new Employee(1,"Omer","Lerman",20 ,"@gmail",054,"5426",22,"Male","haifa","omerlerman3","test");
			ShopManager sm = new ShopManager(1,"Omer","Lerman",20 ,"@gmail",054,"5426",22,"Male","haifa","omerlerman3","test");
			System.out.println(myDB.add_to_DB(c));
			System.out.println(myDB.add_to_DB(em));
			System.out.println(myDB.add_to_DB(sm));
			System.out.println(myDB.checkLogin_user("omerlerman", "test"));
			System.out.println(myDB.checkLogin_user("omerlerman", "test1"));
			System.out.println(myDB.checkLogin_user("omerlerman2", "test"));
			System.out.println(myDB.checkLogin_user("omerlerman3", "test"));
			System.out.println(myDB.delete_from_DB(it));
			System.out.println(myDB.delete_from_DB(c));
			System.out.println(myDB.delete_from_DB(em));
			System.out.println(myDB.delete_from_DB(sm));

	
		

			ArrayList<Item> my = myDB.get_flowers();
		for(int i=0;i<my.size();i++)
		{
			System.out.println(my.get(i).to_String()+"\n");
		}
	System.out.println(myDB.delete_from_DB(my.get(my.size()-1)));
	 my = myDB.get_flowers();
		for(int i=0;i<my.size();i++)
		{
			System.out.println(my.get(i).to_String()+"\n");
		}
		
		try {
			myDB.finalize();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
