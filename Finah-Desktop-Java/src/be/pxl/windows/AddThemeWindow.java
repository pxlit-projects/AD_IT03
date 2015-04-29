package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.pxl.listeners.WindowManager;

public class AddThemeWindow extends JFrame {

	public AddThemeWindow(){
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new GridLayout(5, 1));
		
		JPanel panel1 = new JPanel(new FlowLayout());
		JLabel labelTheme = new JLabel("Welk thema wil je toevoegen?");
		
		panel1.add(labelTheme);
		
		JPanel panel2 = new JPanel(new FlowLayout());
		JTextField textTheme = new JTextField("",20);
		
		textTheme.setToolTipText("geef hier het thema in");
		panel2.add(textTheme);
		
		
		JPanel panel3 = new JPanel(new FlowLayout());
		JLabel label = new JLabel("Hoeveel vragen wil je toevoegen?");
		panel3.add(label);
		
		JPanel panel4 = new JPanel(new FlowLayout());
		String[] input = {"1","2","3","4","5","6","7","8","9","10"};
		JComboBox<String> amount = new JComboBox<>(input);
		panel4.add(amount);
		
		JPanel panel5 = new JPanel(new FlowLayout());
		JButton addButton = new JButton("Voeg vragen toe");
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
				new WindowManager((amount.getSelectedIndex() + 1), textTheme.getText()).actionPerformed(e);

			}
		});
		
		
	}
}
