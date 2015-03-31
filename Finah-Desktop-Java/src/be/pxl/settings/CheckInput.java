package be.pxl.settings;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

public class CheckInput {

	public CheckInput() {

	}

	public boolean checkInput(JTextField firstNameTextField,
			JTextField lastNameTextField, JTextField loginTextField,
			JTextField passwordTextField, JTextField emailTextField,
			JTextField streetTextField, JTextField townTextField,
			JDatePickerImpl datePicker, JTextField zipCodeTextField) {

		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(emailTextField.getText());

		if (firstNameTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Gelieve een voornaam in te vullen.");
		} else if (lastNameTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Gelieve een achternaam in te vullen.");
		} else if (loginTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Gelieve een login in te vullen.");
		} else if (passwordTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Gelieve een wachtwoord in te vullen.");
		} else if (emailTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Gelieve een email in te vullen.");
		} else if (!m.matches()) {
			JOptionPane.showMessageDialog(null,
					"Dit is geen correct email adres");
		} else if (streetTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Gelieve een straat in te vullen.");
		} else if (townTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Gelieve een stad in te vullen.");
		} else if (zipCodeTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Gelieve een postcode in te vullen.");
		} else if (!isInteger(zipCodeTextField.getText(), 10)) {
			JOptionPane.showMessageDialog(null,
					"Een postcode kan enkel cijfers bevatten.");
		} else {
			try {
				datePicker.getModel().getValue().equals(null);
			} catch (NullPointerException npe) {
				JOptionPane.showMessageDialog(null,
						"Gelieve een geboortedatum in te vullen.");
				return false;
			}
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
