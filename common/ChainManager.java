package common;

import java.io.Serializable;

import server.Permissions;

public class ChainManager extends Person implements Serializable {

	public ChainManager(Person p) {
		super(p);
		super.setPermission(Permissions.CHAINMANAGER);
		// TODO Auto-generated constructor stub
	}

	public ChainManager(String _firstname, String _lastname, int _id, String _mail, int _phone, String _credit,
			int _age, String _gender, String _address, String _username, String _password) {
		super(_firstname, _lastname, _id, _mail, _phone, _credit, _age, _gender, _address, _username, _password);
		super.setPermission(Permissions.CHAINMANAGER);

		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return String.valueOf(this.getId());
	}
}
