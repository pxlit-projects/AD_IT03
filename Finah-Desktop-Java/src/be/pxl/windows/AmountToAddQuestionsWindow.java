package be.pxl.windows;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.pxl.listeners.WindowManager;
import be.pxl.objects.Theme;

public class AmountToAddQuestionsWindow extends JFrame {

	private static final long serialVersionUID = -1409962556315579963L;
	private JFrame frame;

	public AmountToAddQuestionsWindow(Theme theme) {
		frame = this;
		
		JPanel panel = new JPanel(new GridLayout(3, 1));
		
		
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
		
		
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		
		this.add(panel);

		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowManager((amount.getSelectedIndex() + 1), theme).actionPerformed(e);
				frame.dispose();

			}
		});
	}

}
