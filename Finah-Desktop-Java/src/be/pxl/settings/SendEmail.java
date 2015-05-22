package be.pxl.settings;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	private Properties configFile = new ConfigFile().getConfigFile();
	
	public SendEmail(String emailadres, boolean client, String hash) {
		
		// Recipient's email ID needs to be mentioned.
		String to = emailadres;// change accordingly

		// Sender's email ID needs to be mentioned
		String from = configFile.getProperty("emailFrom");// change accordingly
		final String username = configFile.getProperty("emailUserName");// change accordingly
		final String password = configFile.getProperty("emailPassword");// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = configFile.getProperty("emailHost");

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(configFile.getProperty("subject"));

			// Now set the actual message
			String mess = configFile.getProperty("mess");
			String link = configFile.getProperty("emailTxt");
			
			// http://127.0.0.1/survey/1/4/7ffc4632a40ed69d2d643ef520bd08ef22d67e86/ 
			if (client) {
				link += "4/";
			} else {
				link += "3/";
			}
			link += hash;
			message.setText(mess + link);

			// Send message
			Transport.send(message);


		} catch (MessagingException e) {
			
		}
	}
	

}
