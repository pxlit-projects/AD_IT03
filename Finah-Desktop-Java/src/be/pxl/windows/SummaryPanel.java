package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import be.pxl.settings.SettingClass;

public class SummaryPanel extends JPanel {

	private static final long serialVersionUID = 7474806161818429171L;

	private Vector columnNames ;
	private Vector columns;
	
	
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
		columnNames = new Vector();
		columns = new Vector();
		
		columnNames.addElement("Vragenlijst");
		columnNames.addElement("Datum");
		columnNames.addElement("Patient");
		columnNames.addElement("Mantelzorger");
		columnNames.addElement("Rapport");
		
		JTable summaryTable = new JTable(columns, columnNames);
		JScrollPane scrollPane = new JScrollPane( summaryTable,
		JScrollPane. VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS ); 
		scrollPane.setVisible(true);
		this.add( scrollPane , BorderLayout.CENTER);
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
