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
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemberFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = -3141501093779143292L;	
	private JPanel contentPane;
	private JTable table;
	private JTextField tfSearchMember;
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
	
	private HashMap<String, LibraryMember> members = null;//LibraryMemberController.loadMembers();
	private JPanel panel_1;
	private JPanel panel;
	private boolean isAddOperation = true;
	JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public MemberFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 11, 818, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search Member(ID/Name):");
		lblNewLabel.setBounds(14, 32, 135, 14);
		panel.add(lblNewLabel);
		
		tfSearchMember = new JTextField();
		tfSearchMember.setBounds(142, 29, 270, 20);
		panel.add(tfSearchMember);
		tfSearchMember.setColumns(10);
		
		btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMember();
			}
		});
		btnNewButton_1.setBounds(421, 28, 66, 23);
		panel.add(btnNewButton_1);
		
		btnDeleteButton = new JButton("Delete");
		btnDeleteButton.setBounds(121, 1, 89, 23);
		panel.add(btnDeleteButton);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyMember();
			}
		});
		btnUpdate.setBounds(213, 1, 76, 23);
		panel.add(btnUpdate);
		
		JButton btnAddButton = new JButton("Add");
		btnAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMember();
			}
		});
		btnAddButton.setBounds(10, 1, 89, 23);
		panel.add(btnAddButton);
		
		btnDeleteButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				
				int selectedRowIndex = table.getSelectedRow();
				if(selectedRowIndex == -1) 
				{
					showMessage("Please select a row first.");
					return;
				}
				int confirmation = JOptionPane.showConfirmDialog(null, "Do you want to delete the Member with Member ID: " + model.getValueAt(selectedRowIndex,0).toString() + "?");
				if(confirmation!=0)return;
				
				else 
				{
					//table.remove(selectedRowIndex);
					LibraryMemberController.removeMember(model.getValueAt(selectedRowIndex,0).toString());					
					showMessage("Member deleted successfully.");
				}
				
				/*
				int selectedRowIndex = table.getSelectedRow();
				if(selectedRowIndex == -1) 
				{
					showMessage("Please select a row first.");
					return;
				}
				int confirmation = JOptionPane.showConfirmDialog(null, "Do you want to delete the Member with Member ID: " + model.getValueAt(selectedRowIndex,0).toString() + "?");
				if(confirmation!=0)return;
				
				else 
				{
					table.remove(selectedRowIndex);
					showMessage("Memeber deleted successfully.");
				}
				*/
			}
		});
		
		scrollPane = new JScrollPane();
				
		
		
		scrollPane.setBounds(20, 107, 824, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(SystemColor.activeCaption);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSelectedRowIntoTextFields();
				tfMemberId.setEditable(false);
			}
		});
		
		
		
		model = new DefaultTableModel();
		Object[] columns  = this.getMemberTableColumnNames();
		Object[] row = new Object[8];
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		panel_1 = new JPanel();
		panel_1.setToolTipText("");
		panel_1.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(183, 266, 432, 206);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Member ID:");
		lblNewLabel_1.setBounds(6, 16, 66, 30);
		panel_1.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("First Name:");
		lblNewLabel_2.setBounds(6, 37, 66, 30);
		panel_1.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Last Name:");
		lblNewLabel_3.setBounds(6, 57, 66, 30);
		panel_1.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Street:");
		lblNewLabel_4.setBounds(6, 78, 66, 30);
		panel_1.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("City:");
		lblNewLabel_5.setBounds(6, 104, 66, 30);
		panel_1.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("State:");
		lblNewLabel_6.setBounds(249, 105, 66, 30);
		panel_1.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Zip:");
		lblNewLabel_7.setBounds(6, 136, 66, 30);
		panel_1.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Telephone:");
		lblNewLabel_8.setBounds(249, 136, 66, 30);
		panel_1.add(lblNewLabel_8);
		
		tfMemberId = new JTextField();
		tfMemberId.setBounds(70, 21, 177, 20);
		panel_1.add(tfMemberId);
		tfMemberId.setColumns(10);
		
		tfFirstName = new JTextField();
		tfFirstName.setBounds(70, 42, 177, 20);
		panel_1.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		tfLastName = new JTextField();
		tfLastName.setBounds(70, 62, 177, 20);
		panel_1.add(tfLastName);
		tfLastName.setColumns(10);
		
		tfStreet = new JTextField();
		tfStreet.setBounds(70, 83, 177, 20);
		panel_1.add(tfStreet);
		tfStreet.setColumns(10);
		
		tfCity = new JTextField();
		tfCity.setBounds(70, 109, 101, 20);
		panel_1.add(tfCity);
		tfCity.setColumns(10);
		
		tfZip = new JTextField();
		tfZip.setBounds(70, 136, 101, 20);
		panel_1.add(tfZip);
		tfZip.setColumns(10);
		
		tfState = new JTextField();
		tfState.setBounds(325, 110, 101, 20);
		panel_1.add(tfState);
		tfState.setColumns(10);
		
		tfTelephone = new JTextField();
		tfTelephone.setBounds(325, 141, 101, 20);
		panel_1.add(tfTelephone);
		tfTelephone.setColumns(10);
		
		btnSaveButton = new JButton("Save");
		btnSaveButton.setBounds(70, 177, 89, 23);
		panel_1.add(btnSaveButton);
		
		btnClearButton = new JButton("Clear");
		btnClearButton.setBounds(174, 177, 89, 23);
		panel_1.add(btnClearButton);
		btnClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearInputFields();
			}
		});
		btnSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				saveMember();
			}
		});
		
		loadMembers();
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
		if(tfMemberId.getText().isEmpty()) return false;
		if(tfFirstName.getText().isEmpty()) return false;
		if(tfLastName.getText().isEmpty()) return false;
		if(tfStreet.getText().isEmpty()) return false;
		if(tfCity.getText().isEmpty()) return false;
		if(tfState.getText().isEmpty()) return false;
		if(tfZip.getText().isEmpty()) return false;
		if(tfTelephone.getText().isEmpty()) return false;
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
	
	private String[] getNewMemberInfoFromFields(){
		return new String[]{
				tfMemberId.getText(),
				tfFirstName.getText(),
				tfLastName.getText(),
				tfStreet.getText(),
				tfCity.getText(),
				tfState.getText(),
				tfZip.getText(),
				tfTelephone.getText()
			};
	}
	
	private void showMessage(String message) 
	{
		JOptionPane.showMessageDialog(null,message);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try 
		{
			/*
			if (e.getSource() == this.btnLoadMembers){
				 this.loadMembers();
				 return;
			}
			
			if (e.getSource() == this.btnDeleteButton){
				this.deleteMember();
				return;
			}
			*/
		}
		catch(Exception e1) 
		{
			showMessage(e1.getLocalizedMessage());
		} 
	}
	
	
	private void searchMember() 
	{
		String searchText = tfSearchMember.getText().trim();
		if(searchText!= null && searchText.trim().length() > 0) 
		{
			model.setRowCount(0);
			HashMap<String, LibraryMember> tempMembers = LibraryMemberController.loadMembersBySearchText(searchText);
			for (String key : tempMembers.keySet()) {
				LibraryMember member = tempMembers.get(key);
				
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
		else 
		{
			//showMessage("Please specify search criteria.");
			loadMembers();
		}
	}
	
	
	private void loadMembers() {
		// Reset the model first
		model.setRowCount(0);
		HashMap<String, LibraryMember> tempMembers = this.getAllMembers();
		
		for (String key : tempMembers.keySet()) {
			LibraryMember member = tempMembers.get(key);
			
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
	
	private void deleteMember() 
	{
		int selectedRowIndex = table.getSelectedRow();
		if(selectedRowIndex == -1) 
		{
			showMessage("Please select a row first.");
			return;
		}
		int confirmation = JOptionPane.showConfirmDialog(null, "Do you want to delete the Member with Member ID: " + model.getValueAt(selectedRowIndex,0).toString() + "?");
		if(confirmation!=0)return;
		
		else 
		{
			LibraryMemberController.removeMember(model.getValueAt(selectedRowIndex,0).toString());
			model.removeRow(selectedRowIndex);
			//table.remove(selectedRowIndex);
			//table.revalidate();
			this.loadMembers();
			//model.fireTableDataChanged();
			//table.repaint();
			//showMessage("Member deleted successfully.");
		}
	}
	
	private void addMember() 
	{
		isAddOperation = true;
	}
	
	private void modifyMember() 
	{
		isAddOperation = false;
		tfMemberId.setEnabled(false);
		btnClearButton.setEnabled(false);
		btnSaveButton.setEnabled(true);
		
		/*
		if(!isValidInputs()) {
			JOptionPane.showMessageDialog(null, "Please insert the values");
			return;
		}		
		
		
		String[] values = getNewMemberInfoFromFields();
		int rowNumber = table.getSelectedRow();
		if(rowNumber < 0) {
			JOptionPane.showMessageDialog(null, "Please select a row");
			return;
		}
		// update model row
		for(int i=0; i<values.length; i++) {
			model.setValueAt(values[i], rowNumber, i);
		}
		
		try 
		{
			saveMember();
		}
		catch(Exception ex) 
		{
			showMessage(ex.getLocalizedMessage());
		}
		*/
	}
	
	private void saveMember() 
	{
		if(!isValidInputs()) 
		{
			showMessage("Please insert all the values.");
			return;
		}
		
		LibraryMember existingMember = LibraryMemberController.searchMemberById(tfMemberId.getText().trim());
		if(existingMember != null && isAddOperation) 
		{
			showMessage("Member id already exists");
			return;
		}
		
		try 
		{
			String[] row = getNewMemberInfoFromFields();
			if(isAddOperation) 
			{
				
				model.addRow(row);
				//LibraryMember member = getLibraryMember(row);
				//LibraryMemberController.saveNewMember(member);
			}
			/*
			else 
			{
				LibraryMemberController.saveNewMember(existingMember);
			}*/
			LibraryMemberController.saveNewMember(getLibraryMember(row));
			
			
			clearInputFields();
			showMessage("Data saved successfully.");
		}
		catch(Exception e1) {
			//model.removeRow(table.getRowCount()-1);
			showMessage(e1.getLocalizedMessage());
		}
	}
	
	private void setSelectedRowIntoTextFields() {
		isAddOperation = false;
		int selectedRow = table.getSelectedRow();
		if(selectedRow < 0)
			return;
		btnSaveButton.setEnabled(false);
		btnClearButton.setEnabled(false);
		tfMemberId.setText(model.getValueAt(selectedRow, 0).toString());
		tfFirstName.setText(model.getValueAt(selectedRow, 1).toString());
		tfLastName.setText(model.getValueAt(selectedRow, 2).toString());
		tfStreet.setText(model.getValueAt(selectedRow, 3).toString());
		tfCity.setText(model.getValueAt(selectedRow, 4).toString());
		tfState.setText(model.getValueAt(selectedRow, 5).toString());
		tfZip.setText(model.getValueAt(selectedRow, 6).toString());
		tfTelephone.setText(model.getValueAt(selectedRow, 7).toString());
	}
	
	public LibraryMember getLibraryMember(String...values) {
		return new LibraryMember(
				values[1],
				values[2],
				values[7],
				values[0],
				new Address(
						values[3],
						values[4],
						values[5],
						values[6])
				);
	}
	
	private HashMap<String, LibraryMember> getAllMembers() 
	{
		this.members = LibraryMemberController.loadMembers();
		return this.members;
	}
	
	/*
	private void refreshMemberList() 
	{
		model = new DefaultTableModel();
		Object[] columns  = this.getMemberTableColumnNames();
		Object[] row = new Object[8];
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}
	*/
	
}
