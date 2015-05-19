package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.pxl.json.QuestionDb;
import be.pxl.json.ThemeDb;
import be.pxl.objects.AnswerList;
import be.pxl.objects.Question;
import be.pxl.objects.Theme;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SettingClass;


public class RapportenWindow extends JFrame {
private Properties configFile = new ConfigFile().getConfigFile();
	public RapportenWindow(AnswerList answerlist) {
		this.setLayout(new BorderLayout());
		headerLayout();
		table();
	}
	
	public RapportenWindow(){
		this.setLayout(new BorderLayout());
		topPanelLayout();
		centerPanelLayout();
		bottemPanelLayout();
	}
	
	private void topPanelLayout() {
		JPanel topPanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel(configFile.getProperty("labelRapport"));
		title.setFont(new SettingClass().getTitleFont());
		topPanel.add(title);
		this.add(topPanel, BorderLayout.NORTH);
	}
}
