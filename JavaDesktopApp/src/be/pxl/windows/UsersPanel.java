package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import be.pxl.database.DatabaseConnection;
import be.pxl.listeners.WindowListener;
import be.pxl.objects.User;
import be.pxl.objects.UserType;

public class UsersPanel extends JPanel{
	
	private static final long serialVersionUID = 5054771516129361055L;
	private JLabel title;
	private JTable usersTable;
	private String[][] data;

	public UsersPanel() {
		
		//Frame Layout
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		//Title
		title = new JLabel("Gebruikers\n");
		Font titleFont = new Font("Arial", Font.PLAIN, 32);
		title.setFont(titleFont); 
		
		//Table
		//addData();
		//String[] columnNames = {"Naam", "Login", "Functie"};
		//usersTable = new JTable(data, columnNames);
		
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
		viewUserButton.addActionListener(new WindowListener(new User(1, "Pieter", "Switten", "PSwitten", "Switten", "pieterswitten@gmail.com", new UserType(1, "admin", "dit is de baas"))));
		editUserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("boe");
				
			}
		});
		
		//Add to frame
		this.add(title, BorderLayout.NORTH);
		this.add(usersTableScroll, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void addData() {
		String[][] tempData = {
				{"Pieter Switten", "PSwitten", "Admin"}
		};
		data = tempData;
	}
	
public void fillUsersTable(){
	try{
		
	DatabaseConnection connection = new DatabaseConnection();
	
	String query = "SELECT firstname, Login, type FROM user";
	
	ResultSet result = connection.ExecuteQuery(query);
	
	String[] columnNames = {"Naam", "Login", "Functie"}; 
	String n = "",l = "", f="";
	while(result.next()) { 
	    n = result.getString("firstname");
	    l = result.getString("login");
	    f = result.getString("type");
	    Object[][]data = {{n,l,f}}; 
	    usersTable = new JTable(data, columnNames);

	}
	}  catch(Exception e){
		e.printStackTrace();
	}
}
	
	
}
