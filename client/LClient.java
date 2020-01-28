package client;
import java.util.ArrayList;

import common.*;
import ocsf.client.AbstractClient;

public class LClient extends AbstractClient {
	public ArrayList<Item> catalog;
	public LClient(String host, int port) {
		super(host, port);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		Massage m = (Massage)msg;
		catalog=(ArrayList<Item>)m.getObject();
	}

}
