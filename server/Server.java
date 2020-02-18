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

	public Server(int port) {
		super(port);
		connectedClients = new ArrayList<ConnectionToClient>();
		// TODO Auto-generated constructor stub
	}

	protected void clientConnected(ConnectionToClient client) {
		connectedClients.add(client);
		System.out.println("client connected");
	}

	synchronized protected void clientDisconnected(ConnectionToClient client) {
		System.out.println("Client : " + client.getName() + " Disconnected");
		connectedClients.remove(client);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// TODO Auto-generated method stub
		Massage massage = (Massage) msg;
		System.out.println(
				"Massage handling from client: " + client.getName() + " Massage: " + massage.getCommand().toString());

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
		}
		case CHECK: {
			Account account = (Account) massage.getObject();
			System.out.println(mydb.checkLogin_user(account.getUsername(), account.getPassword()));

		}
		case DELETE: {
			System.out.println(mydb.delete_from_DB(massage.getObject()));
		}
		case EXISTS: {
			int test = mydb.exists_in_DB(massage.getObject());
			try {
				client.sendToClient(new Massage((test > 0), Commands.EXISTS));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				client.sendToClient(new Massage(mydb.get_person(massage.getId()), Commands.GETPERSON));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
						clientTest = false;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					connectedClients.remove(i);

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
		}
		}

	}

	public DataBase getDataBase() {
		return mydb;
	}

	protected void serverStarted() {
		mydb = DataBase.getInstance();
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
