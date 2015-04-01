package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import be.pxl.database.ReadFromDatabase;
import be.pxl.objects.Question;
import be.pxl.objects.Theme;

public class QuestionnaireWindow extends JFrame {

	private static final long serialVersionUID = 4427682445425152880L;
	
	private ScrollPane questionTableScroll = new ScrollPane();
	private DefaultTableModel model;
	private Vector<Vector<String>> data;
	
	private JTable questionTable;
	private Theme theme;
	private List<Question> questions = new ArrayList<Question>();

	public QuestionnaireWindow(Theme theme) {
		super("Vragenlijst");
		this.theme = theme;
		questions = new ReadFromDatabase().readQuestionsByThemeId(theme.getId());
		
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
		questionTableScroll.add(questionTable);
		this.add(questionTableScroll, BorderLayout.CENTER);
	}
	
	private void windowFooter() {
		JButton addQuestion = new JButton("Voeg vraag toe");
		JButton editQuestion = new JButton("Bewerk vraag");
		JButton deleteQuestions = new JButton ("Verwijder vragen");
		JButton cancelButton = new JButton("Annuleren");
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(addQuestion);
		buttonPanel.add(editQuestion);
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
			heading.addElement("Vraag");
			heading.addElement("Omschrijving");
			model = null;
			model = new DefaultTableModel(data, heading) {
				private static final long serialVersionUID = 6906295176690369795L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			questionTable = new JTable(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
