package be.pxl.settings;

import java.io.IOException;
import java.util.Properties;

public class ConfigFile {

	Properties configFile = new Properties();
	
	public ConfigFile(){
		
	}
	
	public Properties getConfigFile(){
		
		try {
			configFile.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return configFile;
	}
	
}
