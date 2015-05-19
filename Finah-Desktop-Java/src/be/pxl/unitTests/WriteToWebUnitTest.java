package be.pxl.unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import be.pxl.json.WriteToWeb;
import be.pxl.objects.Theme;

public class WriteToWebUnitTest {

	WriteToWeb write = new WriteToWeb();
	Theme testUpdate = new Theme(6, "TestHuishouden", "TestHuishouden");
	
	
	@Test
	public void test() {
		
	
	int code =	write.Update(testUpdate, "http://finah-webapi-appdevit03.azurewebsites.net/api/themes/", 6);
		
	assertEquals(404, code);
	}

}
