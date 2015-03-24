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
import be.pxl.listeners.WindowManager;
import be.pxl.objects.User;
import be.pxl.objects.UserType;

public class UsersPanel extends JPanel {

	private static final long serialVersionUID = 5054771516129361055L;
	private JLabel title;
	private JTable usersTable;
	private List<User> users = new ArrayList<User>();
	private User selectedUser = new User();
	private JPanel usersScrollPanel;
	private ScrollPane usersTableScroll;
	private DefaultTableModel model;
	private Vector<Vector<String>> data;

	public UsersPanel() {

		// Frame Layout
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Title
		title = new JLabel("Gebruikers\n");
		Font titleFont = new Font("Arial", Font.PLAIN, 32);
		title.setFont(titleFont);

		readAllUsers();
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
				new WindowManager(selectedUser).actionPerformed(e);
				;
			}
		});

		editUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowManager(selectedUser).actionPerformed(e);

			}
		});

		deleteUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				deleteUser();

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

	private void deleteUser() {
		getSelectedUser();
		int id = selectedUser.getId();
		DatabaseConnection connection = null;

		try {

			connection = new DatabaseConnection();
			String query = "" + "DELETE FROM user WHERE id = "
					+ String.valueOf(id);
			connection.ExecuteUpdate(query);
			refreshTable();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.deleteConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
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
				tmp.addElement(users.get(i).getType().getTypeName());

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
		readAllUsers();
		fillUsersTable();
		usersTable.setModel(model);

	}

	private void readAllUsers() {
		DatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection();
			String query = ""
					+ "SELECT u.id, u.login, u.firstname, u.lastname, u.password, u.email, t.id, t.screenname, t.description "
					+ "FROM user u, usertype t " + "WHERE u.type = t.id";
			ResultSet result = connection.ExecuteQuery(query);
			users = new ArrayList<User>();
			while (result.next()) {
				int id = Integer.parseInt(result.getString("id"));
				String login = result.getString("login");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String password = result.getString("password");
				String email = result.getString("email");
				int typeId = Integer.parseInt(result.getString("t.id"));
				String screenname = result.getString("t.screenname");
				String description = result.getString("t.description");
				UserType userType = new UserType(typeId, screenname,
						description);
				users.add(new User(id, firstname, lastname, login, password,
						email, userType));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.deleteConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
