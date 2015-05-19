package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import be.pxl.json.ThemeDb;
import be.pxl.json.UserDb;
import be.pxl.listeners.WindowManager;
import be.pxl.objects.Theme;
import be.pxl.settings.ConfigFile;

public class ThemePanel extends JPanel {

	private static final long serialVersionUID = -1471537834378662928L;
	private JLabel title;
	private JTable questionTable;
	
	private List<Theme> themes = new ThemeDb().readThemes();
	
	
	private DefaultTableModel model;
	private List<Theme> selectedTheme = new ArrayList<Theme>();
	private JButton viewQuestionsButton;
	private JButton addThemeButton;
	private JButton deleteThemeButton;	
	private Properties configFile = new ConfigFile().getConfigFile();
	
	private ThemePanel panel;
	
	public ThemePanel() {

		this.panel = this;
		
		// Frame Layout
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Title
		title = new JLabel(configFile.getProperty("labelTheme"));
		Font titleFont = new Font("Arial", Font.PLAIN, 32);
		title.setFont(titleFont);

		fillQuestionTable();
		questionTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				getSelectedTheme();
				
			}
		});
		
		ScrollPane usersTableScroll = new ScrollPane();
		JPanel usersScrollPanel = new JPanel(new BorderLayout());
		usersTableScroll.add(usersScrollPanel);
		usersScrollPanel
				.add(questionTable.getTableHeader(), BorderLayout.NORTH);
		usersScrollPanel.add(questionTable, BorderLayout.CENTER);

		// Bottom buttons
		viewQuestionsButton = new JButton(configFile.getProperty("btnViewQuestions"));
		addThemeButton = new JButton(configFile.getProperty("btnAddTheme"));
		deleteThemeButton = new JButton(configFile.getProperty("btnDelete"));
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(viewQuestionsButton);
		buttonPanel.add(addThemeButton);
		buttonPanel.add(deleteThemeButton);

		
		viewQuestionsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowManager(selectedTheme.get(0)).actionPerformed(e);;
				
			}
		});
		
		addThemeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowManager(panel).actionPerformed(e);
				
			}
		});
		
		
		
		// Add to frame
		this.add(title, BorderLayout.NORTH);
		this.add(usersTableScroll, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void fillQuestionTable() {
		try {
			Vector heading;
			heading = null;
			heading = new Vector();
			heading.addElement(configFile.getProperty("headingThemeID"));
			heading.addElement(configFile.getProperty("headingThemeTitle"));
			heading.addElement(configFile.getProperty("headingThemeDescription"));
			

			Vector data = new Vector();
			
			for (int i = 0; i < themes.size(); i++) {
				Vector<String> tmp = new Vector<String>();
				tmp.addElement(String.valueOf(themes.get(i).getId()));
				tmp.addElement(themes.get(i).getTitle());
				tmp.addElement(themes.get(i).getDescription());

				
				
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
			questionTable = new JTable(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void refreshTable() {
		themes = new ThemeDb().readThemes();
		fillQuestionTable();
		questionTable.setModel(model);
	}
	
	
	private void getSelectedTheme() {
		selectedTheme.clear();
		int[] rowIndexes = questionTable.getSelectedRows();
		for (int i = 0; i < rowIndexes.length; i++) {
			selectedTheme.add(themes.get(rowIndexes[i]));
		}

		if (rowIndexes.length == 1) {
			setButtonsEnabled(true);
		} else if (rowIndexes.length == 0) {
			setButtonsEnabled(false);
		} else {
			viewQuestionsButton.setEnabled(false);
			addThemeButton.setEnabled(false);
			deleteThemeButton.setEnabled(true);
		}

	}
	
	private void setButtonsEnabled(boolean value) {
		viewQuestionsButton.setEnabled(value);
		addThemeButton.setEnabled(value);
		deleteThemeButton.setEnabled(value);
	}
}
