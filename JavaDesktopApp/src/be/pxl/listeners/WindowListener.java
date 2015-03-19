package be.pxl.listeners;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import be.pxl.windows.AddUserWindow;
import be.pxl.windows.EditUserWindow;
import be.pxl.windows.HomeWindow;

public class WindowListener implements ActionListener{

	private JFrame previousFrame;
	
	public WindowListener(JFrame frame) {
		this.previousFrame = frame;
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
			frame = windowSetting(frame);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		if (text.equalsIgnoreCase("bewerken")) {
			JFrame frame = new EditUserWindow();
			frame = windowSetting(frame);
		}
		
		if (text.equalsIgnoreCase("nieuwe gebruiker toevoegen")) {
			JFrame frame = new AddUserWindow();
			frame = windowSetting(frame);
			frame.setSize(700, 350);
			frame.setLocationRelativeTo(null);
		}
	}
	
	private JFrame windowSetting(JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 350);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		return frame;
	}

}
