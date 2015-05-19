package be.pxl.unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import be.pxl.settings.SettingClass;

public class SettingClassUnitTest {

	
	
	@Test
	public void test() {
		String getSite = new SettingClass().getSiteUrl();
		
		assertEquals("http://finah-webapi-appdevit03.azurewebsites.net/", getSite);
	}
	
	
	
	

}
