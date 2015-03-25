package be.pxl.settings;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

public class CheckInput {

	public CheckInput(){
		
	}
	public boolean checkInput(JTextField firstNameTextField,
			JTextField lastNameTextField, JTextField loginTextField,
			JTextField passwordTextField, JTextField emailTextField,
			JTextField streetTextField, JTextField townTextField,
			JDatePickerImpl datePicker, JTextField zipCodeTextField) {
		if (firstNameTextField.getText().trim().isEmpty()
				&& lastNameTextField.getText().trim().isEmpty()
				&& loginTextField.getText().trim().isEmpty()
				&& passwordTextField.getText().trim().isEmpty()
				&& emailTextField.getText().trim().isEmpty()
				&& streetTextField.getText().trim().isEmpty()
				&& townTextField.getText().trim().isEmpty()
				//&& datePicker.getModel().getValue() == null
				&& zipCodeTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Er zijn geen waarden ingevoerd");
			return false;
		} else if (firstNameTextField.getText().trim().isEmpty()
				|| lastNameTextField.getText().trim().isEmpty()
				|| loginTextField.getText().trim().isEmpty()
				|| passwordTextField.getText().trim().isEmpty()
				|| emailTextField.getText().trim().isEmpty()
				|| streetTextField.getText().trim().isEmpty()
				|| townTextField.getText().trim().isEmpty()
				//|| datePicker.getModel().getValue() == null
				|| zipCodeTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Er is minstens 1 veld niet ingevuld");
			return false;
		} else {
			return true;

		}
	}
	
}
