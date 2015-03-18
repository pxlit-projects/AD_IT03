package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginWindow extends JFrame {
	public LoginWindow() {
		super("AD_IT03 - Login");
		setLayout(new BorderLayout());
		layoutWindow();
	}
	
	private void layoutWindow() {
		setLogo();
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField usernameField = new JTextField(20);
		JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField passwordField = new JTextField(20);
		JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel usernameLabel = new JLabel("Login");
		JLabel passwordLabel = new JLabel("Wachtwoord");
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		
		
		
		this.add(rightPanel, BorderLayout.CENTER);
		rightPanel.add(usernamePanel);
		rightPanel.add(passwordPanel);
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