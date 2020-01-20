import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			DataBase myDB= DataBase.getInstance();
			System.out.println(myDB.add_to_DB(new Item("Omer",11,"OMER","OMER","11","12")));
			System.out.println(myDB.add_to_DB(new Item("Omer",11,"OMER","OMER","11","12")));
			System.out.println(myDB.add_to_DB(new Item("Omer",11,"OMER","OMER","11","13")));
			System.out.println(myDB.add_to_DB(new Item("Omer",11,"OMER","OMER","11","14")));

			ArrayList<Item> my = myDB.get_flowers();
		for(int i=0;i<my.size();i++)
		{
			System.out.println(my.get(i).to_String()+"\n");
		}
	System.out.println(myDB.delet_from_DB(my.get(my.size()-1)));
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
