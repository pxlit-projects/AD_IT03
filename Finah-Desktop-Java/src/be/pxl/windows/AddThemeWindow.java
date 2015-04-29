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
		
		JPanel panel = new JPanel(new GridLayout(3, 1));
		
		
		JPanel panel2 = new JPanel(new FlowLayout());
		JLabel label = new JLabel("Hoeveel thema's wil je toevoegen?");
		panel2.add(label);
		
		JPanel panel3 = new JPanel(new FlowLayout());
		String[] input = {"1","2","3","4","5","6","7","8","9","10"};
		JComboBox<String> amount = new JComboBox<>(input);
		panel3.add(amount);
		
		JPanel panel4 = new JPanel(new FlowLayout());
		JButton addButton = new JButton("Voeg vragen toe");
		panel4.add(addButton);
		
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		
		this.add(panel);
		
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowManager((amount.getSelectedIndex() + 1)).actionPerformed(e);

			}
		});
		
		
	}
}
