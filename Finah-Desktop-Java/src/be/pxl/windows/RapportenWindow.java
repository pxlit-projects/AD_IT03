package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import be.pxl.json.QuestionDb;
import be.pxl.json.ThemeDb;
import be.pxl.objects.AnswerList;
import be.pxl.objects.Question;
import be.pxl.objects.Theme;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SettingClass;


public class RapportenWindow extends JFrame {
private Properties configFile = new ConfigFile().getConfigFile();
private Vector columnNames;
private Vector columns;


	public RapportenWindow(AnswerList answerlist) {
		this.setLayout(new BorderLayout());
		
	}
	
	public RapportenWindow(){
		this.setLayout(new BorderLayout());
		topPanelLayout();
		centerPanelLayout();
	}
	
	
	
	private void topPanelLayout() {
		JPanel topPanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel(configFile.getProperty("labelRapport"));
		title.setFont(new SettingClass().getTitleFont());
		topPanel.add(title);
		this.add(topPanel, BorderLayout.NORTH);
	}
	
	private void centerPanelLayout() {
		JPanel centerPanel = new JPanel(new FlowLayout());
		columnNames = new Vector();
		columns = new Vector();
		
		columnNames.addElement(configFile.getProperty("elementThema"));
		columnNames.addElement(configFile.getProperty("elementStelling"));
		columnNames.addElement(configFile.getProperty("elementAntwoordP"));
		columnNames.addElement(configFile.getProperty("elementAntwoordM"));
		columnNames.addElement(configFile.getProperty("elementHulp"));
		
		JTable rapportTable = new JTable(columns, columnNames);
		JScrollPane scrollPane = new JScrollPane( rapportTable,
		JScrollPane. VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS ); 
		scrollPane.setVisible(true);
		this.add( scrollPane , BorderLayout.CENTER);
	}
}
