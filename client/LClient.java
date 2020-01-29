package client;
import java.util.ArrayList;

import common.*;
import ocsf.client.AbstractClient;

public class LClient extends AbstractClient {
	private Massage returnMassage = null;
	public LClient(String host, int port) {
		super(host, port);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		System.out.println("hey");
		setReturnMassage((Massage)msg);
	}

	public Massage getReturnMassage() {
		return returnMassage;
	}

	public void setReturnMassage(Massage returnMassage) {
		this.returnMassage = returnMassage;
	}


}
