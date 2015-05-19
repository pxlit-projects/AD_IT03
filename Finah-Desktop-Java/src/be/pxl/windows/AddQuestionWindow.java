package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.xswingx.PromptSupport;
import org.jdesktop.xswingx.PromptSupport.FocusBehavior;

import be.pxl.json.QuestionDb;
import be.pxl.json.WriteToWeb;
import be.pxl.listeners.ButtonListener;
import be.pxl.objects.Question;
import be.pxl.objects.Theme;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SettingClass;

public class AddQuestionWindow extends JFrame {

	private static final long serialVersionUID = 7129058725274192581L;
	private JPanel centerPanel = new JPanel();
	private int numberOfQuestions;
	private String themeName;
	Properties configFile = new ConfigFile().getConfigFile();

	private JTextField[] questionTextFields;
	private JTextField[] descriptionTextFields;
	private List<Question> questions = new ArrayList<Question>();

	private Theme theme;
	private JFrame frame;

	public AddQuestionWindow(int numberOfQuestions, Theme theme) {
		super("Vragen toevoegen");

		this.setLayout(new BorderLayout());
		this.numberOfQuestions = numberOfQuestions;
		this.themeName = theme.getTitle();
		this.theme = theme;
		this.frame = this;
		
		topPanelLayout();
		centerPanelLayout();
		bottemPanelLayout();
	}

	private void topPanelLayout() {
		JPanel titlePanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel(configFile.getProperty("addQuestionTitle")
				+ themeName);
		title.setFont(new SettingClass().getTitleFont());
		titlePanel.add(title);
		this.add(titlePanel, BorderLayout.NORTH);
	}

	private void centerPanelLayout() {
		JScrollPane scroller = new JScrollPane(centerPanel);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scroller, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(numberOfQuestions, 1));
		questionTextFields = new JTextField[numberOfQuestions];
		descriptionTextFields = new JTextField[numberOfQuestions];
		JPanel[] questionPanels = new JPanel[numberOfQuestions];
		JLabel[] questionLabels = new JLabel[numberOfQuestions];
		JLabel[] descriptionLabels = new JLabel[numberOfQuestions];

		for (int i = 0; i < numberOfQuestions; i++) {
			questionPanels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			questionLabels[i] = new JLabel(
					configFile.getProperty("questionLabel") + (i + 1) + ": ");
			descriptionLabels[i] = new JLabel(
					configFile.getProperty("descriptionLabel"));
			questionTextFields[i] = new JTextField(40);
			descriptionTextFields[i] = new JTextField(40);

			PromptSupport.setPrompt(configFile.getProperty("promptQuestion"),
					questionTextFields[i]);
			PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT,
					questionTextFields[i]);
			;
			PromptSupport.setPrompt(configFile.getProperty("promptDescription")
					+ (i + 1), descriptionTextFields[i]);
			PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT,
					descriptionTextFields[i]);
			;
			PromptSupport.setForeground(Color.GRAY, questionTextFields[i]);
			PromptSupport.setForeground(Color.GRAY, descriptionTextFields[i]);

			questionPanels[i].add(questionLabels[i]);
			questionPanels[i].add(questionTextFields[i]);
			questionPanels[i].add(descriptionLabels[i]);
			questionPanels[i].add(descriptionTextFields[i]);
			centerPanel.add(questionPanels[i]);
		}
	}

	private void bottemPanelLayout() {
		JPanel bottemPanel = new JPanel(new FlowLayout());
		JButton cancelButton = new JButton(configFile.getProperty("btnCancel"));
		JButton saveButton = new JButton(configFile.getProperty("btnSave"));
		bottemPanel.add(saveButton);

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkInput()) {
					for (int i = 0; i < questionTextFields.length; i++) {
						try {
							questions.add(new Question(questionTextFields[i].getText(), descriptionTextFields[i].getText(), theme.getId()));
						} catch (NullPointerException npe) {
							questions.add(new Question(questionTextFields[i].getText(), theme.getId()));
						}
					}
					new QuestionDb().addQuestions(questions);
					frame.dispose();
				}
			}
		});

		bottemPanel.add(cancelButton);

		cancelButton.addActionListener(new ButtonListener(this));

		this.add(bottemPanel, BorderLayout.SOUTH);
	}

	private boolean checkInput() {
		for (JTextField jTextField : questionTextFields) {
			if (jTextField.getText().trim().isEmpty()) {
				return false;
			}
		}
		return true;
	}

}
