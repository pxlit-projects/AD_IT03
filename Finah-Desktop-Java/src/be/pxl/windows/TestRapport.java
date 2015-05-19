package be.pxl.windows;

import javax.swing.JFrame;

public class TestRapport {

	public static void main(String[] args) {
		RapportenWindow rapport = new RapportenWindow();
		rapport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rapport.setSize(500, 500);
		rapport.setLocationRelativeTo(null);
		rapport.setVisible(true);
	}
	
}
