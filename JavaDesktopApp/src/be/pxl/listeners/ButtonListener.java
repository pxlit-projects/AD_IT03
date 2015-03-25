package be.pxl.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import be.pxl.database.DeleteUser;
import be.pxl.objects.User;
import be.pxl.windows.UsersPanel;

public class ButtonListener implements ActionListener {
	
	private JFrame frame;
	private User user;
	private UsersPanel usersPanel;

	public ButtonListener(JFrame frame) {
		this.frame = frame;
	}
	
	public ButtonListener(User user, UsersPanel usersPanel) {
		this.user = user;
		this.usersPanel = usersPanel;
	}
	
	public ButtonListener() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String text = button.getText();
		
		if (text.equalsIgnoreCase("annuleren")) {
			frame.dispose();
		}
		
		if (text.equalsIgnoreCase("verwijderen")) {
			new DeleteUser(user, usersPanel);
		}
		
	}

}
