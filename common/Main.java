package common;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import client.LClient;
import server.Server;

public class Main {

	public static void main(String[] args) {
		Complaint complaint1 = new Complaint(new Date(2002,12,12),"not okay",1,1,1);
		Complaint complaint2 = new Complaint(new Date(2002,12,12),"not okay",2,2,1);
		Complaint complaint3 = new Complaint(new Date(2002,12,12),"not okay",3,1,1);
		Complaint complaint4 = new Complaint(new Date(2002,12,12),"not okay",4,3,4);
		Complaint complaint5 = new Complaint(new Date(2002,12,12),"not okay",2,1,4);
		DataBase myDB = DataBase.getInstance();
		myDB.add_to_DB(complaint5);
		myDB.add_to_DB(complaint4);
		myDB.add_to_DB(complaint3);
		myDB.add_to_DB(complaint2);
		myDB.add_to_DB(complaint1);
		ArrayList<Complaint> search = new ArrayList<Complaint>();
		search=myDB.get_complaints();
		System.out.println("ALL");
		for(int i=0; i<search.size();i++)
		{
			String id =search.get(i).toString();
			System.out.println(id);
		}
		search=myDB.get_complaints("ClientID","2");
		System.out.println("ClientID");
		for(int i=0; i<search.size();i++)
		{
			String id =search.get(i).toString();
			System.out.println(id);
		}
		search=myDB.get_complaints("Status","1");
		System.out.println("ClientID");
		for(int i=0; i<search.size();i++)
		{
			String id =search.get(i).toString();
			System.out.println(id);
		}
		search=myDB.get_complaints("OrderID","1");
		System.out.println("ClientID");
		for(int i=0; i<search.size();i++)
		{
			String id =search.get(i).toString();
			System.out.println(id);
		}
	}

}
