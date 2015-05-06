package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import be.pxl.settings.SettingClass;

public class SummaryPanel extends JPanel {

	private static final long serialVersionUID = 7474806161818429171L;

	public SummaryPanel() {
		this.setLayout(new BorderLayout());
		topPanelLayout();
		centerPanelLayout();
		bottemPanelLayout();
	}
	
	private void topPanelLayout() {
		JPanel topPanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel("Overzicht");
		title.setFont(new SettingClass().getTitleFont());
		topPanel.add(title);
		this.add(topPanel, BorderLayout.NORTH);
	}
	
	private void centerPanelLayout() {
		JPanel centerPanel = new JPanel(new FlowLayout());
	}
	
	private void bottemPanelLayout() {
		JPanel bottemPanel = new JPanel(new FlowLayout());
		JButton sendQuestionnaire = new JButton("Vragenlijst verzenden");
		JButton acceptButton = new JButton("Accepteren");
		JButton refuseButton = new JButton("Weigeren");
		
		
		bottemPanel.add(sendQuestionnaire);
		bottemPanel.add(acceptButton);
		bottemPanel.add(refuseButton);
		
		this.add(bottemPanel, BorderLayout.SOUTH);
	}

}
