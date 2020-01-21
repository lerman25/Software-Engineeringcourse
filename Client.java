
public class Client extends Account {
public Client(String _firstname, String _lastname, int _id, String _mail, int _phone, String _credit, int _age,
		String _gender, String _address,String _username, String _password) {
	super(_username,  _password,_firstname, _lastname, _id, _mail, _phone, _credit, _age, _gender, _address);
	// TODO Auto-generated constructor stub
}
public String getUsername() {
	return super.getUsername();
}
public void setUsername(String username) {
	super.setUsername(username);
}
public String getPassword() {
	return super.getPassword();
}
public void setPassword(String password) {
	super.setUsername(password);
}
public Person getPerson()
{
	return super.getPerson();
}
public String toString()
{
	return String.valueOf(this.getId());

}


}
