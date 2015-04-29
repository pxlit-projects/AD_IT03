package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import be.pxl.listeners.ButtonListener;
import be.pxl.settings.SettingClass;

public class AddQuestionWindow extends JFrame{

	private static final long serialVersionUID = 7129058725274192581L;
	private JPanel centerPanel = new JPanel();
	private int numberOfQuestions;
	private String themeName;

	public AddQuestionWindow(int numberOfQuestions, String themeName) {
		super("Vragen toevoegen");
		this.setLayout(new BorderLayout());
		this.numberOfQuestions = numberOfQuestions;
		this.themeName = themeName;
		topPanelLayout();
		centerPanelLayout();
		bottemPanelLayout();
	}
	
	private void topPanelLayout() {
		JPanel titlePanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel("Vragen toevoegen aan het thema: " + themeName);
		title.setFont(new SettingClass().getTitleFont());
		titlePanel.add(title);
		this.add(titlePanel, BorderLayout.NORTH);
	}
	
	private void centerPanelLayout() {
		JScrollPane scroller = new JScrollPane(centerPanel);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scroller, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(numberOfQuestions, 1));
		JTextField [] questionTextFields = new JTextField[numberOfQuestions];
		JTextField [] descriptionTextFields = new JTextField[numberOfQuestions];
		JPanel [] questionPanels = new JPanel[numberOfQuestions];
		JLabel [] questionLabels = new JLabel[numberOfQuestions];
		JLabel [] descriptionLabels = new JLabel[numberOfQuestions];
		
		for (int i = 0; i < numberOfQuestions; i++) {
			questionPanels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			questionLabels[i] = new JLabel("Vraag " + (i+1) + ": ");
			descriptionLabels[i] = new JLabel("Beschrijving: ");
			questionTextFields[i] = new JTextField(40);
			descriptionTextFields[i] = new JTextField(40);
			
			questionPanels[i].add(questionLabels[i]);
			questionPanels[i].add(questionTextFields[i]);
			questionPanels[i].add(descriptionLabels[i]);
			questionPanels[i].add(descriptionTextFields[i]);
			centerPanel.add(questionPanels[i]);
		}
	}
	
	private void bottemPanelLayout() {
		JPanel bottemPanel = new JPanel(new FlowLayout());
		JButton cancelButton = new JButton("Annuleren");
		JButton saveButton = new JButton("Opslaan");
		bottemPanel.add(saveButton);
		bottemPanel.add(cancelButton);
		
		cancelButton.addActionListener(new ButtonListener(this));
		
		this.add(bottemPanel, BorderLayout.SOUTH);
	}

}
