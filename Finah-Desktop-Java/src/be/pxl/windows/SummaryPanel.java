package be.pxl.windows;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import be.pxl.json.AnswerDb;
import be.pxl.json.HashesDB;
import be.pxl.listeners.WindowManager;
import be.pxl.objects.AnswerList;
import be.pxl.objects.Hashes;
import be.pxl.settings.ConfigFile;
import be.pxl.settings.SettingClass;

public class SummaryPanel extends JPanel {

	private static final long serialVersionUID = 7474806161818429171L;

	private Properties configFile = new ConfigFile().getConfigFile();
	private List<Hashes> hashesList = new HashesDB().readHashes();

	public SummaryPanel() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		topPanelLayout();
		fillUsersTable();
		int usertype = new SettingClass().getUsertype();
		if (usertype != 5) {
			bottemPanelLayout();
		}
	}

	private void topPanelLayout() {
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.setBackground(Color.WHITE);
		JLabel title = new JLabel(configFile.getProperty("labelSummary"));
		title.setFont(new SettingClass().getTitleFont());
		topPanel.add(title);
		this.add(topPanel, BorderLayout.NORTH);
	}

	private void fillUsersTable() {
		DefaultTableModel dm = new DefaultTableModel();

		Object[][] object = new Object[hashesList.size()][];
		for (int i = 0; i < hashesList.size(); i++) {

			object[i] = new Object[] { configFile.getProperty("elementRapportnummer") + hashesList.get(i).getId(),
					hashesList.get(i).getDate().replaceFirst("T", "       "),
					configFile.getProperty("elementRapport") + hashesList.get(i).getId() };
		}

		dm.setDataVector(object, new Object[] { configFile.getProperty("elementRapportnummer"), configFile.getProperty("elementDatum"), configFile.getProperty("elementButton") });

		JTable table = new JTable(dm);

		table.getColumn(configFile.getProperty("elementButton")).setCellRenderer(new ButtonRenderer());
		table.getColumn(configFile.getProperty("elementButton")).setCellEditor(
				new ButtonEditor(new JCheckBox()));
		JScrollPane scroll = new JScrollPane(table);

		table.setBackground(Color.WHITE);
		scroll.setBackground(Color.WHITE);
		this.add(scroll, BorderLayout.CENTER);
	}

	private void bottemPanelLayout() {
		JPanel bottemPanel = new JPanel(new FlowLayout());
		bottemPanel.setBackground(Color.WHITE);
		JButton sendQuestionnaire = new JButton(
				configFile.getProperty("btnSendQuestionnaire"));

		sendQuestionnaire.addActionListener(new WindowManager(this));

		bottemPanel.add(sendQuestionnaire);

		this.add(bottemPanel, BorderLayout.SOUTH);
	}
	
	public void refreshTable() {
		hashesList = new HashesDB().readHashes();
		fillUsersTable();
		
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
	Properties configFile = new ConfigFile().getConfigFile();
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
			int rapportNumber = Integer.parseInt(text.replaceFirst(
					configFile.getProperty("elementRapport"), ""));
			String hash = new HashesDB().getHashById(rapportNumber);
			List<AnswerList> answerList = new AnswerDb().readAnswersByHash(hash);
			boolean client = false;
			boolean caregiver = false;
			for (AnswerList answer : answerList) {
				if(answer.getUsertype() == 3) {
					caregiver = true;
				} else {
					client = true;
				}
			}
			
			if (caregiver && client) {
				new WindowManager(hash);
			} else if(!caregiver && !client) {
				JOptionPane.showMessageDialog(null, configFile.getProperty("errorMP"), configFile.getProperty("error"), JOptionPane.ERROR_MESSAGE);
			} else if(!caregiver) {
				JOptionPane.showMessageDialog(null, configFile.getProperty("errorM"), configFile.getProperty("error"), JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, configFile.getProperty("errorP"), configFile.getProperty("error"), JOptionPane.ERROR_MESSAGE);
			}
			
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
