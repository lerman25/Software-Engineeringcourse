
public class Employee extends Person {

	private int branchID;
	private int rank = 0;

	public Employee(int _branchID, String _username, String _password, Person p) {
		super(p);
		// TODO Auto-generated constructor stub
		branchID = _branchID;
	}

	public Employee(int _branchID, String _firstname, String _lastname, int _id, String _mail, int _phone,
			String _credit, int _age, String _gender, String _address, String _username, String _password) {
		super(_firstname, _lastname, _id, _mail, _phone, _credit, _age, _gender, _address, _username, _password);
		// TODO Auto-generated constructor stub
		branchID = _branchID;

	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Person getPerson() {
		return super.getPerson();
	}

	public String toString() {
		return this.getUsername();

	}

}
