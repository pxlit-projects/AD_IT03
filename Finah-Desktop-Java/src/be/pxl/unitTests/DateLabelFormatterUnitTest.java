package be.pxl.unitTests;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import be.pxl.settings.DateLabelFormatter;

public class DateLabelFormatterUnitTest {

	@Test
	public void test() {
		DateLabelFormatter date = new DateLabelFormatter();
		String testDate = "12-02-2015";
		String result = "";
		try {
		 result = String.valueOf(date.stringToValue(testDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("Thu Feb 12 00:00:00 CET 2015", result);
		
	}

}
