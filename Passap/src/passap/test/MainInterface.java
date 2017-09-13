package passap.test;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Font;

public class MainInterface {
	private int count=0;
	private DBInterface dbObject = new DBInterface();
	private JLabel labelName = new JLabel("");
	private JLabel labelUser = new JLabel("");
	private JLabel labelPass = new JLabel("");
	private JFrame frame;

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
		
		JPanel panel_1 = new JPanel();
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
		labelName.setFont(new Font("Viner Hand ITC", Font.PLAIN, 40));

		labelName.setBounds(10, 11, 338, 57);
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
			showInfo(dbObject.getInfo(name));
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

