package be.pxl.windows;

import java.awt.Color;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import be.pxl.json.UserDb;
import be.pxl.listeners.FrameListener;
import be.pxl.settings.ConfigFile;

public class HomeWindow extends JFrame {

	private static final long serialVersionUID = -5686952163744219865L;
	private JTabbedPane tabbedPane;
	private Properties configFile = new ConfigFile().getConfigFile();
	
	public HomeWindow(String login) {
		this.addWindowListener(new FrameListener());
		int typeId = new UserDb().getTypeIdByLogin(login);
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab(configFile.getProperty("tabSummary"), new SummaryPanel());
		tabbedPane.addTab(configFile.getProperty("tabQuestionLists"), new ThemePanel());
		if (typeId == 1) {
			tabbedPane.addTab(configFile.getProperty("tabUsers"), new UsersPanel());
		}
		this.getContentPane().setBackground(Color.WHITE);
		this.add(tabbedPane);
	}
	//
	// public void refreshUserPanel() {
	// tabbedPane.remove(usersPanel);
	// usersPanel = new UsersPanel();
	// tabbedPane.add(usersPanel);
	// this.repaint();
	// }
}
