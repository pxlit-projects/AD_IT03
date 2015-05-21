package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import be.pxl.json.AnswerDb;
import be.pxl.json.QuestionDb;
import be.pxl.json.ThemeDb;
import be.pxl.objects.AnswerList;
import be.pxl.objects.Question;
import be.pxl.objects.Theme;
import be.pxl.objects.UserType;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SettingClass;

public class RapportenWindow extends JFrame {

	private static final long serialVersionUID = -497157798407330303L;
	private Properties configFile = new ConfigFile().getConfigFile();
	
	private List<AnswerList> answerList = new ArrayList<AnswerList>();
	private List<Theme> themes = new ThemeDb().readThemes();
	private List<Question> questions = new 	QuestionDb().readQuestions();
	
	private JTable rapportTable;
	
	private DefaultTableModel model;
	private Vector<Vector<String>> data;
	private ScrollPane rapportTableScroll;
	private JPanel rapportScrollPanel;
	
	private String hash;
	
	

	public RapportenWindow(String hash) {
		this.setLayout(new BorderLayout());
		this.hash = hash;
		answerList = new AnswerDb().readAnswersByHash(hash);
		topPanelLayout();
//		centerPanelLayout();
		fillUsersTable();
		addTable();
	}

	private void topPanelLayout() {
		JPanel topPanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel(configFile.getProperty("labelRapport"));
		title.setFont(new SettingClass().getTitleFont());
		topPanel.add(title);
		this.add(topPanel, BorderLayout.NORTH);
	}
	
	private void addTable() {
		// data en heading in de tabel steken
		rapportTable = new JTable(model);

		rapportTableScroll = new ScrollPane();
		rapportScrollPanel = new JPanel(new BorderLayout());
		rapportTableScroll.add(rapportScrollPanel);
		rapportScrollPanel.add(rapportTable.getTableHeader(), BorderLayout.NORTH);
		rapportScrollPanel.add(rapportTable, BorderLayout.CENTER);
		this.add(rapportTableScroll, BorderLayout.CENTER);
	}
	
	private void fillUsersTable() {
		try {
			data = null;
			data = new Vector<Vector<String>>();
			for (int i = 0; i < 53; i++) {
				Vector<String> tmp = new Vector<String>();
				tmp.addElement(themes.get(questions.get(i).getThemeId()-1).getTitle());
				tmp.addElement(questions.get(i).getTitle());
				tmp.addElement(String.valueOf(getScore(3, answerList.get(i).getQuestion())));
				tmp.addElement(String.valueOf(getScore(4, answerList.get(i).getQuestion())));
				tmp.addElement("boe");
				
				// add to model
				data.addElement(tmp);
			}

			Vector<String> heading = new Vector<String>();
			heading.addElement("Thema");
			heading.addElement("Stelling");
			heading.addElement("Antwoord Patient");
			heading.addElement("Antwoord Mantelzorger");
			heading.addElement("Hulp gevraagd door");
			model = null;
			model = new DefaultTableModel(data, heading) {
				private static final long serialVersionUID = 6906295176690369795L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private String getScore(int usertype, int question) {
		String result = "";
		for (AnswerList answerList2 : answerList) {
			if(usertype == answerList2.getUsertype() && question == answerList2.getQuestion() && hash.equalsIgnoreCase(answerList2.getHash())){
				
				int answer = answerList2.getAnswer();
				
				switch (answer) {
				case 1: result = "Verloopt naar wens";
						break;
				case 2: result = "Probleem niet hinderlijk";
				break;
				case 3: result = "Probleem hinderlijk voor cliënt.";
				break;
				case 4: result = "Probleem hinderlijk voor mantelzorger.";
				break;
				case 5: result = "Probleem hinderlijk voor beide.";
				break;
				default: result = "-1";
				}

			}
		}
		return result;
	}
	
	
	
}
