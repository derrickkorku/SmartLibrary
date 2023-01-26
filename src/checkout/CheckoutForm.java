package checkout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import book_mgmt.Book;
import book_mgmt.BookController;
import book_mgmt.BookCopy;
import mem_mgmt.LibraryMember;
import mem_mgmt.LibraryMemberController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class CheckoutForm extends JFrame implements ActionListener{
	private static final long serialVersionUID = -8819649738818882117L;
	private JPanel contentPane;
	private JTextField txtMemberID;
	private JTextField txtBookISBN;
	JButton btnCheckoutBook = new JButton("Checkout Book");
	JButton btnViewMemberRecord = new JButton("View Checkout Record");

	/**
	 * Create the frame.
	 */
	public CheckoutForm() {
		setTitle("Checkout Book | View Member Checkout Record");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Member ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(103, 53, 165, 14);
		contentPane.add(lblNewLabel);
		
		txtMemberID = new JTextField();
		txtMemberID.setBounds(103, 78, 416, 51);
		contentPane.add(txtMemberID);
		txtMemberID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Book ISBN");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(103, 155, 213, 14);
		contentPane.add(lblNewLabel_1);
		
		txtBookISBN = new JTextField();
		txtBookISBN.setBounds(103, 180, 416, 51);
		contentPane.add(txtBookISBN);
		txtBookISBN.setColumns(10);
		
		
		btnCheckoutBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCheckoutBook.setBounds(103, 242, 196, 62);
		btnCheckoutBook.addActionListener(this);
		contentPane.add(btnCheckoutBook);
		
		btnViewMemberRecord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewMemberRecord.setBounds(309, 242, 210, 62);
		btnViewMemberRecord.addActionListener(this);
		contentPane.add(btnViewMemberRecord);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCheckoutBook) {
			this.checkoutBook();
		}
		
		if(e.getSource() == this.btnViewMemberRecord) {
			this.viewMemberCheckoutRecord();
		}
	}
	
	private void checkoutBook() {
		if (this.txtBookISBN.getText().isBlank() || this.txtMemberID.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Book ISBN and Member ID are required for Book Checkout operation.");
			return;
		}
		
		LibraryMember member = LibraryMemberController.getByMemberID(this.txtMemberID.getText());
		
		if (member == null) {
			JOptionPane.showMessageDialog(this, "No library member record exists for the provided MemberID.");
			return;
		}
		
		Book book = BookController.getBookByISBN(this.txtBookISBN.getText());
		
		if (book == null) {
			JOptionPane.showMessageDialog(this, "No book record exits for the provided Book ISBN");
			return;
		}
		
		
		BookCopy bookCopy = BookController.getBookCopy(book);
		
		if (bookCopy == null) {
			JOptionPane.showMessageDialog(this, "There is currently no copy for book " + book.getIsbn() + " - " + book.getTitle());
			return;
		}
		
		MemberCheckoutRecord record = MemberCheckoutRecordController.createMemberCheckoutRecord(bookCopy, member);
		
		if (record == null) {
			JOptionPane.showMessageDialog(this, "There was an error creating checkout record. Try again.");
			return;
		}
		
		JOptionPane.showMessageDialog(this, "Checkout Entry created successfully for " + member.toString());
		this.dispose();
		MemberCheckoutRecordFrame recordsFrame = new MemberCheckoutRecordFrame(member);
	}
	
	private void viewMemberCheckoutRecord() {
		if (this.txtMemberID.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Member ID is required for View Member Checkout Record operation.");
			return;
		}
		
        LibraryMember member = LibraryMemberController.getByMemberID(this.txtMemberID.getText());
		
		if (member == null) {
			JOptionPane.showMessageDialog(this, "No library member record exists for the provided MemberID.");
			return;
		}
		
		MemberCheckoutRecordFrame recordsFrame = new MemberCheckoutRecordFrame(member);
	}
}
