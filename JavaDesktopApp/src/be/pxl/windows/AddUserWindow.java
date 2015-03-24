package be.pxl.windows;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import be.pxl.database.DatabaseConnection;
import be.pxl.listeners.ButtonListener;
import be.pxl.settings.DateLabelFormatter;
import be.pxl.settings.SettingClass;

public class AddUserWindow extends JFrame {

	private static final long serialVersionUID = 7793413020042788948L;
	
	private UsersPanel usersPanel;

	public AddUserWindow(UsersPanel usersPanel) {
		
		this.setLayout(new BorderLayout());
		this.usersPanel = usersPanel;
		//Top panel
		JPanel topPanel = new JPanel(new FlowLayout());
		JLabel titleLabel = new JLabel("Nieuwe gebruiker toevoegen");
		titleLabel.setFont(new SettingClass().getTitleFont());
		topPanel.add(titleLabel);
		
		//Datapanel
		JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		dataPanel.setPreferredSize(new Dimension(350,500));
		
		JLabel nameLabel = new JLabel("Naam");
		JLabel loginLabel = new JLabel("Login");
		JLabel streetLabel = new JLabel("Straat");
		JLabel townLabel = new JLabel("Gemeente");
		JLabel zipCodeLabel = new JLabel("Postcode");
		JLabel emailLabel = new JLabel("E-mail");
		JLabel birthDateLabel = new JLabel("Geboortedatum");
		JLabel functionLabel = new JLabel("Functie");
		
		nameLabel.setPreferredSize(new Dimension(90,20));
		loginLabel.setPreferredSize(new Dimension(90,20));
		streetLabel.setPreferredSize(new Dimension(90,20));
		townLabel.setPreferredSize(new Dimension(90,20));
		zipCodeLabel.setPreferredSize(new Dimension(90,20));
		emailLabel.setPreferredSize(new Dimension(90,20));
		birthDateLabel.setPreferredSize(new Dimension(90,20));
		functionLabel.setPreferredSize(new Dimension(90,20));
		
		JTextField nameTextField = new JTextField(20);
		JTextField loginTextField = new JTextField(20);
		JTextField streetTextField = new JTextField(20);
		JTextField townTextField = new JTextField(20);
		JTextField zipCodeTextField = new JTextField(20);
		JTextField emailTextField = new JTextField(20);
		JTextField functionTextField = new JTextField(20);
		
		//JDatePicker
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("Text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		dataPanel.add(nameLabel);
		dataPanel.add(nameTextField);
		dataPanel.add(loginLabel);
		dataPanel.add(loginTextField);
		dataPanel.add(streetLabel);
		dataPanel.add(streetTextField);
		dataPanel.add(townLabel);
		dataPanel.add(townTextField);
		dataPanel.add(zipCodeLabel);
		dataPanel.add(zipCodeTextField);
		dataPanel.add(emailLabel);
		dataPanel.add(emailTextField);
		dataPanel.add(birthDateLabel);
		dataPanel.add(datePicker);
		dataPanel.add(functionLabel);
		dataPanel.add(functionTextField);
		
		//ButtonPanel
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton createButton = new JButton("Aanmaken");
		JButton resetButton = new JButton("Reset");
		JButton cancelButton = new JButton("Annuleren");
		
		createButton.addActionListener(new ActionListener() {
			
			Date selectedDate = (Date) datePicker.getModel().getValue();
			
			@Override
			public void actionPerformed(ActionEvent e) { 
		        AddUser(nameTextField.getText(), loginTextField.getText(),streetTextField.getText(), townTextField.getText(),zipCodeTextField.getText(),emailTextField.getText(), selectedDate, functionTextField.getText());
		    } 
		});
		cancelButton.addActionListener(new ButtonListener(this));
		
		buttonPanel.add(createButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(cancelButton);
		
		//rightPanel
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel picturePanel = new JPanel(new FlowLayout());
		JPanel pictureButtonPanel = new JPanel(new FlowLayout());
		
		JLabel pictureLabel = setPicture();
		JButton uploadButton = new JButton("Upload foto");
		
		pictureButtonPanel.add(uploadButton);
		picturePanel.add(pictureLabel);
		rightPanel.add(picturePanel, BorderLayout.CENTER);
		rightPanel.add(pictureButtonPanel, BorderLayout.SOUTH);
		
		
		//add to frame
		this.add(topPanel, BorderLayout.NORTH);
		this.add(dataPanel, BorderLayout.WEST);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(rightPanel, BorderLayout.EAST);
	}
	
	private JLabel setPicture() {
		JLabel label = new JLabel();
		try {
			String path = "Images/profile.png";
			BufferedImage image = ImageIO.read(new File(path));
			BufferedImage newImage = resizeImageWithHint(image, image.getType());
			label = new JLabel(new ImageIcon(newImage));
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
		return label;
	}
	
	private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){
		 
		BufferedImage resizedImage = new BufferedImage(200, 200, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, 200, 200, null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);
	 
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	 
		return resizedImage;
    }

	public void AddUser(String nameTextField, String loginTextField, String streetTextField,String townTextField, String zipCodeTextField,String emailTextField,Date selectedDate,String functionTextField){
		try {
			
		
		DatabaseConnection connectie = new DatabaseConnection();
		String url = connectie.getConnectionURL();
		
		Connection conn = DriverManager.getConnection(url, "luke", "lukeluke");
		java.sql.Statement st = conn.createStatement();
		
		String query = "INSERT INTO user (login, firstname,lastname, password, email, type) VALUES ('" + loginTextField +"', '" + nameTextField + "', " + "'testlastname', 'testpassword','" + emailTextField +"', '" + functionTextField+"')"; 

		
		st.execute(query);
		
		usersPanel.refreshTable();
//		usersPanel.repaint();
		//UsersPanel users = new UsersPanel();
		this.dispose();
		//Aanpassen!!!!
	
		
	}   catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
}
}
	
}
