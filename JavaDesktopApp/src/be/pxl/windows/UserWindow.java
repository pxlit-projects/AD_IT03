package be.pxl.windows;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserWindow extends JPanel {

	public UserWindow() {
		super(new GridLayout(1, 1));

	
		JTabbedPane tabbedPane = new JTabbedPane();

		
		JComponent panelOverzicht = new JComponent() {
			
		};
		
		JComponent panelVragenlijst = new JComponent() {
			
		};
	
		
		
		tabbedPane.addTab("Overzicht", panelOverzicht);
		tabbedPane.addTab("Vragenlijst", panelVragenlijst);
		
		
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		add(tabbedPane);
	}

	public static void main(String[] args) {
		JFrame Frame = new JFrame("AppDevIT_03 - Gebruiker");
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Frame.add(new UserWindow(), BorderLayout.CENTER);
		
		Frame.setSize(500, 500);
		Frame.setVisible(true);
	}

}