package passap.test;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class MainInterface {
	private int count=0;
	private DBInterface dbObject = new DBInterface();
	private JLabel labelName = new JLabel("");
	private JLabel labelUser = new JLabel("");
	private JLabel labelPass = new JLabel("");
	private JFrame frame;
	private JPanel panel_1;
	private JButton btnEdit;
	private JButton btnDelete;

	/**
	 * Create the application.
	 */
	public MainInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String[] names = dbObject.getNames();
		double numNames = names.length;
		int rows = (int) Math.ceil(numNames/2.0);
		int cols = 2;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 550);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new  WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				closeDBInterface();
				System.exit(0);
			}
		});
		
		
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1184, 862);
		
		for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
            	if (count < (int)numNames) {
            		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
            		gbc_btnNewButton.gridx = j;
            		gbc_btnNewButton.gridy = i;
	                JButton btn=new JButton(names[count]);
	                btn.addActionListener(new ButtonActionListener(names[count]));
	                btn.setPreferredSize(new Dimension(150, 40));
	                panel.add(btn, gbc_btnNewButton);
	                count++;
            	}
            }
        }
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane, panel_1);
		panel_1.setLayout(null);
		labelName.setFont(new Font("Viner Hand ITC", Font.PLAIN, 33));

		labelName.setBounds(10, 122, 338, 44);
		panel_1.add(labelName);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblUsername.setBounds(10, 177, 150, 29);
		panel_1.add(lblUsername);
		labelUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		
		labelUser.setBounds(10, 230, 338, 24);
		panel_1.add(labelUser);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblPassword.setBounds(10, 300, 150, 29);
		panel_1.add(lblPassword);
		
		labelPass.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelPass.setBounds(10, 369, 338, 24);
		panel_1.add(labelPass);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 96, 338, 2);
		panel_1.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 109, 338, 2);
		panel_1.add(separator_1);
		
		JLabel lblTheManger = new JLabel("The Manager");
		lblTheManger.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTheManger.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheManger.setBackground(Color.DARK_GRAY);
		lblTheManger.setFont(new Font("Viner Hand ITC", Font.PLAIN, 40));
		lblTheManger.setBounds(10, 11, 338, 57);
		panel_1.add(lblTheManger);
		
		JLabel lblToAddA = new JLabel("To add a new username/password: ");
		lblToAddA.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblToAddA.setBounds(10, 71, 172, 14);
		panel_1.add(lblToAddA);
		
		JButton btnNewAccount = new JButton("Click Here");
		btnNewAccount.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNewAccount.setBounds(192, 71, 156, 14);
		btnNewAccount.addActionListener(new newAccountListener());
		panel_1.add(btnNewAccount);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(320);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}

	private class ButtonActionListener implements ActionListener{
		
		String name;
		
		public ButtonActionListener(String name) {
			this.name = name;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (dbObject.getInfo(name) != null) {
				showInfo(dbObject.getInfo(name));
				
				if (btnEdit != null) panel_1.remove(btnEdit);
				if (btnDelete != null) panel_1.remove(btnDelete);
				
				btnEdit = new JButton("Edit");
				btnEdit.setBounds(10, 462, 89, 23);
				btnEdit.addActionListener(new editListener(labelName.getText()));
				panel_1.add(btnEdit);
				
				btnDelete = new JButton("Delete");
				btnDelete.setBounds(259, 462, 89, 23);
				btnDelete.addActionListener(new deleteListener(labelName.getText()));
				panel_1.add(btnDelete);
				
				frame.validate();
				frame.repaint();
			}
			else {
				showInfo(new String[] {"Deleted.", "This account was deleted.", ""});
				if (btnEdit != null) panel_1.remove(btnEdit);
				if (btnDelete != null) panel_1.remove(btnDelete);
				frame.validate();
				frame.repaint();
			}
		}
	}
	
	private class editListener implements ActionListener{
		
		String name;
		
		public editListener(String name) {
			this.name = name;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String userEdit;
			String passEdit;
			
			JTextField userField = new JTextField(5);
			JTextField passField = new JTextField(5);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("Username:"));
			myPanel.add(userField);
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel("Password:"));
			myPanel.add(passField);
			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Please enter in new username/password", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		    	//potentially(definitely) more simplistic if I created a different call to updateEntry using only user or pass
		    	//but eh
		    	if (!userField.getText().trim().isEmpty()) {
		    		userEdit = userField.getText();
		    	}
		    	else userEdit = dbObject.getInfo(name)[1]; //if empty resave old username
		    	
		    	if (!passField.getText().trim().isEmpty()) {
		    		passEdit = passField.getText();
		    	}
		    	else passEdit = dbObject.getInfo(name)[2]; //if empty resave old password
		    	
		    	dbObject.updateEntry(name, userEdit, passEdit);
				showInfo(dbObject.getInfo(name));
		    }
		}
	}
	
	private class deleteListener implements ActionListener{
		
		String name;
		
		public deleteListener(String name) {
			this.name = name;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int result = JOptionPane.showConfirmDialog(frame,
					"Are you sure you want to delete this account?","Delete Confirmation", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
				dbObject.deleteEntry(name);
				showInfo(new String[]{"","",""});
		    }
		}
	}
	
	private class newAccountListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JTextField nameField = new JTextField(5);
			JTextField userField = new JTextField(5);
			JTextField passField = new JTextField(5);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("Company:"));
			myPanel.add(nameField);
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel("Username:"));
			myPanel.add(userField);
			myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			myPanel.add(new JLabel("Password:"));
			myPanel.add(passField);
			int result = JOptionPane.showConfirmDialog(null, myPanel, 
					"Please enter in new username/password", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		    	dbObject.addEntry(nameField.getText(), userField.getText(), passField.getText());
				showInfo(dbObject.getInfo(nameField.getText()));
		    }
		}
	}
	
	private void  closeDBInterface() {
		dbObject.close();
	}
	
	
	private void showInfo(String[] info) {
		this.labelName.setText(info[0]);
		this.labelUser.setText(info[1]);
		this.labelPass.setText(info[2]);
		this.frame.validate();
		this.frame.repaint();
	}
}

