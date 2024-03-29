package be.pxl.settings;

import java.awt.Font;
import java.util.Properties;

public class SettingClass {
	Properties configFile = new ConfigFile().getConfigFile();
	private Font titleFont = new Font("Arial", Font.PLAIN, 32);
	private String siteUrl = configFile.getProperty("siteUrl");
	private static int user;
	private static int usertype;

	public Font getTitleFont() {
		return titleFont;
	}
	
	public String getSiteUrl() {
		return siteUrl;
	}

	public SettingClass() {
		// TODO Auto-generated constructor stub
	}
	
	public int getUser() {
		return user;
	}
	
	@SuppressWarnings("static-access")
	public void setUser(int user) {
		this.user = user;
	}
	
	public int getUsertype() {
		return usertype;
	}
	
	@SuppressWarnings("static-access")
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

}
