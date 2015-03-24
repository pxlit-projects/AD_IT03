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

import be.pxl.database.DatabaseConnection;
import be.pxl.listeners.WindowListener;
import be.pxl.objects.User;
import be.pxl.objects.UserType;

public class UsersPanel extends JPanel{
	
	private static final long serialVersionUID = 5054771516129361055L;
	private JLabel title;
	private JTable usersTable;
	private List<User> users = new ArrayList<User>();
	private User selectedUser = new User();

	public UsersPanel() {
		readAllUsers();
		
		//Frame Layout
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		//Title
		title = new JLabel("Gebruikers\n");
		Font titleFont = new Font("Arial", Font.PLAIN, 32);
		title.setFont(titleFont); 
		
		fillUsersTable();
		ScrollPane usersTableScroll = new ScrollPane();
		JPanel usersScrollPanel = new JPanel(new BorderLayout());
		usersTableScroll.add(usersScrollPanel);
		usersScrollPanel.add(usersTable.getTableHeader(), BorderLayout.NORTH);
		usersScrollPanel.add(usersTable, BorderLayout.CENTER);
		
		//Bottom buttons
		JButton addUserButton = new JButton("Nieuwe gebruiker toevoegen");
		JButton viewUserButton = new JButton("Bekijk gegevens");
		JButton editUserButton = new JButton("Bewerken");
		JButton deleteUserButton = new JButton ("Verwijderen");
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(addUserButton);
		buttonPanel.add(viewUserButton);
		buttonPanel.add(editUserButton);
		buttonPanel.add(deleteUserButton);
		
		//Buttons add actions
		addUserButton.addActionListener(new WindowListener());
		editUserButton.addActionListener(new WindowListener());
		viewUserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("actionPerformed");
				new WindowListener(selectedUser).actionPerformed(e);;
			}
		});
		
		editUserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowListener(selectedUser).actionPerformed(e);
				
			}
		});
		
		usersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				getSelectedUser();
			}
		});
		
		//Add to frame
		this.add(title, BorderLayout.NORTH);
		this.add(usersTableScroll, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void getSelectedUser() {
		int rowIndex = usersTable.getSelectedRow();
		System.out.println(rowIndex);
		selectedUser = users.get(rowIndex);
	}
	
	public void fillUsersTable(){
		try{

			Vector<Vector<String>> data = new Vector<Vector<String>>();
			for (int i = 0; i < users.size(); i++) {
				Vector<String> tmp = new Vector<String>(); 
			    tmp.addElement(users.get(i).getFirstname()); 
			    tmp.addElement(users.get(i).getScreenName()); 
			    tmp.addElement(users.get(i).getType().getScreenName()); 
		
			     
			    //add to model 
			    data.addElement(tmp);
			}
			
			Vector<String> heading = new Vector<String>(); 
			heading.addElement("Naam"); 
			heading.addElement("Login");
			heading.addElement("Functie");
			
			
			//data en heading in de tabel steken
			usersTable = new JTable(data, heading); 
		}  catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	private void readAllUsers() {
		try {
			DatabaseConnection connection = new DatabaseConnection();
			String query = ""
					+ "SELECT u.id, u.login, u.firstname, u.lastname, u.password, u.email, t.id, t.screenname, t.description "
					+ "FROM user u, usertype t "
					+ "WHERE u.type = t.id";
			ResultSet result = connection.ExecuteQuery(query);
			
			while(result.next()) {
				int id = Integer.parseInt(result.getString("id"));
				String login = result.getString("login");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String password = result.getString("password");
				String email = result.getString("email");
				int typeId = Integer.parseInt(result.getString("t.id"));
				String screenname = result.getString("t.screenname");
				String description = result.getString("t.description");
				UserType userType = new UserType(typeId, screenname, description);
				users.add(new User(id, firstname, lastname, login, password, email, userType));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	
}
