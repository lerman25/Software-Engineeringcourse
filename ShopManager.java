
public class ShopManager extends Person {
	private int BranchID;

	public ShopManager(Person p,int _branch) {
		super(p);
		BranchID=_branch;
		// TODO Auto-generated constructor stub
	}
	public ShopManager(int _branch ,String _firstname, String _lastname, int _id, String _mail, int _phone, String _credit, int _age,
			String _gender, String _address, String _username, String _password) {
		super(_firstname, _lastname, _id, _mail, _phone, _credit, _age, _gender, _address, _username, _password);
		// TODO Auto-generated constructor stub
		BranchID=_branch;

	}

	public int getBranchID() {
		return BranchID;
	}

	public void setBranchID(int branchID) {
		BranchID = branchID;
	}
	public String toString()
	{
		return this.getUsername();
	}
}
