package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import book_mgmt.BookForm;
import mem_mgmt.CheckoutStatusFrame;
import mem_mgmt.MemberFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminDashboard extends JFrame implements ActionListener{
	private static final long serialVersionUID = -8070083938234267136L;
	private JPanel contentPane;
	private JButton btnMemberShipManagement = new JButton("Membership Management");
	private JButton btnBookManagement = new JButton("Book Management");
	JButton btnViewBookCheckoutRecords = new JButton("View Book Checkout Records");

	/**
	 * Create the frame.
	 */
	public AdminDashboard() {
		setTitle("Admin Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnMemberShipManagement.addActionListener(this);
		btnMemberShipManagement.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMemberShipManagement.setBounds(10, 49, 235, 170);
		contentPane.add(btnMemberShipManagement);
		
		btnBookManagement.addActionListener(this);
		btnBookManagement.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBookManagement.setBounds(255, 50, 230, 169);
		contentPane.add(btnBookManagement);
		
		btnViewBookCheckoutRecords.addActionListener(this);
		btnViewBookCheckoutRecords.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnViewBookCheckoutRecords.setBounds(495, 54, 198, 163);
		contentPane.add(btnViewBookCheckoutRecords);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		
		if (e.getSource() == this.btnMemberShipManagement) {
			MemberFrame mFrame = new MemberFrame();
			mFrame.setVisible(true);
			return;
		}
		
		if (e.getSource() == btnBookManagement) {
			BookForm.getInstance().setVisible(true);
			return;
		}
		
		if (e.getSource() == btnViewBookCheckoutRecords) {
			CheckoutStatusFrame statusFrame = new CheckoutStatusFrame();
			statusFrame.setVisible(true);
			return;
		}
	}
}
