//needs to be changed if needed to be in use!
public class Account extends Person{
	private String username;
	private String password;
	public Account(String _username, String _password,Person p)
	{
		super(p);
		username=_username;
		password = _password;
	}
	public Account(String _username, String _password,String _firstname, String _lastname, int _id, String _mail, int _phone, String _credit, int _age,
			String _gender, String _address)
	{
		super(_firstname, _lastname, _id, _mail, _phone, _credit, _age, _gender, _address, _address, _address);
		username=_username;
		password = _password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String toString()
	{
		return username;
	}
	
}
