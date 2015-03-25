package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import be.pxl.database.DatabaseConnection;
import be.pxl.listeners.ButtonListener;
import be.pxl.database.ReadFromDatabase;
import be.pxl.listeners.WindowManager;
import be.pxl.objects.User;
import be.pxl.objects.UserType;
import be.pxl.database.*;


public class UsersPanel extends JPanel {

	private static final long serialVersionUID = 5054771516129361055L;
	private JLabel title;
	private JTable usersTable;
	private List<User> users = new ArrayList<User>();
	private List<UserType> userTypes = new ArrayList<UserType>();
	private User selectedUser = new User();
	private JPanel usersScrollPanel;
	private ScrollPane usersTableScroll;
	private DefaultTableModel model;
	private Vector<Vector<String>> data;
	private UsersPanel usersPanel;

	public UsersPanel() {

		// Frame Layout
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		usersPanel = this;
		
		// Title
		title = new JLabel("Gebruikers\n");
		Font titleFont = new Font("Arial", Font.PLAIN, 32);
		title.setFont(titleFont);

		users = new ReadFromDatabase().readUsers();
		userTypes = new ReadFromDatabase().readUserTypes();
		fillUsersTable();
		addTable();

		// Bottom buttons
		JButton addUserButton = new JButton("Nieuwe gebruiker toevoegen");
		JButton viewUserButton = new JButton("Bekijk gegevens");
		JButton editUserButton = new JButton("Bewerken");
		JButton deleteUserButton = new JButton("Verwijderen");
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
				System.out.println("actionPerformed");
				new WindowManager(selectedUser, usersPanel).actionPerformed(e);
			}
		});

		editUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowManager(selectedUser, usersPanel).actionPerformed(e);

			}
		});
		
		
		deleteUserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ButtonListener(selectedUser, usersPanel).actionPerformed(e);;
				
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
		int rowIndex = usersTable.getSelectedRow();
		System.out.println(rowIndex);
		try {
			selectedUser = users.get(rowIndex);
		} catch (IndexOutOfBoundsException ioobe) {
			System.out.println("Row gone");
		}

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
			model = new DefaultTableModel(data, heading);
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
		System.out.println("refreshTable");
		users = new ReadFromDatabase().readUsers();
		fillUsersTable();
		usersTable.setModel(model);

	}

}
