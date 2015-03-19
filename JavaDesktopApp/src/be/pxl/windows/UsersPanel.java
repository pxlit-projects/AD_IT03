package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import be.pxl.listeners.WindowListener;

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
		addData();
		String[] columnNames = {"Naam", "Login", "Functie"};
		usersTable = new JTable(data, columnNames);
		ScrollPane usersTableScroll = new ScrollPane();
		usersTableScroll.add(usersTable);
		
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
		viewUserButton.addActionListener(new WindowListener());
		
		//Add to frame
		this.add(title, BorderLayout.NORTH);
		this.add(usersTableScroll, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private void addData() {
		String[][] tempData = {{"Pieter Switten", "PSwitten", "Admin"}};
		data = tempData;
	}
	

}
