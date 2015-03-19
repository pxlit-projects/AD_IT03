package be.pxl.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ButtonListener implements ActionListener {
	
	private JFrame frame;

	public ButtonListener(JFrame frame) {
		this.frame = frame;
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
		
	}

}
