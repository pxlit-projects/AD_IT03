package be.pxl.unitTests;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

import be.pxl.settings.ConfigFile;

public class ConfigFileUnitTest {

	@Test
	public void test() {
		Properties configfile = new ConfigFile().getConfigFile();
		Boolean test;
		if (configfile  != null){
			test = true;
		}else{
			test = false;
		}
		
		
		assertEquals( true , test);
	}

}
