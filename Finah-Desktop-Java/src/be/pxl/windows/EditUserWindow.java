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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.xswingx.PromptSupport;
import org.jdesktop.xswingx.PromptSupport.FocusBehavior;

import be.pxl.json.UserDb;
import be.pxl.listeners.ButtonListener;
import be.pxl.objects.User;
import be.pxl.objects.UserType;
import be.pxl.settings.CheckInput;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.DateLabelFormatter;
import be.pxl.settings.SettingClass;

public class EditUserWindow extends JFrame {

	private static final long serialVersionUID = 1699334243189059182L;
	private JFrame frame;
	private User user;
	private DefaultComboBoxModel<String> modelComboBox;

	private JTextField firstNameTextField = new JTextField(20);
	private JTextField lastNameTextField = new JTextField(20);
	private JTextField loginTextField = new JTextField(20);
	private JTextField passwordTextField = new JTextField(20);
	private JTextField streetTextField = new JTextField(20);
	private JTextField townTextField = new JTextField(20);
	private JTextField zipCodeTextField = new JTextField(20);
	private JTextField emailTextField = new JTextField(20);
	private JComboBox<String> functionComboBox = new JComboBox<String>();
	private UtilDateModel model;

	private Properties configFile = new ConfigFile().getConfigFile();
	
