package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

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
		String Naam, Login, Functie;
		
		Vector heading = new Vector(); 
		heading.addElement("Naam"); 
		heading.addElement("Login");
		heading.addElement("Functie");
		
		Vector data = new Vector();
	
	DatabaseConnection connection = new DatabaseConnection();
	String query = "SELECT u.firstname, u.Login, t.screenname FROM user u, usertype t WHERE u.type = t.id";
	
	ResultSet result = connection.ExecuteQuery(query);
	
	// loop voor de data te tonen
	while(result.next()) { 
	    Naam = result.getString("firstname"); 
	    Login = result.getString("login"); 
	    Functie = result.getString("screenname"); 

	    //create a row 
	    Vector tmp = new Vector(); 
	    tmp.addElement(Naam); 
	    tmp.addElement(Login); 
	    tmp.addElement(Functie); 

	     
	    //add to model 
	    data.addElement(tmp); 
	} 
	
	//data en heading in de tabel steken
	usersTable = new JTable(data, heading); 
	}  catch(Exception e){
		e.printStackTrace();
	}
}
	
	
}
