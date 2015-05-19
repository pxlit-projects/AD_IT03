package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.pxl.json.QuestionDb;
import be.pxl.json.ThemeDb;
import be.pxl.objects.AnswerList;
import be.pxl.objects.Question;
import be.pxl.objects.Theme;
import be.pxl.settings.SettingClass;


public class RapportenWindow extends JFrame {
	
	private List<Question> questionList = new QuestionDb().readQuestions();
	private List<Theme> themeList = new ThemeDb().readThemes();
	
	
	public RapportenWindow(AnswerList answerlist) {
		this.setLayout(new BorderLayout());
		headerLayout();
		table();
	}
	
	private void headerLayout() {
		JPanel headerPanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel("Rapport");
		title.setFont(new SettingClass().getTitleFont());
		
		headerPanel.add(title);
		
		this.add(headerPanel, BorderLayout.NORTH);
	}
	
	private void table() {
		
	}
}
