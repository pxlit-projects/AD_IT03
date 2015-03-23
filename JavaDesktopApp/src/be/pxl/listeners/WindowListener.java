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
import be.pxl.windows.ViewUserWindow;

public class WindowListener implements ActionListener{

	private JFrame previousFrame;
	private User user;
	
	public WindowListener(JFrame frame) {
		this.previousFrame = frame;
	}
	
	public WindowListener(User user) {
		this.user = user;
	}
	
	public WindowListener() {
		
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
			JFrame frame = new EditUserWindow();
			frame = windowNotFullScreen(frame);
		}
		
		if (text.equalsIgnoreCase("nieuwe gebruiker toevoegen")) {
			JFrame frame = new AddUserWindow();
			frame = windowNotFullScreen(frame);
		}
		
		if (text.equalsIgnoreCase("bekijk gegevens")) {
			JFrame frame = new ViewUserWindow(user);
			frame = windowNotFullScreen(frame);
		}
	}
	
	private JFrame windowFullScreen(JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 350);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		return frame;
	}
	
	public JFrame windowNotFullScreen(JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 350);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		return frame;
	}

}
