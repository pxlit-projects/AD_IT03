package be.pxl.listeners;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import be.pxl.windows.UserWindow;

public class WindowListener implements ActionListener{

	private JFrame previousFrame;
	
	public WindowListener(JFrame frame) {
		this.previousFrame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String text = button.getText();
		
		if (text.equalsIgnoreCase("login")) {
			previousFrame.dispose();
			JFrame frame = new UserWindow();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500,500);
			//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
			frame.setVisible(true);
		}
	}

}
