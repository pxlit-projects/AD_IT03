package be.pxl.windows;

import java.awt.Color;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import be.pxl.json.UserDb;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SettingClass;

public class HomeWindow extends JFrame {

	private static final long serialVersionUID = -5686952163744219865L;
	private JTabbedPane tabbedPane;
	private Properties configFile = new ConfigFile().getConfigFile();
	
	public HomeWindow(String login) {
		int typeId = new UserDb().getTypeIdByLogin(login);
		new SettingClass().setUsertype(typeId);
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab(configFile.getProperty("tabSummary"), new SummaryPanel());
		if (typeId != 5) {
			tabbedPane.addTab(configFile.getProperty("tabQuestionLists"), new ThemePanel());
		}
		if (typeId == 1) {
			tabbedPane.addTab(configFile.getProperty("tabUsers"), new UsersPanel());
		}
		this.getContentPane().setBackground(Color.WHITE);
		this.add(tabbedPane);
	}
}
