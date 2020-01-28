package common;

import java.io.Serializable;

public  class Person implements Serializable{
	private String firstName;
	private String lastName;
	private  int id;
	private String mail;
	private int phone_number;
	private String credit_card;
	private int age;
	private String gender;
	private String address;
	private String username;
	private String password;
	static int counter=0;
	public Person(String _firstname,String _lastname, int _id,String _mail,int _phone,String _credit,int _age,String _gender,String _address,String _username,String _password)
	{
		firstName=_firstname;
		lastName=_lastname;
		id=++counter;
		mail=_mail;
		phone_number=_phone;
		credit_card=_credit;
		age=_age;
		gender=_gender;
		address=_address;
		username=_username;
		password=_password;
	}
	public Person(Person p)
	{
		firstName=p.getFirstName();
		lastName=p.getLastName();
		id=p.getId();
		mail=p.getMail();
		phone_number=p.getPhone_number();
		credit_card=p.getCredit_card();
		age=p.getAge();
		gender=p.getGender();
		address=p.getAddress();
		username=p.getUsername();
		password=p.getPassword();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	public String getCredit_card() {
		return credit_card;
	}
	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String toString() {
		return String.valueOf(this.id);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String _lastName) {
		lastName = _lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public Person getPerson()
	{
		return this;
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

}
