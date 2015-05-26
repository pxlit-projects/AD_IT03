package be.pxl.unitTests;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.junit.Test;

import be.pxl.settings.CheckInput;
import be.pxl.settings.DateLabelFormatter;

public class CheckInputUnitTest {

	@Test
	public void test() {

		UtilDateModel dateModel = new UtilDateModel();
		dateModel.setYear(1990);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		JTextField firstNameTextField = new JTextField();
		JTextField lastNameTextField = new JTextField();
		JTextField loginTextField = new JTextField();
		JTextField passwordTextField = new JTextField();
		JTextField emailTextField = new JTextField();
		JTextField streetTextField = new JTextField();
		JTextField townTextField = new JTextField();

		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);

		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());
		JTextField zipCodeTextField = new JTextField();

		firstNameTextField.setText("Jordy");
		lastNameTextField.setText("Collas");
		loginTextField.setText("Jordy123");
		passwordTextField.setText("*****");
		emailTextField.setText("jordy.collas@gmail.Com");
		streetTextField.setText("overheidelaan");
		townTextField.setText("Genk");
		datePicker.getModel().setDay(17);
		datePicker.getModel().setMonth(02);
		datePicker.getModel().setYear(2015);
		datePicker.getModel().setSelected(true);;

		zipCodeTextField.setText("3600");

		CheckInput check = new CheckInput();

		assertEquals(true, check.checkInputAddUser(firstNameTextField,
				lastNameTextField, loginTextField, passwordTextField,
				emailTextField, streetTextField, townTextField, datePicker,
				zipCodeTextField));

	}

}
