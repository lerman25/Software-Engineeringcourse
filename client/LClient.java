package client;
import java.io.IOException;
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
	protected synchronized void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		Massage massage = (Massage)msg;
		if(massage.getCommand().equals(Commands.PING))
		{
			try {
				this.sendToServer(new Massage(true,Commands.PONG));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			setReturnMassage(massage);
			returnMassage.notifyAll();
		}
	}

	public synchronized Massage  getReturnMassage() {
		Massage newmsg = returnMassage;
		returnMassage=null;
		return newmsg;
		
	}
	public boolean isnull()
	{
		return returnMassage==null;
	}

	public void setReturnMassage(Massage returnMassage) {
		this.returnMassage = returnMassage;
	}


}
