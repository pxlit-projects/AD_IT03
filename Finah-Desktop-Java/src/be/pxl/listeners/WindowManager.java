package be.pxl.listeners;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import be.pxl.objects.User;
import be.pxl.windows.AddUserWindow;
import be.pxl.windows.EditUserWindow;
import be.pxl.windows.HomeWindow;
import be.pxl.windows.UsersPanel;
import be.pxl.windows.ViewUserWindow;

public class WindowManager implements ActionListener{

	private JFrame previousFrame;
	private User user;
	private String frameName = "";
	private UsersPanel usersPanel;
	
	public WindowManager(JFrame frame) {
		this.previousFrame = frame;
	}
	
	public WindowManager(User user, UsersPanel usersPanel) {
		this.usersPanel = usersPanel;
		this.user = user;
	}
	
	public WindowManager(User user, String frameName) {
		this.user = user;
		this.frameName = frameName;
	}
	
	public WindowManager(UsersPanel usersPanel) {
		this.usersPanel = usersPanel;
	}
	
	public WindowManager() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String text = button.getText();
		
		if (text.equalsIgnoreCase("login")) {
			previousFrame.dispose();
			JFrame frame = new HomeWindow();
			frame = windowFullScreen(frame);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		if (text.equalsIgnoreCase("bewerken")) {
			JFrame frame = new EditUserWindow(user, usersPanel);
			frame = windowNotFullScreen(frame);
		}
		
		if (text.equalsIgnoreCase("nieuwe gebruiker toevoegen")) {
			JFrame frame = new AddUserWindow(usersPanel);
			frame = windowNotFullScreen(frame);
		}
		
		if (text.equalsIgnoreCase("bekijk gegevens") || frameName.equalsIgnoreCase("bekijk gegevens")) {
			JFrame frame = new ViewUserWindow(user, usersPanel);
			frame = windowNotFullScreen(frame);
		}
	}
	
	private JFrame windowFullScreen(JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		return frame;
	}
	
	public JFrame windowNotFullScreen(JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		return frame;
	}

}
