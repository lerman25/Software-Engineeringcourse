import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataBase myDB = DataBase.getInstance();
		//myDB.table_delete("Item");
		//Item

		Client c = new Client("Omer", "Lerman", 19, "@gmail", 054, "5426", 22, "Male", "haifa", "omerlerman2", "test");
		Employee em = new Employee(1, "Omer", "Lerman", 20, "@gmail", 054, "5426", 22, "Male", "haifa", "omerlerman3",
				"test");
		Item item1 = new Item("item1",10,"kind1","color1","size1");
		Item item2 = new Item("item2",20,"kind2","color2","size2");
		Item item3 = new Item("item3",30,"kind3","color3","size3");
		Item item4 = new Item("item4",40,"kind4","color4","size4");
		ArrayList<Item> list = new ArrayList();
		list.add(item4);
		list.add(item4);
		list.add(item4);
		list.add(item4);
		list.add(item3);
		list.add(item3);
		list.add(item3);
		list.add(item2);
		list.add(item2);
		list.add(item1);
		Orders order =new Orders(c.getId(), new Date(22, 1, 1), new Date(22, 1, 1), 0, c.getAddress(), 0, c.getFirstName(), new Date(22, 1, 1), 0);
		ItemInOrder iio = new ItemInOrder(order.getID(),c.getId());
		iio.setItemList(list);
		order.setItemList(iio);
		
		ShopManager sm = new ShopManager(1, "Omer", "Lerman", 20, "@gmail", 054, "5426", 22, "Male", "haifa",
				"omerlerman1", "test");
		ChainManager cm = new ChainManager("Omer", "Lerman", 20, "@gmail", 054, "5426", 22, "Male", "haifa",
				"omerlerman4", "test");
		System.out.println(myDB.add_to_DB(order));
		System.out.println(myDB.delete_from_DB(order));

	/*	System.out.println(myDB.add_to_DB(c));
		System.out.println(myDB.add_to_DB(em));
		System.out.println(myDB.add_to_DB(sm));
		System.out.println(myDB.add_to_DB(cm));
		System.out.println(myDB.add_to_DB(order));

		System.out.println(myDB.checkLogin_user("omerlerman", "test"));
		System.out.println(myDB.checkLogin_user("omerlerman", "test1"));
		System.out.println(myDB.checkLogin_user("omerlerman2", "test"));
		System.out.println(myDB.checkLogin_user("omerlerman3", "test"));
		System.out.println(myDB.delete_from_DB(c));
		System.out.println(myDB.delete_from_DB(em));
		System.out.println(myDB.delete_from_DB(sm));
		System.out.println(myDB.delete_from_DB(cm));

		/*ArrayList<Item> my = myDB.get_flowers();
		for (int i = 0; i < my.size(); i++) {
			System.out.println(my.get(i).to_String() + "\n");
		}
		System.out.println(myDB.delete_from_DB(my.get(my.size() - 1)));
		my = myDB.get_flowers();
		for (int i = 0; i < my.size(); i++) {
			System.out.println(my.get(i).to_String() + "\n");
		}
*/
		try {
			myDB.finalize();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
