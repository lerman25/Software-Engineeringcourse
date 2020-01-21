
public class Client extends Person {
private String username;
private String password;
public Client(String _firstname, String _lastname, int _id, String _mail, int _phone, String _credit, int _age,
		String _gender, String _address,String _username, String _password) {
	super(_firstname, _lastname, _id, _mail, _phone, _credit, _age, _gender, _address);
	// TODO Auto-generated constructor stub
	username=_username;
	password=_password;
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
public Person getPerson()
{
	return super.getPerson();
}

}
