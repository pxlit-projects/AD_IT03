package be.pxl.windows;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import be.pxl.database.ReadFromDatabase;
import be.pxl.listeners.FrameListener;

public class HomeWindow extends JFrame {

	private static final long serialVersionUID = -5686952163744219865L;
	private JTabbedPane tabbedPane;

	public HomeWindow(String login) {
		this.addWindowListener(new FrameListener());
		int typeId = new ReadFromDatabase().getTypeIdByLogin(login);
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Overzicht", new SummaryPanel());
		tabbedPane.addTab("Vragenlijsten", new ThemePanel());
		if (typeId == 1) {
			tabbedPane.addTab("Gebruikers", new UsersPanel());
		}

		this.add(tabbedPane);
	}
	//
	// public void refreshUserPanel() {
	// tabbedPane.remove(usersPanel);
	// usersPanel = new UsersPanel();
	// tabbedPane.add(usersPanel);
	// this.repaint();
	// }
}
