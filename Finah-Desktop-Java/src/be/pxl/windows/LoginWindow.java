package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import be.pxl.listeners.WindowManager;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = -3648674988481735332L;

	public LoginWindow() {
		super("AD_IT03 - Login");
		setLayout(new BorderLayout());
		layoutWindow();
	}
	
	private void layoutWindow() {
		setLogo();
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField usernameField = new JTextField(20);
		JTextField passwordField = new JTextField(20);
		JLabel usernameLabel = new JLabel("Login");
		JLabel passwordLabel = new JLabel("Wachtwoord");
		JButton loginButton = new JButton("Login");
		
		usernameLabel.setPreferredSize(new Dimension(80 , 20));
		passwordLabel.setPreferredSize(new Dimension(80 , 20));
		usernamePanel.setBorder(new EmptyBorder(60, 0, 0, 0));
		
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		loginButton.addActionListener(new WindowManager(this));
		
		
		this.add(rightPanel, BorderLayout.CENTER);
		rightPanel.add(usernamePanel);
		rightPanel.add(passwordPanel);
		rightPanel.add(loginButton);
	}
	
	private void setLogo() {
		try {
			String path = "Images/logo.png";
			Image image = ImageIO.read(new File(path));
			JLabel label = new JLabel(new ImageIcon(image));
			this.add(label, BorderLayout.WEST);
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
}