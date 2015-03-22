package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import be.pxl.database.DatabaseConnection;

public class QuestionnairePanel extends JPanel {

	private static final long serialVersionUID = -1471537834378662928L;
	private JLabel title;
	private JTable QuestionTable;
	private String[][] data;
	
	
	
	public QuestionnairePanel() {
		
		
		//Frame Layout
				this.setLayout(new BorderLayout());
				this.setBorder(new EmptyBorder(10, 10, 10, 10) );
				
				//Title
				title = new JLabel("Gebruikers\n");
				Font titleFont = new Font("Arial", Font.PLAIN, 32);
				title.setFont(titleFont); 
				
				fillQuestionTable();
				ScrollPane usersTableScroll = new ScrollPane();
				JPanel usersScrollPanel = new JPanel(new BorderLayout());
				usersTableScroll.add(usersScrollPanel);
				usersScrollPanel.add(QuestionTable.getTableHeader(), BorderLayout.NORTH);
				usersScrollPanel.add(QuestionTable, BorderLayout.CENTER);
				
				
				//Bottom buttons
				JButton viewUserButton = new JButton("Bekijk gegevens");
				JButton editUserButton = new JButton("Bewerken");
				JButton deleteUserButton = new JButton ("Verwijderen");
				JPanel buttonPanel = new JPanel(new FlowLayout());
				buttonPanel.add(viewUserButton);
				buttonPanel.add(editUserButton);
				buttonPanel.add(deleteUserButton);
				
				
				//Add to frame
				this.add(title, BorderLayout.NORTH);
				this.add(usersTableScroll, BorderLayout.CENTER);
				this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	
	
	
	
	
	
	public void fillQuestionTable(){
		try{
			String Title, Description, Theme, Choice;
			
			Vector heading = new Vector(); 
			heading.addElement("Title"); 
			heading.addElement("Description");
			heading.addElement("Theme");
			heading.addElement("Choice");
			
			Vector data = new Vector();
		
		DatabaseConnection connection = new DatabaseConnection();
		String query = "SELECT questionlist.list, question.choice, question.description, question.theme, question.title, theme.description, theme.title, user.type, user.firstname, usertype.screenname FROM `questionlist` JOIN `question` on questionlist.question = question.id JOIN `theme` on question.theme = theme.id JOIN `user` on questionlist.user = user.id JOIN `usertype` on user.type = usertype.id WHERE theme.title = 'Leren en toepassen van kennis' AND questionlist.list = 1;";
		
		ResultSet result = connection.ExecuteQuery(query);
		
		// loop voor de data te tonen
		while(result.next()) { 
		    Title = result.getString("title"); 
		    Description = result.getString("description"); 
		    Theme = result.getString("theme"); 
		    Choice = result.getString("choice");

		    //create a row 
		    Vector tmp = new Vector(); 
		    tmp.addElement(Title); 
		    tmp.addElement(Description); 
		    tmp.addElement(Theme);
		    tmp.addElement(Choice);

		     
		    //add to model 
		    data.addElement(tmp); 
		} 
		
		//data en heading in de tabel steken
		QuestionTable = new JTable(data, heading); 
		}  catch(Exception e){
			e.printStackTrace();
		}

}
}
