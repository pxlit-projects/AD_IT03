package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.xswingx.PromptSupport;
import org.jdesktop.xswingx.PromptSupport.FocusBehavior;

import be.pxl.json.ThemeDb;
import be.pxl.listeners.WindowManager;
import be.pxl.objects.Theme;
import be.pxl.settings.ConfigFile;

public class AddThemeWindow extends JFrame {
	
	private JFrame frame;
	private Properties configFile = new ConfigFile().getConfigFile();
	
	public AddThemeWindow(){
		this.setLayout(new BorderLayout());
		
		frame = this;
		
		JPanel panel = new JPanel(new GridLayout(5, 1));
		
		JPanel panel1 = new JPanel(new FlowLayout());
		JLabel labelTheme = new JLabel(configFile.getProperty("labelTheme"));
		
		panel1.add(labelTheme);
		
		JPanel panel2 = new JPanel(new FlowLayout());
		JTextField textTheme = new JTextField("",20);
		
		PromptSupport.setPrompt(configFile.getProperty("promptTheme"), textTheme);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, textTheme);
		PromptSupport.setForeground(Color.GRAY, textTheme);
		panel2.add(textTheme);
		
		
		JPanel panel3 = new JPanel(new FlowLayout());
		JLabel label = new JLabel(configFile.getProperty("labelAddQuestion"));
		panel3.add(label);
		
		JPanel panel4 = new JPanel(new FlowLayout());
		String[] input = {"1","2","3","4","5","6","7","8","9","10"};
		JComboBox<String> amount = new JComboBox<>(input);
		panel4.add(amount);
		
		JPanel panel5 = new JPanel(new FlowLayout());
		JButton addButton = new JButton(configFile.getProperty("btnAddQuestion"));
		panel5.add(addButton);
		
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		
		this.add(panel);

		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Theme theme = new Theme();
				theme.setTitle(textTheme.getText());
				List<Theme> themes = new ThemeDb().readThemes();
				boolean exists = false;
				for (Theme theme2 : themes) {
					if (theme.getTitle().equalsIgnoreCase(theme2.getTitle())) {
						exists = true;
					}
				}
				if (!exists) {
					new ThemeDb().addTheme(theme);
					theme = new ThemeDb().readThemeByTitle(textTheme.getText());
					new WindowManager((amount.getSelectedIndex() + 1), theme).actionPerformed(e);
					frame.dispose();
				} else {
					
				}
				

			}
		});
		
		
	}
}
