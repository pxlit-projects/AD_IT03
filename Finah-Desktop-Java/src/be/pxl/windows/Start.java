package be.pxl.windows;

import javax.swing.JFrame;

public class Start {

	public static void main(String[] args) {
		LoginWindow loginWindow = new LoginWindow();
		loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginWindow.setSize(600, 250);
		loginWindow.setLocationRelativeTo(null);
		loginWindow.setVisible(true);
	}

}
