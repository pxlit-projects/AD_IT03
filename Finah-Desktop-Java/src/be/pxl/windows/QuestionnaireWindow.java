package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import be.pxl.json.QuestionDb;
import be.pxl.json.UserDb;
import be.pxl.listeners.ButtonListener;
import be.pxl.listeners.WindowManager;
import be.pxl.objects.Question;
import be.pxl.objects.Theme;
import be.pxl.settings.ConfigFile;

public class QuestionnaireWindow extends JFrame {

	private static final long serialVersionUID = 4427682445425152880L;
	
	private ScrollPane questionTableScroll = new ScrollPane();
	private DefaultTableModel model;
	private Vector<Vector<String>> data;
	
	private JTable questionTable;
	private Theme theme;
	private List<Question> questions = new ArrayList<Question>();
	private Properties configFile = new ConfigFile().getConfigFile();
	private QuestionnaireWindow questionnaireWindow;
	
	private JButton addQuestion;
	private JButton deleteQuestions;
	private JButton cancelButton;
	
	private List<Question> selectedQuestions = new ArrayList<Question>();

	public QuestionnaireWindow(Theme theme) {
		super("Vragenlijst");
		this.theme = theme;
		this.questionnaireWindow = this;
		
		questions = new QuestionDb().getQuestionByThemeId(theme.getId());
		this.setLayout(new BorderLayout());
		
		windowTop();
		windowBody();
		windowFooter();
		
	}
	
	private void windowTop() {
		JLabel titleLabel = new JLabel(theme.getTitle());
		this.add(titleLabel, BorderLayout.NORTH);
	}
	
	private void windowBody() {
		fillQuestionTable();
		questionTable = new JTable(model);
		questionTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				getSelectedQuestions();
				
			}
		});
		questionTableScroll.add(questionTable);
		this.add(questionTableScroll, BorderLayout.CENTER);
	}
	
	private void windowFooter() {
		addQuestion = new JButton(configFile.getProperty("btnAddQuestions"));
		deleteQuestions = new JButton (configFile.getProperty("btnDeleteQuestion"));
		cancelButton = new JButton(configFile.getProperty("btnCancel"));
		
		deleteQuestions.setEnabled(false);
		
		deleteQuestions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new QuestionDb().deleteQuestions(selectedQuestions);
				refreshTable();
			}
		});
		
		addQuestion.addActionListener(new WindowManager(theme, questionnaireWindow));
		
		cancelButton.addActionListener(new ButtonListener(this));
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(addQuestion);
		buttonPanel.add(deleteQuestions);
		buttonPanel.add(cancelButton);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void fillQuestionTable() {
		try {
			data = null;
			data = new Vector<Vector<String>>();
			for (int i = 0; i < questions.size(); i++) {
				Vector<String> tmp = new Vector<String>();
				tmp.addElement(questions.get(i).getTitle());
				tmp.addElement(questions.get(i).getDescription());

				// add to model
				data.addElement(tmp);
			}

			Vector<String> heading = new Vector<String>();
			heading.addElement(configFile.getProperty("questionLabel"));
			heading.addElement(configFile.getProperty("labelDescription"));
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
	
	private void getSelectedQuestions() {
		selectedQuestions.clear();
		int[] rowIndexes = questionTable.getSelectedRows();
		for (int i = 0; i < rowIndexes.length; i++) {
			selectedQuestions.add(questions.get(rowIndexes[i]));
		}
		
		if (rowIndexes.length == 0) {
			deleteQuestions.setEnabled(false);
		} else {
			deleteQuestions.setEnabled(true);
		}

	}

	public void refreshTable() {
		questions = new QuestionDb().getQuestionByThemeId(theme.getId());
		fillQuestionTable();
		questionTable.setModel(model);

	}

}
