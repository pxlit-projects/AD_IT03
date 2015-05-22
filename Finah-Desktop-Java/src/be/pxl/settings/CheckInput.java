package be.pxl.settings;

import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

public class CheckInput {
	Properties configFile = new ConfigFile().getConfigFile();
	private String ePattern = configFile.getProperty("pattern");
	java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);

	public CheckInput() {

	}

	public boolean checkInputAddUser(JTextField firstNameTextField,
			JTextField lastNameTextField, JTextField loginTextField,
			JTextField passwordTextField, JTextField emailTextField,
			JTextField streetTextField, JTextField townTextField,
			JDatePickerImpl datePicker, JTextField zipCodeTextField) {
	
		
		java.util.regex.Matcher m = p.matcher(emailTextField.getText());

		String checkfirstName = configFile.getProperty("checkFirstname");
		String checkLastName = configFile.getProperty("checkLastName");
		String checkLogin = configFile.getProperty("checkLogin");
		String checkPassword = configFile.getProperty("checkPassword");
		String checkEmail = configFile.getProperty("checkEmail");
		String wrongMail = configFile.getProperty("wrongMail");
		String checkStreet = configFile.getProperty("checkStreet");
		String checkTown = configFile.getProperty("checkTown");
		String checkZipCode = configFile.getProperty("checkZipCode");
		String wrongZipCode = configFile.getProperty("wrongZipCode");
		String checkDatePicker = configFile.getProperty("checkDatePicker");
		
		
		
		if (firstNameTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,checkfirstName);
		} else if (lastNameTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,checkLastName);
		} else if (loginTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,checkLogin);
		} else if (passwordTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,checkPassword);
		} else if (emailTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,checkEmail);
		} else if (!m.matches()) {
			JOptionPane.showMessageDialog(null,wrongMail);
		} else if (streetTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,checkStreet);
		} else if (townTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,checkTown);
		} else if (zipCodeTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,checkZipCode);
		} else if (!isInteger(zipCodeTextField.getText(), 10)) {
			JOptionPane.showMessageDialog(null,wrongZipCode);
		} else {
			try {
				datePicker.getModel().getValue().equals(null);
			} catch (NullPointerException npe) {
				JOptionPane.showMessageDialog(null,checkDatePicker);
				return false;
			}
			return true;
		}
		return false;

	}

	public boolean checkInputSendQuestionnaire(JTextField clientEmailTextField,
			JTextField caregiverEmailTextField, JTextField clientAgeTextField,
			JTextField caregiverAgeTextField, JTextField relationTextField,
			JTextField categoryTextField) {
		
		java.util.regex.Matcher clientEmail = p.matcher(clientEmailTextField.getText());
		java.util.regex.Matcher caregiverEmail = p.matcher(caregiverEmailTextField.getText());
		
		if (clientEmailTextField.getText().trim().isEmpty()) {
			
		} else if (!clientEmail.matches()) {
			
		} else if(caregiverEmailTextField.getText().trim().isEmpty()) {
			
		} else if(!caregiverEmail.matches()) {
			
		} else if(clientAgeTextField.getText().trim().isEmpty()) {
			
		} else if(!isInteger(clientAgeTextField.getText().trim(), 10)) {
			
		} else if(caregiverAgeTextField.getText().trim().isEmpty()) {
			
		} else if(!isInteger(caregiverAgeTextField.getText().trim(), 10)) {
			
		} else if(relationTextField.getText().trim().isEmpty()) {
			
		} else if(categoryTextField.getText().trim().isEmpty()) {
			
		} else {
			return true;
		}
		
		
		
		return false;
	}

	public boolean isInteger(String s, int radix) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}

}
