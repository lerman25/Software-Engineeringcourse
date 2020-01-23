package common;
//needs to be changed if needed to be in use!
public class Account {
	private String username;
	private String password;
	public Account(String _username, String _password)
	{
		username=_username;
		password = _password;
	}
	public Account(Object msg)
	{
		
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
