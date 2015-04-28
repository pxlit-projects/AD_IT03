package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import be.pxl.database.ReadFromDatabase;
import be.pxl.json.UserDb;
import be.pxl.listeners.ButtonListener;
import be.pxl.listeners.WindowManager;
import be.pxl.objects.User;
import be.pxl.objects.UserType;

public class UsersPanel extends JPanel {

	private static final long serialVersionUID = 5054771516129361055L;
	private JLabel title;
	private JTable usersTable;
	private List<User> users = new ArrayList<User>();
	private List<UserType> userTypes = new ArrayList<UserType>();
	private JPanel usersScrollPanel;
	private JButton viewUserButton;
	private JButton editUserButton;
	private JButton deleteUserButton;
	private ScrollPane usersTableScroll;
	private DefaultTableModel model;
	private Vector<Vector<String>> data;
	private UsersPanel usersPanel;
	private List<User> selectedUsers = new ArrayList<User>();

	public UsersPanel() {

		// Frame Layout
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		usersPanel = this;

		// Title
		title = new JLabel("Gebruikers\n");
		Font titleFont = new Font("Arial", Font.PLAIN, 32);
		title.setFont(titleFont);

//		users = new ReadFromDatabase().readUsers();
		users = new UserDb().readUsers();
		System.out.println();
		
		userTypes = new UserDb().readUserTypes();
		fillUsersTable();
		addTable();

		// Bottom buttons
		JButton addUserButton = new JButton("Nieuwe gebruiker toevoegen");
		viewUserButton = new JButton("Bekijk gegevens");
		editUserButton = new JButton("Bewerken");
		deleteUserButton = new JButton("Verwijderen");
		setButtonsEnabled(false);
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(addUserButton);
		buttonPanel.add(viewUserButton);
		buttonPanel.add(editUserButton);
		buttonPanel.add(deleteUserButton);

		// Buttons add actions
		addUserButton.addActionListener(new WindowManager(this));
		// editUserButton.addActionListener(new WindowManager());

		viewUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowManager(selectedUsers.get(0), usersPanel)
						.actionPerformed(e);
			}
		});

		editUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowManager(selectedUsers.get(0), usersPanel)
						.actionPerformed(e);

			}
		});

		deleteUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ButtonListener(selectedUsers, usersPanel)
						.actionPerformed(e);
				;

			}
		});

		usersTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						getSelectedUser();
					}
				});

		// Add to frame
		this.add(title, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void getSelectedUser() {
		selectedUsers.clear();
		int[] rowIndexes = usersTable.getSelectedRows();
		for (int i = 0; i < rowIndexes.length; i++) {
			selectedUsers.add(users.get(rowIndexes[i]));
		}

		if (rowIndexes.length == 1) {
			setButtonsEnabled(true);
		} else if (rowIndexes.length == 0) {
			setButtonsEnabled(false);
		} else {
			viewUserButton.setEnabled(false);
			editUserButton.setEnabled(false);
			deleteUserButton.setEnabled(true);
		}

	}

	private void setButtonsEnabled(boolean value) {
		viewUserButton.setEnabled(value);
		editUserButton.setEnabled(value);
		deleteUserButton.setEnabled(value);
	}

	private void fillUsersTable() {
		try {
			data = null;
			data = new Vector<Vector<String>>();
			for (int i = 0; i < users.size(); i++) {
				Vector<String> tmp = new Vector<String>();
				tmp.addElement(users.get(i).getFirstname());
				tmp.addElement(users.get(i).getLogin());
				tmp.addElement(userTypes.get(users.get(i).getType()-1).getTypeName());

				// add to model
				data.addElement(tmp);
			}

			Vector<String> heading = new Vector<String>();
			heading.addElement("Naam");
			heading.addElement("Login");
			heading.addElement("Functie");
			model = null;
			model = new DefaultTableModel(data, heading) {
				private static final long serialVersionUID = 6906295176690369795L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void addTable() {
		// data en heading in de tabel steken
		usersTable = new JTable(model);

		usersTableScroll = new ScrollPane();
		usersScrollPanel = new JPanel(new BorderLayout());
		usersTableScroll.add(usersScrollPanel);
		usersScrollPanel.add(usersTable.getTableHeader(), BorderLayout.NORTH);
		usersScrollPanel.add(usersTable, BorderLayout.CENTER);
		this.add(usersTableScroll, BorderLayout.CENTER);
	}

	public void refreshTable() {
		users = new UserDb().readUsers();
		fillUsersTable();
		usersTable.setModel(model);

	}

}
