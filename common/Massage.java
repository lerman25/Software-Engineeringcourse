package common;

import java.io.Serializable;

public class Massage implements Serializable {
	/**
	 * 
	 */
	//private Commands command;
	private Object object;
	private String username;
	private String password;
	private Commands command;
public Massage(Object _object,Commands _command)
{
	setObject(_object);
	command=_command;
}

public Massage(Commands _command,String _username, String _password)
{
	command=_command;
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
public Commands getCommand() {
	return command;
}
public void setCommand(Commands command) {
	this.command = command;
}

public Object getObject() {
	return object;
}

public void setObject(Object object) {
	this.object = object;
}

	
	}

