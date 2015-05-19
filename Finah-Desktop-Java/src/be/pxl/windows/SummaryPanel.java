package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import be.pxl.json.HashesDB;
import be.pxl.listeners.WindowManager;
import be.pxl.objects.Hashes;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SettingClass;

public class SummaryPanel extends JPanel {

	private static final long serialVersionUID = 7474806161818429171L;

	private Vector columnNames ;
	private Vector columns;
	private Properties configFile = new ConfigFile().getConfigFile();
	private List<Hashes> hashesList = new HashesDB().readHashes();
	private JLabel id;
	private JLabel date;
	private JButton rapport;
	
		public SummaryPanel() {
		this.setLayout(new BorderLayout());
		topPanelLayout();
		centerPanelLayout();
		bottemPanelLayout();
	}
	
	private void topPanelLayout() {
		JPanel topPanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel(configFile.getProperty("labelSummary"));
		title.setFont(new SettingClass().getTitleFont());
		topPanel.add(title);
		this.add(topPanel, BorderLayout.NORTH);
	}
	
	private void centerPanelLayout() {
		JPanel centerPanel = new JPanel(new FlowLayout());
		columnNames = new Vector();
		columns = new Vector();
		
		columnNames.addElement(configFile.getProperty("elementVragenlijst"));
		columnNames.addElement(configFile.getProperty("elementDatum"));
		columnNames.addElement(configFile.getProperty("elementRapport"));
		
		JTable summaryTable = new JTable(columns, columnNames);
		JScrollPane scrollPane = new JScrollPane( summaryTable,
		JScrollPane. VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS ); 
		scrollPane.setVisible(true);
		this.add( scrollPane , BorderLayout.CENTER);
		
		
		for (Hashes hash : hashesList) {
			id = new JLabel(String.valueOf(hash.getId()));
			date = new JLabel(String.valueOf(hash.getDate()));
			 rapport = new JButton("Bekijk rapport");
			
			summaryTable.add(id);
			summaryTable.add(date);
			summaryTable.add(rapport);
						
		}
		
	}
	
	private void bottemPanelLayout() {
		JPanel bottemPanel = new JPanel(new FlowLayout());
		JButton sendQuestionnaire = new JButton(configFile.getProperty("btnSendQuestionnaire"));
		JButton acceptButton = new JButton(configFile.getProperty("btnAccept"));
		JButton refuseButton = new JButton(configFile.getProperty("btnRefuse"));
		
		sendQuestionnaire.addActionListener(new WindowManager());
		
		bottemPanel.add(sendQuestionnaire);
		bottemPanel.add(acceptButton);
		bottemPanel.add(refuseButton);
		
		this.add(bottemPanel, BorderLayout.SOUTH);
	}
	

}
