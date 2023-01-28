package checkout;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import mem_mgmt.LibraryMember;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberCheckoutRecordFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = -7112903973145529432L;
	private JPanel contentPane;
	private LibraryMember member;
	private List<MemberCheckoutRecord> records;
	private JTable table;
	JLabel lblMemberInfo = new JLabel("");
	DefaultTableModel model;
	JButton btnNewCheckout = new JButton("Create | View Checkout Record");
	
	
	MemberCheckoutRecordFrame (LibraryMember member){
		setResizable(false);
		setTitle("Member Checkout Record");
		this.setMember(member);
		this.setRecords(MemberCheckoutRecordController.getMemberRecords(member));
	    
		if (this.records.size() == 0) {
			JOptionPane.showMessageDialog(this, "No record exists for member " + member.toString());
			return;
		}
		
		lblMemberInfo.setText("Checkout Record for " + member.toString());
	
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
		lblMemberInfo.setBounds(10, 11, 435, 14);
		contentPane.add(lblMemberInfo);
		
		btnNewCheckout.addActionListener(this);
		btnNewCheckout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewCheckout.setBounds(445, 8, 223, 40);
		contentPane.add(btnNewCheckout);
		
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
		this.records.remove(0);
		
		for (MemberCheckoutRecord record : this.records) {
			String[] aRow = {
					record.getCheckoutEntry().getBookCopy().getBook().getIsbn(),
					record.getCheckoutEntry().getBookCopy().getCopyNum() + "",
					record.getCheckoutEntry().getCheckoutDate().toString(),
					record.getCheckoutEntry().getDueDate().toString()
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

	public List<MemberCheckoutRecord> getRecords() {
		return records;
	}

	public void setRecords(List<MemberCheckoutRecord> records) {
		this.records = records;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewCheckout) {
			CheckoutForm chkForm = new CheckoutForm();
			this.dispose();
			chkForm.setVisible(true);
		}
	}
}