	public EditUserWindow(User originalUser, UsersPanel usersPanel) {
		super("AppDevIT_03 - Admin");
		this.setLayout(new BorderLayout());
		frame = this;
		this.user = originalUser;
		// Top panel
		JPanel topPanel = new JPanel(new FlowLayout());
		JLabel titleLabel = new JLabel(configFile.getProperty("labelEditUser"));
		titleLabel.setFont(new SettingClass().getTitleFont());
		topPanel.add(titleLabel);

		// Datapanel
		JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		dataPanel.setPreferredSize(new Dimension(350, 500));

		JLabel firstNameLabel = new JLabel(configFile.getProperty("labelFirstName"));
		JLabel lastNameLabel = new JLabel(configFile.getProperty("labelLastName"));
		JLabel loginLabel = new JLabel(configFile.getProperty("labelLogin"));
		JLabel passwordLabel = new JLabel(configFile.getProperty("labelPassword"));
		JLabel streetLabel = new JLabel(configFile.getProperty("labelStreet"));
		JLabel townLabel = new JLabel(configFile.getProperty("labelTown"));
		JLabel zipCodeLabel = new JLabel(configFile.getProperty("labelZipCode"));
		JLabel emailLabel = new JLabel(configFile.getProperty("labelEmail"));
		JLabel birthDateLabel = new JLabel(configFile.getProperty("labelBirthDate"));
		JLabel functionLabel = new JLabel(configFile.getProperty("labelFunction"));

		firstNameLabel.setPreferredSize(new Dimension(90, 20));
		lastNameLabel.setPreferredSize(new Dimension(90, 20));
		loginLabel.setPreferredSize(new Dimension(90, 20));
		passwordLabel.setPreferredSize(new Dimension(90, 20));
		streetLabel.setPreferredSize(new Dimension(90, 20));
		townLabel.setPreferredSize(new Dimension(90, 20));
		zipCodeLabel.setPreferredSize(new Dimension(90, 20));
		emailLabel.setPreferredSize(new Dimension(90, 20));
		birthDateLabel.setPreferredSize(new Dimension(90, 20));
		functionLabel.setPreferredSize(new Dimension(90, 20));

		firstNameTextField = new JTextField(20);
		lastNameTextField = new JTextField(20);
		loginTextField = new JTextField(20);
		passwordTextField = new JTextField(20);
		streetTextField = new JTextField(20);
		townTextField = new JTextField(20);
		zipCodeTextField = new JTextField(20);
		emailTextField = new JTextField(20);
		functionComboBox = new JComboBox<String>();

		PromptSupport.setPrompt(configFile.getProperty("promptFirstName"), firstNameTextField);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, firstNameTextField);
		PromptSupport.setForeground(Color.GRAY, firstNameTextField);
		PromptSupport.setPrompt(configFile.getProperty("promptLastName"), lastNameTextField);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, lastNameTextField);
		PromptSupport.setForeground(Color.GRAY, lastNameTextField);
		PromptSupport.setPrompt(configFile.getProperty("promptLogin"), loginTextField);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, loginTextField);
		PromptSupport.setForeground(Color.GRAY, loginTextField);
		PromptSupport.setPrompt(configFile.getProperty("promptPassword"), passwordTextField);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, passwordTextField);
		PromptSupport.setForeground(Color.GRAY, passwordTextField);
		PromptSupport.setPrompt(configFile.getProperty("promptStreet"), streetTextField);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, streetTextField);
		PromptSupport.setForeground(Color.GRAY, streetTextField);
		PromptSupport.setPrompt(configFile.getProperty("promptTown"), townTextField);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, townTextField);
		PromptSupport.setForeground(Color.GRAY, townTextField);
		PromptSupport.setPrompt(configFile.getProperty("promptZipCode"), zipCodeTextField);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, zipCodeTextField);
		PromptSupport.setForeground(Color.GRAY, zipCodeTextField);
		PromptSupport.setPrompt(configFile.getProperty("promptMail"), emailTextField);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, emailTextField);;
		PromptSupport.setForeground(Color.GRAY, emailTextField);
		
		fillComboBox();
		functionComboBox.setModel(modelComboBox);

		// JDatePicker
		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());

		fillFields();

		dataPanel.add(firstNameLabel);
		dataPanel.add(firstNameTextField);
		dataPanel.add(lastNameLabel);
		dataPanel.add(lastNameTextField);
		dataPanel.add(loginLabel);
		dataPanel.add(loginTextField);
		dataPanel.add(passwordLabel);
		dataPanel.add(passwordTextField);
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
		dataPanel.add(functionComboBox);

		// ButtonPanel
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton saveButton = new JButton(configFile.getProperty("btnSave"));
		JButton resetButton = new JButton(configFile.getProperty("btnReset"));
		JButton cancelButton = new JButton(configFile.getProperty("btnCancel"));

		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fillFields();

			}
		});

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (new CheckInput().checkInputAddUser(firstNameTextField, lastNameTextField,
						loginTextField, passwordTextField, emailTextField,
						streetTextField, townTextField, datePicker,
						zipCodeTextField)) {
					Date selectedDate = (Date) datePicker.getModel().getValue();
					User user = new User();
					user.setId(originalUser.getId());
					user.setFirstname(firstNameTextField.getText());
					user.setLastname(lastNameTextField.getText());
					user.setLogin(loginTextField.getText());
					user.setPassword(passwordTextField.getText());
					user.setEmail(emailTextField.getText());
					user.setStreet(streetTextField.getText());
					user.setTown(townTextField.getText());
					user.setZipCode(Integer.parseInt(zipCodeTextField.getText()));
					user.setBirthDate(selectedDate);
					user.setType(functionComboBox.getSelectedIndex() + 1);
					new UserDb().updateUser(user);
					usersPanel.refreshTable();
					frame.dispose();
				}

			}
		});
		cancelButton.addActionListener(new ButtonListener(this));

		buttonPanel.add(saveButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(cancelButton);

		// rightPanel
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel picturePanel = new JPanel(new FlowLayout());
		JPanel pictureButtonPanel = new JPanel(new FlowLayout());

		JLabel pictureLabel = setPicture();
		JButton uploadButton = new JButton(configFile.getProperty("btnUploadPhoto"));

		pictureButtonPanel.add(uploadButton);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void fillComboBox() {

		List<UserType> userType = new UserDb().readUserTypes();

		ArrayList<String> typeNames = new ArrayList<String>();

		for (UserType type : userType) {
			typeNames.add(type.getTypeName());
		}

		modelComboBox = new DefaultComboBoxModel(typeNames.toArray());

	}

	private void fillFields() {
		firstNameTextField.setText(user.getFirstname());
		lastNameTextField.setText(user.getLastname());
		loginTextField.setText(user.getLogin());
		passwordTextField.setText(user.getPassword());
		streetTextField.setText(user.getStreet());
		townTextField.setText(user.getTown());
		zipCodeTextField.setText(String.valueOf(user.getZipCode()));
		emailTextField.setText(user.getEmail());
		functionComboBox.setSelectedIndex(user.getType() - 1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(user.getBirthDate());
		model.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH));
		model.setSelected(true);

	}

}
