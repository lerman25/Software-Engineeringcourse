package common;

public class ChainManager extends Person {

	public ChainManager(Person p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public ChainManager(String _firstname, String _lastname, int _id, String _mail, int _phone, String _credit,
			int _age, String _gender, String _address, String _username, String _password) {
		super(_firstname, _lastname, _id, _mail, _phone, _credit, _age, _gender, _address, _username, _password);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return this.getUsername();
	}
}
