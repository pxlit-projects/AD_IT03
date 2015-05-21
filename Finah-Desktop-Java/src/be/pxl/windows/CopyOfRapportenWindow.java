package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.pxl.json.QuestionDb;
import be.pxl.json.ThemeDb;
import be.pxl.objects.Question;
import be.pxl.objects.Theme;

public class CopyOfRapportenWindow extends JFrame {

	private static final long serialVersionUID = 6223578293165741823L;
	ThemeDb themes = new ThemeDb();
	QuestionDb questions = new QuestionDb();
		JLabel hinderclient;
		JLabel hinderMZ;
		JLabel hulpvraag;
		JLabel	empty;
		JPanel panel1;
		JPanel headerPanel;
		JPanel themePanel;
	public CopyOfRapportenWindow() {
		@SuppressWarnings("unused")
		QuestionDb questionDb = new QuestionDb();
		Theme theme = new Theme();
		this.setLayout(new BorderLayout());
		panel1 = new JPanel(new GridLayout(11,1));
		
		List<Theme> themeList = themes.readThemes();
		List<Question> questionList = questions.readQuestions();
		List<Question> questionByTheme = new ArrayList<Question>();
		hinderclient = new JLabel("Hinder voor client gerapporteerd door");
		hinderMZ = new JLabel("Hinder voor mantelzorger gerapporteerd door");
		hulpvraag = new JLabel("Hulpvraag gesteld door");
		empty = new JLabel("");
		
		headerPanel = new JPanel(new GridLayout(1,4));
		headerPanel.add(empty);
		headerPanel.add(hinderclient);
		headerPanel.add(hinderMZ);
		headerPanel.add(hulpvraag);
		
		this.add(headerPanel);
		
		for (int i = 0; i <themeList.size(); i++){
			theme = (Theme) themeList.get(i);
			@SuppressWarnings("unused")
			int themeId = theme.getId();
			for (int j = 0; j < questionList.size(); j++){
				
//				 questionByTheme.add(questionDb.getQuestionByThemeId(themeId));
				
			}
			 themePanel = new JPanel(new GridLayout(questionByTheme.size()+1, 6));
			JLabel themeTitle = new JLabel(theme.getTitle());
			themePanel.add(themeTitle);
			
			for(int k = 0 ; k < questionByTheme.size() ; k++){
				themePanel.add(empty);
				themePanel.add(empty);
				themePanel.add(empty);
				themePanel.add(empty);
				themePanel.add(empty);
				Question question = (Question) questionByTheme.get(k);
				JLabel questionDescription = new JLabel(question.getDescription());
				themePanel.add(questionDescription);
			}
		
			
			
			
		}
		this.add(themePanel);
		
	
		
		
	}
}
