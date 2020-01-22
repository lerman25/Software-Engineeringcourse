package server;
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
