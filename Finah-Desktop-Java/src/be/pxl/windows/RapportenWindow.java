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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import be.pxl.json.AnswerDb;
import be.pxl.json.QuestionDb;
import be.pxl.json.ThemeDb;
import be.pxl.objects.AnswerList;
import be.pxl.objects.Question;
import be.pxl.objects.Theme;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SettingClass;

public class RapportenWindow extends JFrame {

	private static final long serialVersionUID = -497157798407330303L;
	private Properties configFile = new ConfigFile().getConfigFile();

	private List<AnswerList> answerList = new ArrayList<AnswerList>();
	private List<Theme> themes = new ThemeDb().readThemes();
	private List<Question> questions = new QuestionDb().readQuestions();

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
		// centerPanelLayout();
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
		rapportScrollPanel.add(rapportTable.getTableHeader(),
				BorderLayout.NORTH);
		rapportScrollPanel.add(rapportTable, BorderLayout.CENTER);
		this.add(rapportTableScroll, BorderLayout.CENTER);
	}

	private void fillUsersTable() {
		try {
			data = null;
			data = new Vector<Vector<String>>();
			for (int i = 0; i < 53; i++) {
				Vector<String> tmp = new Vector<String>();
				tmp.addElement(themes.get(questions.get(i).getThemeId() - 1)
						.getTitle());
				tmp.addElement(questions.get(i).getTitle());
				tmp.addElement(String.valueOf(getScore(3, answerList.get(i)
						.getQuestion())));
				tmp.addElement(String.valueOf(getScore(4, answerList.get(i)
						.getQuestion())));
				tmp.addElement(getHelp(answerList.get(i).getQuestion()));

				// add to model
				data.addElement(tmp);
			}

			Vector<String> heading = new Vector<String>();
			heading.addElement(configFile.getProperty("elementThema"));
			heading.addElement(configFile.getProperty("elementStelling"));
			heading.addElement(configFile.getProperty("elementAntwoordP"));
			heading.addElement(configFile.getProperty("elementAntwoordM"));
			heading.addElement(configFile.getProperty("elementHulp"));
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
			if (usertype == answerList2.getUsertype()
					&& question == answerList2.getQuestion()
					&& hash.equalsIgnoreCase(answerList2.getHash())) {

				int answer = answerList2.getAnswer();

				switch (answer) {
				case 1:
					result = configFile.getProperty("result1");
					break;
				case 2:
					result = configFile.getProperty("result2");
					break;
				case 3:
					result = configFile.getProperty("result3");
					break;
				case 4:
					result = configFile.getProperty("result4");
					break;
				case 5:
					result = configFile.getProperty("result5");
					break;
				default:
					result = configFile.getProperty("result0");
				}

			}
		}
		return result;
	}

	private String getHelp(int question) {
		String result = "";
		boolean caregiver = false;
		boolean patient = false;
		for (AnswerList answerList2 : answerList) {
			if (question == answerList2.getQuestion()) {
				if(answerList2.getUsertype() == 4 && answerList2.getWorkpoint() == 1) {
					caregiver = true;
				} else if (answerList2.getUsertype() == 3 && answerList2.getWorkpoint() == 1) {
					patient = true;
				}
				if(caregiver && patient) {
					result = configFile.getProperty("resultMP");
				} else if (caregiver) {
					result = configFile.getProperty("resultM");
				} else if (patient) {
					result = configFile.getProperty("resultP");
				}
			}
		}
		return result;
	}

}
