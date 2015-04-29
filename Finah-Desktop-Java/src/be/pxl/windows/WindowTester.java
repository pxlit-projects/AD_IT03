package be.pxl.windows;

import javax.swing.JFrame;

public class WindowTester {

	public static void main(String[] args) {
		JFrame frame = new AddQuestionWindow(5);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1100, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
