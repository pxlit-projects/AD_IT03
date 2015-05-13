package be.pxl.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;

import be.pxl.database.DeleteUsers;
import be.pxl.objects.User;
import be.pxl.settings.ConfigFile;
import be.pxl.windows.UsersPanel;

public class ButtonListener implements ActionListener {
	
	private JFrame frame;
	private List<User> users = new ArrayList<User>();
	private UsersPanel usersPanel;
	private Properties configFile = new ConfigFile().getConfigFile();
	public ButtonListener(JFrame frame) {
		this.frame = frame;
	}
	
	public ButtonListener(List<User> users, UsersPanel usersPanel) {
		this.users = users;
		this.usersPanel = usersPanel;
	}
	
	public ButtonListener() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String text = button.getText();
		
		if (text.equalsIgnoreCase(configFile.getProperty("btnCancel"))) {
			frame.dispose();
		}
		
		if (text.equalsIgnoreCase(configFile.getProperty("btnDelete"))) {
			new DeleteUsers(users, usersPanel);
		}
		
	}

}
