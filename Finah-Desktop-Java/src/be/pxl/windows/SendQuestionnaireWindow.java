package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.pxl.json.HashesDB;
import be.pxl.listeners.ButtonListener;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SendEmail;
import be.pxl.settings.SettingClass;
public class SendQuestionnaireWindow extends JFrame {

	private static final long serialVersionUID = -5966739664048277914L;
	private Properties configFile = new ConfigFile().getConfigFile();
	JTextField clientEmailTextField;
	JTextField caregiverEmailTextField;
	JFrame frame;
	
	public SendQuestionnaireWindow() {
		frame = this;
		this.setLayout(new BorderLayout());
		topPanelLayout();
		centerPanelLayout();
		bottemPanelLayout();
	}
	
	private void topPanelLayout() {
		JPanel topPanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel(configFile.getProperty("labelSendQuestionList"));
		title.setFont(new SettingClass().getTitleFont());
		
		topPanel.add(title);
		
		this.add(topPanel, BorderLayout.NORTH);
	}
	
	private void centerPanelLayout() {
		JPanel centerPanel = new JPanel(new FlowLayout());
		
		JLabel clientEmailLabel = new JLabel(configFile.getProperty("labelClientMail"));
		JLabel caregiverEmailLabel = new JLabel(configFile.getProperty("labelCareGiverMail"));
		
		clientEmailLabel.setPreferredSize(new Dimension(150, 20));
		caregiverEmailLabel.setPreferredSize(new Dimension(150, 20));
		
		clientEmailTextField = new JTextField(20);
		caregiverEmailTextField = new JTextField(20);
		
		centerPanel.add(clientEmailLabel);
		centerPanel.add(clientEmailTextField);
		centerPanel.add(caregiverEmailLabel);
		centerPanel.add(caregiverEmailTextField);
		
		this.add(centerPanel, BorderLayout.CENTER);
	}
	
	private void bottemPanelLayout() {
		JPanel bottemPanel = new JPanel(new FlowLayout());
		JButton sendButton = new JButton(configFile.getProperty("btnSend"));
		JButton cancelButton = new JButton(configFile.getProperty("btnCancel"));
		
		cancelButton.addActionListener(new ButtonListener(this));
		sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String hash = encrypt(String.valueOf(Math.random()));
				new SendEmail(clientEmailTextField.getText(), true, hash);
				new SendEmail(caregiverEmailTextField.getText(), false, hash);
				new HashesDB().addHash(hash);
				frame.dispose();
				
			}
		});
		
		bottemPanel.add(sendButton);
		bottemPanel.add(cancelButton);
		this.add(bottemPanel, BorderLayout.SOUTH);
	}
	
	private String encrypt(String x) {
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
	        md.update(x.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	       
	        //convert the byte to hex format for sha1
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

}
