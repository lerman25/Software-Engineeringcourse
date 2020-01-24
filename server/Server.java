package server;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import common.*;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {
	private DataBase mydb;
	private ArrayList<ConnectionToClient> connectedClients;
	public Server(int port) {
		super(port);
		connectedClients=new ArrayList<ConnectionToClient>();
		// TODO Auto-generated constructor stub
	}
	  protected void clientConnected(ConnectionToClient client) {
		  connectedClients.add(client);
	  }
	  synchronized protected void clientDisconnected(
			    ConnectionToClient client) {
		  connectedClients.remove(client);
	  }


	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// TODO Auto-generated method stub
		Massage massage=(Massage)msg;
		String user = massage.getUsername();
		String pass= massage.getPassword();
		System.out.println(user);
		System.out.println(pass);
		if(massage.getCommand()==Commands.LOGIN)
		{
			int login= mydb.checkLogin_user(user,pass);
			System.out.println(mydb.checkLogin_user(user,pass));
			if(login>0)
			{
				client.setInfo("username",user);
			}
		}
		/*switch(massage.getCommand())
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
		
*/
	}
	public DataBase getDataBase()
	{
		return mydb;
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
