package common;

public class Massage {
	private Commands command;
	private Object object;
	public Massage(Commands _command,Object _object)
	{
		command=_command;
		object=_object;
	}
	public Massage(Massage M)
	{
		command=M.getCommand();
		object=M.getObject();
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
