package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.pxl.listeners.ButtonListener;
import be.pxl.listeners.WindowManager;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SettingClass;

public class SendQuestionnaireWindow extends JFrame {

	private static final long serialVersionUID = -5966739664048277914L;
	private Properties configFile = new ConfigFile().getConfigFile();
	JTextField clientEmailTextField;
	JTextField caregiverEmailTextField;
	JTextField clientAgeTextField;
	JTextField caregiverAgeTextField;
	JTextField relationTextField;
	JTextField categoryTextField;

	public SendQuestionnaireWindow() {
		this.setLayout(new BorderLayout());
		topPanelLayout();
		centerPanelLayout();
		bottemPanelLayout();
	}
	
	private void topPanelLayout() {
		JPanel topPanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel(configFile.getProperty("labelSendQuestionList"));
		title.setFont(new SettingClass().getTitleFont());
		
		topPanel.add(title);
		
		this.add(topPanel, BorderLayout.NORTH);
	}
	
	private void centerPanelLayout() {
		JPanel centerPanel = new JPanel(new FlowLayout());
		
		JLabel clientEmailLabel = new JLabel(configFile.getProperty("labelClientMail"));
		JLabel caregiverEmailLabel = new JLabel(configFile.getProperty("labelCareGiverMail"));
		JLabel clientAgeLabel = new JLabel(configFile.getProperty("labelClientAge"));
		JLabel caregiverAgeLabel = new JLabel(configFile.getProperty("labelCareGiverAge"));
		JLabel relationLabel = new JLabel(configFile.getProperty("labelRelation"));
		JLabel categoryLabel = new JLabel(configFile.getProperty("labelCategory"));
		
		clientEmailLabel.setPreferredSize(new Dimension(150, 20));
		caregiverEmailLabel.setPreferredSize(new Dimension(150, 20));
		clientAgeLabel.setPreferredSize(new Dimension(150, 20));
		caregiverAgeLabel.setPreferredSize(new Dimension(150, 20));
		relationLabel.setPreferredSize(new Dimension(150, 20));
		categoryLabel.setPreferredSize(new Dimension(150, 20));
		
		clientEmailTextField = new JTextField(20);
		caregiverEmailTextField = new JTextField(20);
		clientAgeTextField = new JTextField(20);
		caregiverAgeTextField = new JTextField(20);
		relationTextField = new JTextField(20);
		categoryTextField = new JTextField(20);
		
		centerPanel.add(clientEmailLabel);
		centerPanel.add(clientEmailTextField);
		centerPanel.add(caregiverEmailLabel);
		centerPanel.add(caregiverEmailTextField);
		centerPanel.add(clientAgeLabel);
		centerPanel.add(clientAgeTextField);
		centerPanel.add(caregiverAgeLabel);
		centerPanel.add(caregiverAgeTextField);
		centerPanel.add(relationLabel);
		centerPanel.add(relationTextField);
		centerPanel.add(categoryLabel);
		centerPanel.add(categoryTextField);
		
		this.add(centerPanel, BorderLayout.CENTER);
	}
	
	private void bottemPanelLayout() {
		JPanel bottemPanel = new JPanel(new FlowLayout());
		JButton sendButton = new JButton(configFile.getProperty("btnSend"));
		JButton cancelButton = new JButton(configFile.getProperty("btnCancel"));
		
		cancelButton.addActionListener(new ButtonListener(this));
		sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		bottemPanel.add(sendButton);
		bottemPanel.add(cancelButton);
		this.add(bottemPanel, BorderLayout.SOUTH);
	}

}
