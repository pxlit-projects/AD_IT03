package be.pxl.windows;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class HomeWindow extends JFrame {

	private static final long serialVersionUID = -5686952163744219865L;
	private	JTabbedPane tabbedPane;
	
	public HomeWindow() {

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Overzicht", new SummaryPanel());
		tabbedPane.addTab("Vragenlijsten", new QuestionnairePanel());
		tabbedPane.addTab("Gebruikers", new UsersPanel());
		
		this.add(tabbedPane);
	}
}
