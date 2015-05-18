package be.pxl.settings;

import java.awt.Font;

public class SettingClass {
	
	private Font titleFont = new Font("Arial", Font.PLAIN, 32);
	private String siteUrl = "http://finah-webapi-appdevit03.azurewebsites.net/";

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
