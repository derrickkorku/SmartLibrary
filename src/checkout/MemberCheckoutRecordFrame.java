package checkout;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mem_mgmt.Address;
import mem_mgmt.LibraryMember;

import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;

public class MemberCheckoutRecordFrame extends JFrame {
	private static final long serialVersionUID = -7112903973145529432L;
	private JPanel contentPane;
	private LibraryMember member;
	private List<CheckOutEntry> records;
	private JTable table;
	JLabel lblMemberInfo = new JLabel("");
	DefaultTableModel model;
	
	
	MemberCheckoutRecordFrame (LibraryMember member){
		setTitle("Member Checkout Record");
		this.setMember(member);
		this.setRecords(MemberCheckoutRecordController.getMemberRecords(member));
	    
		if (this.records.size() == 0) {
			JOptionPane.showMessageDialog(this, "No record exists for member " + member.toString());
			return;
		}
		
		lblMemberInfo.setText("Checkout Record for " + member.toString());
		JOptionPane.showMessageDialog(this, "Checkout Entry created successfully for " + member.toString());
		
		this.showForm();
		this.loadTable();
	}

	/**
	 * Create the frame.
	 */
	public void showForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 658, 358);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		Object[] columns  = this.getColumnNames();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		
		lblMemberInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMemberInfo.setBounds(10, 11, 588, 14);
		contentPane.add(lblMemberInfo);
		
		this.setVisible(true);
	}
	
	private Object[] getColumnNames() {
		return new Object[]{
			"Book ISBN", 
			"Book Copy Number", 
			"Checkout Date",
			"Due Date"
		};
	}
	
	
	private void loadTable() {
		for (CheckOutEntry record : this.records) {
			System.out.println(record.getBookCopy().getBook().getIsbn());
			String[] aRow = {
					record.getBookCopy().getBook().getIsbn(),
					record.getBookCopy().getCopyNum() + "",
					record.getCheckoutDate().toString(),
					record.getDueDate().toString()
			};
			
			model.addRow(aRow);
		}
	}
	

	public LibraryMember getMember() {
		return member;
	}

	public void setMember(LibraryMember member) {
		this.member = member;
	}

	public List<CheckOutEntry> getRecords() {
		return records;
	}

	public void setRecords(List<CheckOutEntry> records) {
		this.records = records;
	}
}
