package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import be.pxl.database.DatabaseConnection;
import be.pxl.database.ReadFromDatabase;
import be.pxl.objects.Question;
import be.pxl.objects.Theme;
import be.pxl.objects.User;

public class ThemePanel extends JPanel {

	private static final long serialVersionUID = -1471537834378662928L;
	private JLabel title;
	private JTable QuestionTable;
	private List<Question> questions = new ReadFromDatabase().readQuestions();
	private List<Theme> themes = new ReadFromDatabase().readThemes();
	private DefaultTableModel model;
	
	
	public ThemePanel() {

		// Frame Layout
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Title
		title = new JLabel("Question list\n");
		Font titleFont = new Font("Arial", Font.PLAIN, 32);
		title.setFont(titleFont);

		fillQuestionTable();
		ScrollPane usersTableScroll = new ScrollPane();
		JPanel usersScrollPanel = new JPanel(new BorderLayout());
		usersTableScroll.add(usersScrollPanel);
		usersScrollPanel
				.add(QuestionTable.getTableHeader(), BorderLayout.NORTH);
		usersScrollPanel.add(QuestionTable, BorderLayout.CENTER);

		// Bottom buttons
		JButton viewUserButton = new JButton("Bekijk gegevens");
		JButton editUserButton = new JButton("Bewerken");
		JButton deleteUserButton = new JButton("Verwijderen");
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(viewUserButton);
		buttonPanel.add(editUserButton);
		buttonPanel.add(deleteUserButton);

		// Add to frame
		this.add(title, BorderLayout.NORTH);
		this.add(usersTableScroll, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fillQuestionTable() {
		try {
			String title, description, themeId, choice;

			Vector heading = new Vector();
			heading.addElement("Title");
			heading.addElement("Description");
			heading.addElement("Theme");
			heading.addElement("Choice");

			Vector data = new Vector();
			
			for (int i = 0; i < questions.size(); i++) {
				Vector<String> tmp = new Vector<String>();
				tmp.addElement(questions.get(i).getTitle());
				tmp.addElement(questions.get(i).getDescription());
				tmp.addElement(themes.get(questions.get(i).getThemeId()-1).getTitle());
				tmp.addElement(String.valueOf(questions.get(i).getChoice()));
				
				
				// add to model
				data.addElement(tmp);
			}
			
			model = null;
			model = new DefaultTableModel(data, heading) {
				private static final long serialVersionUID = 6906295176690369795L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
//			
			// data en heading in de tabel steken
			QuestionTable = new JTable(data, heading);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
