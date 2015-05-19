package be.pxl.settings;

import java.awt.Font;
import java.util.Properties;

public class SettingClass {
	Properties configFile = new ConfigFile().getConfigFile();
	private Font titleFont = new Font("Arial", Font.PLAIN, 32);
	private String siteUrl = configFile.getProperty("siteUrl");

	public Font getTitleFont() {
		return titleFont;
	}
	
	public String getSiteUrl() {
		return siteUrl;
	}

	public SettingClass() {
		// TODO Auto-generated constructor stub
	}

}
