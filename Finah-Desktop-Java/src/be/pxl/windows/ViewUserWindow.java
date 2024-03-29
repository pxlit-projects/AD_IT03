package be.pxl.windows;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.pxl.json.UserDb;
import be.pxl.listeners.ButtonListener;
import be.pxl.listeners.WindowManager;
import be.pxl.objects.User;
import be.pxl.objects.UserType;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SettingClass;

public class ViewUserWindow extends JFrame {

	private static final long serialVersionUID = 2441268389721501033L;

	private User user;
	private UsersPanel usersPanel;
	private ViewUserWindow viewUserWindow;
	private List<UserType> userTypes = new UserDb().readUserTypes();
	private Properties configFile = new ConfigFile().getConfigFile();
	public ViewUserWindow(User user, UsersPanel usersPanel) {
		this.user = user;
		this.usersPanel = usersPanel;
		windowLayout();
	}

	private void windowLayout() {
		this.setLayout(new BorderLayout());
		viewUserWindow = this;
		// Top panel
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.setBackground(Color.WHITE);
		JLabel titleLabel = new JLabel(configFile.getProperty("TitelViewUser"));
		titleLabel.setFont(new SettingClass().getTitleFont());
		topPanel.add(titleLabel);

		// Datapanel
		JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		dataPanel.setBackground(Color.WHITE);
		dataPanel.setPreferredSize(new Dimension(350, 500));

		JLabel firstnameLabel = new JLabel(configFile.getProperty("labelFirstName"));
		JLabel lastnameLabel = new JLabel(configFile.getProperty("labelLastName"));
		JLabel loginLabel = new JLabel(configFile.getProperty("labelLogin"));
		JLabel streetLabel = new JLabel(configFile.getProperty("labelStreet"));
		JLabel townLabel = new JLabel(configFile.getProperty("labelTown"));
		JLabel zipCodeLabel = new JLabel(configFile.getProperty("labelZipCode"));
		JLabel emailLabel = new JLabel(configFile.getProperty("labelEmail"));
		JLabel birthDateLabel = new JLabel(configFile.getProperty("labelBirthDate"));
		JLabel functionLabel = new JLabel(configFile.getProperty("labelFunction"));

		firstnameLabel.setPreferredSize(new Dimension(90, 20));
		lastnameLabel.setPreferredSize(new Dimension(90, 20));
		loginLabel.setPreferredSize(new Dimension(90, 20));
		streetLabel.setPreferredSize(new Dimension(90, 20));
		townLabel.setPreferredSize(new Dimension(90, 20));
		zipCodeLabel.setPreferredSize(new Dimension(90, 20));
		emailLabel.setPreferredSize(new Dimension(90, 20));
		birthDateLabel.setPreferredSize(new Dimension(90, 20));
		functionLabel.setPreferredSize(new Dimension(90, 20));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(user.getBirthDate());
		String date = cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR);

		JLabel firstnameAnswerLabel = new JLabel(user.getFirstname());
		JLabel lastnameAnswerLabel = new JLabel(user.getLastname());
		JLabel loginAnswerLabel = new JLabel(user.getLogin());
		JLabel streetAnswerLabel = new JLabel(user.getStreet());
		JLabel townAnswerLabel = new JLabel(user.getTown());
		JLabel zipCodeAnswerLabel = new JLabel(
				String.valueOf(user.getZipCode()));
		JLabel emailAnswerLabel = new JLabel(user.getEmail());
		JLabel birthDateAnswerLabel = new JLabel(date);
		JLabel functionAnswerLabel = new JLabel(userTypes.get(user.getType()-1).getTypeName());

		firstnameAnswerLabel.setPreferredSize(new Dimension(250, 20));
		lastnameAnswerLabel.setPreferredSize(new Dimension(250, 20));
		loginAnswerLabel.setPreferredSize(new Dimension(250, 20));
		streetAnswerLabel.setPreferredSize(new Dimension(250, 20));
		townAnswerLabel.setPreferredSize(new Dimension(250, 20));
		zipCodeAnswerLabel.setPreferredSize(new Dimension(250, 20));
		emailAnswerLabel.setPreferredSize(new Dimension(250, 20));
		birthDateAnswerLabel.setPreferredSize(new Dimension(250, 20));
		functionAnswerLabel.setPreferredSize(new Dimension(250, 20));

		dataPanel.add(firstnameLabel);
		dataPanel.add(firstnameAnswerLabel);
		dataPanel.add(lastnameLabel);
		dataPanel.add(lastnameAnswerLabel);
		dataPanel.add(loginLabel);
		dataPanel.add(loginAnswerLabel);
		dataPanel.add(streetLabel);
		dataPanel.add(streetAnswerLabel);
		dataPanel.add(townLabel);
		dataPanel.add(townAnswerLabel);
		dataPanel.add(zipCodeLabel);
		dataPanel.add(zipCodeAnswerLabel);
		dataPanel.add(emailLabel);
		dataPanel.add(emailAnswerLabel);
		dataPanel.add(birthDateLabel);
		dataPanel.add(birthDateAnswerLabel);
		dataPanel.add(functionLabel);
		dataPanel.add(functionAnswerLabel);

		// ButtonPanel
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBackground(Color.WHITE);
		JButton editButton = new JButton(configFile.getProperty("btnEditUser"));
		JButton cancelButton = new JButton(configFile.getProperty("btnCancel"));

		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new WindowManager(user, usersPanel).actionPerformed(e);
				viewUserWindow.dispose();
			}
		});
		cancelButton.addActionListener(new ButtonListener(this));

		buttonPanel.add(editButton);
		buttonPanel.add(cancelButton);

		// rightPanel
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel picturePanel = new JPanel(new FlowLayout());
		JPanel pictureButtonPanel = new JPanel(new FlowLayout());
		rightPanel.setBackground(Color.WHITE);
		pictureButtonPanel.setBackground(Color.WHITE);
		picturePanel.setBackground(Color.WHITE);

		JLabel pictureLabel = setPicture();

		picturePanel.add(pictureLabel);
		rightPanel.add(picturePanel, BorderLayout.CENTER);
		rightPanel.add(pictureButtonPanel, BorderLayout.SOUTH);

		// add to frame
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

	private static BufferedImage resizeImageWithHint(
			BufferedImage originalImage, int type) {

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

}
