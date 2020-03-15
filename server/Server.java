package server;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import common.*;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {
	private DataBase mydb;
	private ArrayList<ConnectionToClient> connectedClients;
	private boolean clientTest = false;
	private boolean dbFlag = true;

	public Server(int port) {

		super(port);
		System.out.println("Server Loading...");
		connectedClients = new ArrayList<ConnectionToClient>();
		// TODO Auto-generated constructor stub
	}
	protected void sendToAllClients(Massage msg)
	{
		for(int i = 0; i<connectedClients.size();i++)
		{
			if(connectedClients.get(i).isAlive())
				try {
					connectedClients.get(i).sendToClient(msg);
					Thread.sleep(300);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	protected void clientConnected(ConnectionToClient client) {
		connectedClients.add(client);
		System.out.println(connectedClients.size());
		System.out.println("client connected - ID : " +  client.getId());
	}

	synchronized protected void clientDisconnected(ConnectionToClient client) {
		System.out.println("Client : " + client.getId() + " Disconnected");
		connectedClients.remove(client);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// TODO Auto-generated method stub
		Massage massage = (Massage) msg;
		System.out.println(
				"Massage handling from client: " + client.getName() + " Massage: " + massage.getCommand().toString());
		if(dbFlag==false)
			try {
				client.sendToClient(new Massage(true, Commands.DBERROR));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
		{
		String user = massage.getUsername();
		String pass = massage.getPassword();
		if (massage.getCommand() == Commands.LOGIN) {
			int login = mydb.checkLogin_user(user, pass);
			int userid = mydb.checkLogin_user(user, pass);
			if (login > 0) {
				client.setInfo("username", user);
				try {
					client.sendToClient(new Massage(userid, Commands.LOGIN));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			try {
				client.sendToClient(new Massage(-1, Commands.LOGIN));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		switch (massage.getCommand()) {
		case ADD: {
			System.out.println(mydb.add_to_DB(massage.getObject()));
			try {
				client.sendToClient(new Massage(true, Commands.ADD));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		}
		case CHECK: {
			Account account = (Account) massage.getObject();
			System.out.println(mydb.checkLogin_user(account.getUsername(), account.getPassword()));
			break;

		}
		case DELETE: {
			System.out.println(mydb.delete_from_DB(massage.getObject()));
			try {
				client.sendToClient(new Massage(true, Commands.DELETE));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		}
		case EXISTS: {
			int test = mydb.exists_in_DB(massage.getObject());
			try {
				client.sendToClient(new Massage((test > 0), Commands.EXISTS));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case GETCATALOG: {
			try {
				Massage m = new Massage(mydb.get_flowers(), Commands.GETCATALOG);
				client.sendToClient(m);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		}
		case UPDATE: {
			mydb.update_in_DB(massage.getObject());
			break;
		}
		case GETIMAGE: {
			Item item1 = (Item) massage.getObject();
			byte[] imm = mydb.get_imageDBasByte((Integer.parseInt(item1.getId())));
			try {
				client.sendToClient(new Massage(imm, Commands.GETIMAGE));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case ADDIMAGE: {
			BufferedImage imm = (BufferedImage) massage.getObject();
			int id = massage.getId();
			mydb.add_image_to_item(id, imm);
			break;
		}
		case GETEMPLOYEES: {
			try {
				client.sendToClient(new Massage(mydb.get_employees(), Commands.GETEMPLOYEES));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case GETORDERS: {
			try {
				client.sendToClient(new Massage(mydb.get_orders(), Commands.GETORDERS));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case GETPERSON: {
			try {
				client.sendToClient(new Massage(mydb.get_person((int) massage.getObject()), Commands.GETPERSON));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case GETCLIENT: {
			try {
				client.sendToClient(new Massage(mydb.get_client((int) massage.getObject()), Commands.GETCLIENT));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case LOGINUSERNAME: {
			int test = mydb.checkLogin_user(massage.getUsername());
			if (test > 0) {
				try {
					client.sendToClient(new Massage(true, Commands.LOGINUSERNAME));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				try {
					client.sendToClient(new Massage(false, Commands.LOGINUSERNAME));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			break;
		}
		case GETLASTID: {
			String object = (String) massage.getObject();
			int id = mydb.getLastID(object);
			try {
				client.sendToClient(new Massage(id, Commands.GETLASTID));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case CONNECTED: {
			boolean flag = false;
			String username = (String) massage.getObject();

			for (int i = 0; i < connectedClients.size(); i++) {
				if (connectedClients.get(i).getName().equals(username)) {

					try {
						client.sendToClient(new Massage(true, Commands.CONNECTED));
						flag = true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 

			}
			if (!flag) {
				client.setName(username);
				try {
					client.sendToClient(new Massage(false, Commands.CONNECTED));
					System.out.println("User " + username + " Connected");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
		case PONG: {
			clientTest = true;
			break;
		}
		case LOGOUT:
		{
			String username = (String) massage.getObject();
			for (int i = 0; i < connectedClients.size(); i++) {
				if (connectedClients.get(i).getName().equals(username)) {
					connectedClients.remove(i);
				}

			}
			System.out.println(username+" Logged Out");
		}
		case CLIENTORDERS: // this function is not effcient , but okay for project.
		{
			int id = (int)massage.getObject();
			ArrayList<Orders> orders = mydb.get_orders();
			ArrayList<Orders> clientO = new ArrayList<Orders>();
			for(int i = 0 ; i < orders.size();i++)
			{
				if(orders.get(i).getClientID()==id)
				{
					System.out.println("ADDDDD");
					clientO.add(orders.get(i));
				}
			}
			try {
				client.sendToClient(new Massage(clientO, Commands.CLIENTORDERS));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case GETITEMSORDER:
		{
			int id = (int)massage.getObject();
			try {
				client.sendToClient(new Massage(mydb.getItemsInOrder(id),Commands.GETITEMSORDER));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		}
		case COMPLAIN:
		{
			Complaint complaint =(Complaint) massage.getObject();
			mydb.add_to_DB(complaint);
			break;
		}
		case GETCOMPLAINTS:
		{
			try {
				if(massage.getId()<=0) // this is for all complaints
				client.sendToClient(new Massage(mydb.get_complaints(),Commands.GETCOMPLAINTS));
				else // for specific client id
					client.sendToClient(new Massage(mydb.get_complaints(massage.getId()),Commands.GETCOMPLAINTS));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		}
		}
	}

	public DataBase getDataBase() {
		return mydb;
	}

	protected void serverStarted() {
		mydb = DataBase.getLocalInstance("remote");
		if(mydb.workFlag == false)
		{
			dbFlag = false;
		}
	}

	protected void serverStopped() {
		try {
			mydb.finalize();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
