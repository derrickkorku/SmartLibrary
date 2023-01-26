package mem_mgmt;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.SystemColor;

public class MemberFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3141501093779143292L;
	private JPanel contentPane;
	private JTable table;
	private JTextField tfSearchMember;
	
	JButton btnLoadMembers;
	DefaultTableModel model;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JTextField tfMemberId;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfStreet;
	private JTextField tfCity;
	private JTextField tfZip;
	private JTextField tfState;
	private JTextField tfTelephone;
	private JButton btnSaveButton;
	private JButton btnNewButton_1;
	private JButton btnClearButton;
	private JButton btnDeleteButton;
	private JButton btnUpdate;
	
	private HashMap<String, LibraryMember> members = LibraryMemberController.loadMembers();

	/**
	 * Create the frame.
	 */
	public MemberFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(0, 0, 795, 23);
		contentPane.add(panel);
		
		btnLoadMembers = new JButton("Load Members");
		btnLoadMembers.addActionListener(this);
//		btnLoadMembers.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//row[0] = tfMemberId.getText();
//				
//				List<LibraryMember> allMembers =  controller.getLibraryMembers();
//				allMembers.forEach((LibraryMember member) -> 
//				{
//					Address address = member.getAddress();
//					String[] aRow = {
//							member.getMemberId(),
//							member.getFirstName(),
//							member.getLastName(),			
//							address.getStreet(),
//							address.getCity(),
//							address.getState(),
//							address.getZip(),
//							member.getTelephone()
//					};
//					model.addRow(aRow);
//		        });
//				
//			}
//		});
		btnLoadMembers.setBounds(20, 35, 101, 23);
		contentPane.add(btnLoadMembers);
		btnLoadMembers.setHorizontalAlignment(SwingConstants.LEFT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 105, 824, 70);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(SystemColor.activeCaption);
		model = new DefaultTableModel();
		Object[] columns  = this.getMemberTableColumnNames();
		Object[] row = new Object[8];
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Search Member(ID/Name):");
		lblNewLabel.setBounds(183, 38, 135, 14);
		contentPane.add(lblNewLabel);
		
		tfSearchMember = new JTextField();
		tfSearchMember.setBounds(310, 35, 270, 20);
		contentPane.add(tfSearchMember);
		tfSearchMember.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Member ID:");
		lblNewLabel_1.setBounds(20, 197, 66, 30);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("First Name:");
		lblNewLabel_2.setBounds(20, 218, 66, 30);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Last Name:");
		lblNewLabel_3.setBounds(20, 238, 66, 30);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Street:");
		lblNewLabel_4.setBounds(20, 259, 66, 30);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("City:");
		lblNewLabel_5.setBounds(20, 285, 66, 30);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("State:");
		lblNewLabel_6.setBounds(263, 286, 66, 30);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Zip:");
		lblNewLabel_7.setBounds(20, 317, 66, 30);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Telephone:");
		lblNewLabel_8.setBounds(263, 317, 66, 30);
		contentPane.add(lblNewLabel_8);
		
		tfMemberId = new JTextField();
		tfMemberId.setBounds(84, 202, 177, 20);
		contentPane.add(tfMemberId);
		tfMemberId.setColumns(10);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(84, 223, 177, 20);
		contentPane.add(tfFirstName);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(84, 243, 177, 20);
		contentPane.add(tfLastName);
		
		tfStreet = new JTextField();
		tfStreet.setColumns(10);
		tfStreet.setBounds(84, 264, 177, 20);
		contentPane.add(tfStreet);
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(84, 290, 101, 20);
		contentPane.add(tfCity);
		
		tfZip = new JTextField();
		tfZip.setColumns(10);
		tfZip.setBounds(84, 317, 101, 20);
		contentPane.add(tfZip);
		
		tfState = new JTextField();
		tfState.setColumns(10);
		tfState.setBounds(339, 291, 101, 20);
		contentPane.add(tfState);
		
		tfTelephone = new JTextField();
		tfTelephone.setColumns(10);
		tfTelephone.setBounds(339, 322, 101, 20);
		contentPane.add(tfTelephone);
		
		btnSaveButton = new JButton("Save");
		btnSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				if(!isValidInputs()) 
				{
					showMessage("Please atleast type Id and first name.");
				}
				else 
				{
					row[0] = tfMemberId.getText();
					row[1] = tfFirstName.getText();
					row[2] = tfLastName.getText();
					row[3] = tfStreet.getText();
					row[4] = tfCity.getText();
					row[5] = tfState.getText();
					row[6] = tfZip.getText();
					row[7] = tfTelephone.getText();
					
					model.addRow(row);
					clearInputFields();
					showMessage("Data saved successfully.");
				}
			}
		});
		btnSaveButton.setBounds(84, 358, 89, 23);
		contentPane.add(btnSaveButton);
		
		btnNewButton_1 = new JButton("Search");
		btnNewButton_1.setBounds(590, 34, 66, 23);
		contentPane.add(btnNewButton_1);
		
		btnClearButton = new JButton("Clear");
		btnClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearInputFields();
			}
		});
		btnClearButton.setBounds(188, 358, 89, 23);
		contentPane.add(btnClearButton);
		
		btnDeleteButton = new JButton("Delete");
		btnDeleteButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table.getSelectedRow();
				if(selectedRowIndex == -1) 
				{
					showMessage("Please select a row first.");
				}
				else 
				{
					table.remove(selectedRowIndex);
					showMessage("Memeber deleted successfully.");
				}
			}
		});
		btnDeleteButton.setBounds(20, 71, 89, 23);
		contentPane.add(btnDeleteButton);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(120, 71, 89, 23);
		contentPane.add(btnUpdate);
	}
	
	private void clearInputFields() 
	{
		tfMemberId.setText(null);
		tfFirstName.setText(null);
		tfLastName.setText(null);
		tfStreet.setText(null);
		tfCity.setText(null);
		tfState.setText(null);
		tfZip.setText(null);
		tfTelephone.setText(null);
	}
	
	private boolean isValidInputs() 
	{
		if(
				tfMemberId.getText().trim().length() <= 0 
				|| tfFirstName.getText().trim().length() <= 0
			) 
		{
			return false;
		}
		return true;
	}
	
	private Object[] getMemberTableColumnNames() {
		return new Object[]{
			"Member ID", 
			"First Name", 
			"Last Name",
			"Street", 
			"City", 
			"State", 
			"Zip", 
			"Telephone Number"
		};
	}
	
	private void showMessage(String message) 
	{
		JOptionPane.showMessageDialog(null,message);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	 if (e.getSource() == this.btnLoadMembers) {
		 this.loadMembers();
	 }
		
	}
	
	private void loadMembers() {
		for (String key : this.members.keySet()) {
			LibraryMember member = this.members.get(key);
			
			Address address = member.getAddress();
			String[] aRow = {
					member.getMemberId(),
					member.getFirstName(),
					member.getLastName(),			
					address.getStreet(),
					address.getCity(),
					address.getState(),
					address.getZip(),
					member.getPhoneNumber()
			};
			
			model.addRow(aRow);
		}
	
	}
}
