package be.pxl.windows;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import be.pxl.listeners.FrameListener;

public class HomeWindow extends JFrame {

	private static final long serialVersionUID = -5686952163744219865L;
	private	JTabbedPane tabbedPane;
	private SummaryPanel summaryPanel = new SummaryPanel();
	private QuestionnairePanel questionnairePanel = new QuestionnairePanel();
	private UsersPanel usersPanel = new UsersPanel();
	
	public HomeWindow() {
		this.addWindowListener(new FrameListener());
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Overzicht", summaryPanel);
		tabbedPane.addTab("Vragenlijsten", questionnairePanel);
		tabbedPane.addTab("Gebruikers", usersPanel);
		
		this.add(tabbedPane);
	}
	
	public void refreshUserPanel() {
		tabbedPane.remove(usersPanel);
		usersPanel = new UsersPanel();
		tabbedPane.add(usersPanel);
		this.repaint();
	}
}
