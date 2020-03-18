package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.ChainManager;
import common.Client;
import common.Commands;
import common.Employee;
import common.Massage;
import common.Person;
import common.ShopManager;

public class SignUp implements Initializable {

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private TextField password;

	@FXML
	private TextField phoneNumber;

	@FXML
	private TextField creditField;

	@FXML
	private AnchorPane page;

	@FXML
	private TextField userName;

	@FXML
	private Button signup;

	@FXML
	private TextField age;

	@FXML
	private TextField eMail;

	@FXML
	private ChoiceBox<String> genderBox;

	@FXML
	private ChoiceBox<Permissions> typeSignup;

	@FXML
	private Label perLabel;
	@FXML
	private Text mainText;
	@FXML
	private TextField address;
	@FXML
	private TextField brachID;

	@FXML
	private Text branchLabel;
	private Stage thisStage;
	private Person user;
	private boolean updateMode = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		genderBox.getItems().add("Female");
		genderBox.getItems().add("Male");
		genderBox.getItems().add("Private");
		Permissions user = Main.getPermission();
		typeSignup.getItems().addAll(Permissions.values());
		branchVis();
		System.out.println(user.toString());
		if (user.equals(Permissions.GUEST) || user.equals(Permissions.CLIENT)) {
			typeSignup.getSelectionModel().select(Permissions.CLIENT);

			typeSignup.setValue(Permissions.CLIENT);
			typeSignup.setDisable(true);
			perLabel.setVisible(false);
		} else {

			for (int i = Permissions.values().length - 1; i >= user.ordinal(); i--) {
				typeSignup.getItems().remove(Permissions.values()[i]);
			}
			typeSignup.getItems().remove(Permissions.GUEST);
			if (user.equals(Permissions.ADMIN))
				typeSignup.getItems().add(Permissions.ADMIN);
		}
	}

	public void signup(ActionEvent event) throws IOException {
		Object newuser = textValidation();
		boolean removeflag = true;
		if (newuser != null) {
			if (updateMode) {
				Main.send_toServer(new Massage(Person.returnUser(user), Commands.DELETE));
				Massage msg = Main.get_from_server();
				if (msg.getCommand() == Commands.DBERROR) {
					AlertBox.display("Remove", "ERROR!");
					removeflag = false;
				}
				else
				{
					AlertBox.display("Remove", "SUCCESS!");

				}
			}
			Main.send_toServer(new Massage(newuser, Commands.ADD));
			Massage msg = server.Main.get_from_server();
			if (msg.getCommand() != Commands.DBERROR && removeflag) {
				AlertBox.display("Sign UP", "SUCCESS!");
				thisStage.close();
			}

		}

	}

	@FXML
	private void perChange(ActionEvent event) {
		// existing code...
		branchVisC();

	}

	public void branchVis() {
		int perOrd = Main.getPermission().ordinal();
		boolean isEmp = (perOrd == Permissions.EMPLOYEE.ordinal());
		if (isEmp) {
			brachID.setVisible(true);
			branchLabel.setVisible(true);
		}
		boolean isSM = (perOrd == Permissions.SHOPMANAGER.ordinal());
		if (isSM) {
			brachID.setVisible(true);
			branchLabel.setVisible(true);
		}

	}

	public void branchVisC() {
		int perOrd = typeSignup.getSelectionModel().getSelectedItem().ordinal();
		boolean isEmp = (perOrd == Permissions.EMPLOYEE.ordinal());
		if (isEmp) {
			brachID.setVisible(true);
			branchLabel.setVisible(true);
		}
		boolean isSM = (perOrd == Permissions.SHOPMANAGER.ordinal());
		if (isSM) {
			brachID.setVisible(true);
			branchLabel.setVisible(true);
		}
		if ((!isSM) && (!isEmp)) {
			brachID.setVisible(false);
			branchLabel.setVisible(false);
		}

	}

	public void renderPerson(Object _user) {
		user = (Person) _user;
		int perOrd = user.getPermission().ordinal();
		boolean isEmp = (perOrd == Permissions.EMPLOYEE.ordinal());
		if (isEmp) {
			Employee e = new Employee(0, user.getFirstName(), user.getLastName(), user); // this need to be changes...to
																							// tired right now
			brachID.setText(String.valueOf(e.getBranchID()));
		}
		boolean isSM = (perOrd == Permissions.SHOPMANAGER.ordinal());// this need to be changes...to tired right now
		if (isSM) {
			ShopManager e = new ShopManager(user, 0);
			brachID.setText(String.valueOf(e.getBranchID()));
		}
		firstName.setText(user.getFirstName());
		lastName.setText(user.getLastName());
		phoneNumber.setText(String.valueOf(user.getPhone_number()));
		creditField.setText(user.getCredit_card());
		userName.setText(user.getUsername());
		age.setText(String.valueOf(user.getAge()));
		eMail.setText(user.getMail());
		genderBox.setValue(user.getGender());
		typeSignup.getSelectionModel().select(user.getPermission());
		typeSignup.setValue(user.getPermission());
		address.setText(user.getAddress());
		password.setText(user.getPassword());
		signup.setText("Update");
		mainText.setText("Update");
		updateMode = true;
	}

	public Object textValidation() {
		String _firstname = firstName.getText();
		String _lastname = lastName.getText();
		String _mail = eMail.getText();
		String s_phone_number = phoneNumber.getText();
		String _credit = creditField.getText();
		String s_age = age.getText();
		int i_gender = genderBox.getSelectionModel().getSelectedIndex();
		String _address = address.getText();
		String _username = userName.getText();
		String _password = password.getText();
		Permissions _permission = typeSignup.getSelectionModel().getSelectedItem();
		String s_branchID = "-1";

		boolean firstEmpty = _firstname.isEmpty() || _firstname.isBlank();
		boolean lastEmpty = _lastname.isEmpty() || _lastname.isBlank();
		boolean passEmpty = _password.isEmpty() || _password.isBlank();
		boolean phoneEmpty = s_phone_number.isEmpty() || s_phone_number.isBlank();
		boolean creditEmpty = _credit.isEmpty() || _credit.isBlank();
		boolean userEmpty = _username.isEmpty() || _username.isBlank();
		boolean ageEmpty = s_age.isEmpty() || s_age.isBlank();
		boolean mailEmpty = _mail.isEmpty() || _mail.isBlank();
		boolean genderEmpty = i_gender == -1;
		boolean addressEmpty = _address.isEmpty() || _address.isBlank();
		boolean branchEmpty = s_branchID.isEmpty() || s_branchID.isBlank();
		boolean NotFull = firstEmpty || lastEmpty || passEmpty || phoneEmpty || creditEmpty || userEmpty || ageEmpty
				|| mailEmpty || genderEmpty || addressEmpty;
		int perOrd = _permission.ordinal();
		boolean isEmp = perOrd == Permissions.EMPLOYEE.ordinal();
		boolean isSM = perOrd == Permissions.SHOPMANAGER.ordinal();
		if (isEmp || isSM) {
			s_branchID = brachID.getText();
			branchEmpty = s_branchID.isEmpty() || s_branchID.isBlank();
			NotFull = NotFull || branchEmpty;
		}
		if (NotFull) {
			String newLine = System.getProperty("line.separator");
			newLine += " ";
			String start = "There are empty fields :";
			if (firstEmpty)
				start += newLine + "First Name Empty";
			if (lastEmpty)
				start += newLine + "Last Name Empty";
			if (passEmpty)
				start += newLine + "Password Empty";
			if (phoneEmpty)
				start += newLine + "Phone Number Empty";
			if (creditEmpty)
				start += newLine + "Credit Card Empty";
			if (userEmpty)
				start += newLine + "User Name Empty";
			if (ageEmpty)
				start += newLine + "Age Empty";
			if (mailEmpty)
				start += newLine + "Email Empty";
			if (firstEmpty)
				start += newLine + "First Name Empty";
			if (genderEmpty)
				start += newLine + "Gender Empty";
			if (addressEmpty)
				start += newLine + "Address Empty";
			if (branchEmpty)
				start += newLine + "Branch ID Empty";

			start += newLine + newLine + newLine + "Please fill the missing data";
			AlertBox.display("SignUp", start);
			return null;
		} else {
			int id = -1;
			if (!updateMode) {
				Main.send_toServer(new Massage("Person", Commands.GETLASTID));
				Massage msg = Main.get_from_server();
				id = (int) msg.getObject() + 1;
			} else {
				id = user.getId();
			}
			int _age = Integer.parseInt(s_age);
			int _phone = Integer.parseInt(s_phone_number);
			String _gender = "Male";
			// enum for gender!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			switch (i_gender) {
			case 0: {
				_gender = "Female";
				break;
			}
			case 1: {
				_gender = "Male";
				break;
			}
			case 2: {
				_gender = "Private";
				break;

			}
			}
			// check parsing.
			Person newPerson = new Person(_firstname, _lastname, _mail, _phone, _credit, _age, _gender, _address,
					_username, _password);
			newPerson.setPermission(_permission);
			newPerson.setId(id);
			if (_permission == Permissions.SHOPMANAGER || _permission == Permissions.EMPLOYEE) {
				int _branchID = Integer.parseInt(s_branchID);
				return Person.returnUser(newPerson, _branchID);
			} else
				return Person.returnUser(newPerson);
		}
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}

}
