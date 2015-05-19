package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import be.pxl.json.HashesDB;
import be.pxl.listeners.WindowManager;
import be.pxl.objects.Hashes;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SettingClass;

public class SummaryPanel extends JPanel {

	private static final long serialVersionUID = 7474806161818429171L;

	private Properties configFile = new ConfigFile().getConfigFile();
	private List<Hashes> hashesList = new HashesDB().readHashes();


	public SummaryPanel() {
		this.setLayout(new BorderLayout());
		topPanelLayout();
		centerPanelLayout();
		bottemPanelLayout();
	}

	private void topPanelLayout() {
		JPanel topPanel = new JPanel(new FlowLayout());
		JLabel title = new JLabel(configFile.getProperty("labelSummary"));
		title.setFont(new SettingClass().getTitleFont());
		topPanel.add(title);
		this.add(topPanel, BorderLayout.NORTH);
	}

	private void fillUsersTable() {
		DefaultTableModel dm = new DefaultTableModel();
		Object[][] object = new Object[hashesList.size()][];
		for (int i = 0; i < hashesList.size(); i++) {

			object[i] = new Object[] {hashesList.get(i).getId(), hashesList.get(i).getDate(), "Genereer rapport " + hashesList.get(i).getId()};
		}
		
	    dm.setDataVector(object , 
	    		new Object[] { "Integer", "String", "Button" }
	    );

	    JTable table = new JTable(dm);
	    table.getColumn("Button").setCellRenderer(new ButtonRenderer());
	    table.getColumn("Button").setCellEditor(
	        new ButtonEditor(new JCheckBox()));
	    JScrollPane scroll = new JScrollPane(table);
	    this.add(scroll, BorderLayout.CENTER);
	}

	private void centerPanelLayout() {
		fillUsersTable();

	}

	private void bottemPanelLayout() {
		JPanel bottemPanel = new JPanel(new FlowLayout());
		JButton sendQuestionnaire = new JButton(
				configFile.getProperty("btnSendQuestionnaire"));
		JButton acceptButton = new JButton(configFile.getProperty("btnAccept"));
		JButton refuseButton = new JButton(configFile.getProperty("btnRefuse"));

		sendQuestionnaire.addActionListener(new WindowManager());

		bottemPanel.add(sendQuestionnaire);
		bottemPanel.add(acceptButton);
		bottemPanel.add(refuseButton);

		this.add(bottemPanel, BorderLayout.SOUTH);
	}

}

@SuppressWarnings("serial")
class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}

@SuppressWarnings("serial")
class ButtonEditor extends DefaultCellEditor {
	protected JButton button;

	private String label;

	private boolean isPushed;

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			String text = button.getText();
			int rapportNumber = Integer.parseInt(text.replaceFirst("Genereer rapport ", ""));
			String hash = new HashesDB().getHashById(rapportNumber - 1);
			new WindowManager(hash);
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
