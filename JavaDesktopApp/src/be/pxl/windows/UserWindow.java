package be.pxl.windows;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class UserWindow extends JFrame {
	
	private	JTabbedPane tabbedPane;
	
	public UserWindow() {

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Overzicht", new SummaryPanel());
		tabbedPane.addTab("Vragenlijsten", new QuestionnairePanel());
		
		this.add(tabbedPane);
	}
}
