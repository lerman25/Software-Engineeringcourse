package server;
import java.io.IOException;
import java.sql.SQLException;

import common.*;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {
	private DataBase mydb;
	public Server(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// TODO Auto-generated method stub
		Massage massage=(Massage)msg;
		switch(massage.getCommand())
		{
			case ADD:
			{
				System.out.println(mydb.add_to_DB(massage.getObject()));
			}
			case CHECK:
			{
				Account account = (Account)massage.getObject();
				System.out.println(mydb.checkLogin_user(account.getUsername(),account.getPassword()));

			}
			case DELETE:
			{
				System.out.println(mydb.delete_from_DB(massage.getObject()));
			}
			case EXISTS:
			{
				mydb.exists_in_DB(massage.getObject());
			}
			case GETCATALOG:
			{
				try {
					client.sendToClient(mydb.get_flowers());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			case UPDATE:
			{
				System.out.println("TO BE IMPLEMENTED");
			}
		}
		

	}
	protected void serverStarted()
	{
		mydb=DataBase.getInstance();
	}
	protected void serverStopped()
	{
		try {
			mydb.finalize();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
