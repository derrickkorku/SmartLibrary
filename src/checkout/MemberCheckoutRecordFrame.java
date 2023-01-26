package checkout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mem_mgmt.LibraryMember;

import javax.swing.JTable;

public class MemberCheckoutRecordFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private LibraryMember member;
	
	
	MemberCheckoutRecord(LibraryMember member){
		this.setMember(member);
	}

	/**
	 * Create the frame.
	 */
	public MemberCheckoutRecord() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 958, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		table = new JTable();
		contentPane.add(table);
	}

	public LibraryMember getMember() {
		return member;
	}

	public void setMember(LibraryMember member) {
		this.member = member;
	}

}
