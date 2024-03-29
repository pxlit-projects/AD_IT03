package be.pxl.listeners;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import be.pxl.json.LoginService;
import be.pxl.objects.Theme;
import be.pxl.objects.User;
import be.pxl.settings.ConfigFile;
import be.pxl.windows.AddQuestionWindow;
import be.pxl.windows.AddThemeWindow;
import be.pxl.windows.AddUserWindow;
import be.pxl.windows.AmountToAddQuestionsWindow;
import be.pxl.windows.EditUserWindow;
import be.pxl.windows.HomeWindow;
import be.pxl.windows.QuestionnaireWindow;
import be.pxl.windows.RapportenWindow;
import be.pxl.windows.SendQuestionnaireWindow;
import be.pxl.windows.SummaryPanel;
import be.pxl.windows.ThemePanel;
import be.pxl.windows.UsersPanel;
import be.pxl.windows.ViewUserWindow;

public class WindowManager implements ActionListener {

	private JFrame previousFrame;
	private User user;
	private String frameName = "";
	private UsersPanel usersPanel;
	private JTextField usernameField;
	private JTextField passwordField;
	private Theme theme;
	private int numberOfQuestions;
	@SuppressWarnings("unused")
	private String themeName;
	private ThemePanel themePanel;
	private Properties configFile = new ConfigFile().getConfigFile();
	private QuestionnaireWindow questionnaireWindow;
	private SummaryPanel summaryPanel;
	
	public WindowManager(JFrame frame) {
		this.previousFrame = frame;
	}

	public WindowManager(JFrame frame, JTextField usernameField,
			JTextField passwordField) {
		this.previousFrame = frame;
		this.usernameField = usernameField;
		this.passwordField = passwordField;
	}
	
	public WindowManager(Theme theme) {
		this.theme = theme;
	}
	
	public WindowManager(Theme theme, QuestionnaireWindow questionnaireWindow) {
		this.theme = theme;
		this.questionnaireWindow = questionnaireWindow;
	}
	
	public WindowManager(ThemePanel themePanel) {
		this.themePanel = themePanel;
	}

	public WindowManager(User user, UsersPanel usersPanel) {
		this.usersPanel = usersPanel;
		this.user = user;
	}

	public WindowManager(User user, String frameName) {
		this.user = user;
		this.frameName = frameName;
	}

	public WindowManager(UsersPanel usersPanel) {
		this.usersPanel = usersPanel;
	}
	
	public WindowManager (String hash) {
		JFrame frame = new RapportenWindow(hash);
		frame = windowFullScreen(frame);
	}
	
	public WindowManager (SummaryPanel summaryPanel) {
		this.summaryPanel = summaryPanel;
	}
	
//	
//	public WindowManager(int numberOfQuestions, String themeName) {
//		this.numberOfQuestions = numberOfQuestions;
//		this.themeName = themeName;
//	}
	
	public WindowManager(int numberOfQuestions, Theme theme, QuestionnaireWindow questionnaireWindow) {
		this.numberOfQuestions = numberOfQuestions;
		this.theme = theme;
		this.themeName = theme.getTitle();
		this.questionnaireWindow = questionnaireWindow;
	}
	public WindowManager(int numberOfQuestions, Theme theme) {
		this.numberOfQuestions = numberOfQuestions;
		this.theme = theme;
		this.themeName = theme.getTitle();
	}

	public WindowManager() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String text = button.getText();

		if (text.equalsIgnoreCase(configFile.getProperty("btnLogin"))) {

			if (new LoginService().loginCheck(usernameField.getText(), passwordField.getText())) {
				previousFrame.dispose();
				JFrame frame = new HomeWindow(usernameField.getText());
				frame = windowFullScreen(frame);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			} else {
				JOptionPane.showMessageDialog(null,
						configFile.getProperty("wrongLogin"), "Inlogfout!", JOptionPane.ERROR_MESSAGE);
			}

		}

		if (text.equalsIgnoreCase(configFile.getProperty("btnEditUser"))) {
			JFrame frame = new EditUserWindow(user, usersPanel);
			frame = windowNotFullScreen(frame);
		}

		if (text.equalsIgnoreCase(configFile.getProperty("btnAddUsers"))) {
			JFrame frame = new AddUserWindow(usersPanel);
			frame = windowNotFullScreen(frame);
		}

		if (text.equalsIgnoreCase(configFile.getProperty("btnViewUser"))
				|| frameName.equalsIgnoreCase(configFile.getProperty("btnViewUser"))) {
			JFrame frame = new ViewUserWindow(user, usersPanel);
			frame = windowNotFullScreen(frame);
		}
		
		if (text.equalsIgnoreCase(configFile.getProperty("btnViewQuestions"))) {
			JFrame frame = new QuestionnaireWindow(theme);
			frame = windowNotFullScreen(frame);
			frame.setSize(1400, 500);
			frame.setLocationRelativeTo(null);	
		}
		
		if (text.equalsIgnoreCase(configFile.getProperty("btnAddTheme"))){
			JFrame frame = new AddThemeWindow(themePanel);
			frame = windowNotFullScreen(frame);
			frame.setSize(300, 200);
			frame.setLocationRelativeTo(null);
		}
		
		if (text.equalsIgnoreCase(configFile.getProperty("btnAddQuestion"))) {
			JFrame frame = new AddQuestionWindow(numberOfQuestions, theme, questionnaireWindow);
			frame = windowNotFullScreen(frame);
			frame.setSize(1100, 500);
			frame.setLocationRelativeTo(null);
		}
		
		if (text.equalsIgnoreCase(configFile.getProperty("btnAddQuestions"))) {
			JFrame frame = new AmountToAddQuestionsWindow(theme, questionnaireWindow);
			frame = windowNotFullScreen(frame);
			frame.setSize(300, 200);
			frame.setLocationRelativeTo(null);
		}
		
		if (text.equalsIgnoreCase(configFile.getProperty("btnSendQuestionnaire"))) {
			JFrame frame = new SendQuestionnaireWindow(summaryPanel);
			frame = windowNotFullScreen(frame);
			frame.setSize(400, 200);
			frame.setLocationRelativeTo(null);
		}
	}

	private JFrame windowFullScreen(JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 400);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
		return frame;
	}

	private JFrame windowNotFullScreen(JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 400);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
		return frame;
	}

}
