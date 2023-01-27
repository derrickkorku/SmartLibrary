package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import book_mgmt.BookForm;
import checkout.CheckoutForm;
import mem_mgmt.MemberFrame;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainAppFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 4926976640562875295L;
	private JPanel contentPane;
	private JButton btnMembership = new JButton("MEMBERSHIP MANAGEMENT");
	private JButton btnBookCheckOut = new JButton("BOOK CHECKOUTS");
	private JButton btnBookManagement = new JButton("BOOK MANAGEMENT");

	/**
	 * Create the frame.
	 */
	public MainAppFrame() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setTitle("Main Application Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		btnMembership.setBounds(519, 25, 241, 257);
		
	
		btnMembership.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMembership.addActionListener(this);
		contentPane.setLayout(null);
		
		btnBookManagement.addActionListener(this);
		btnBookManagement.setBounds(10, 25, 248, 257);
		btnBookManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(btnBookManagement);
		
		btnBookCheckOut.addActionListener(this);
		btnBookCheckOut.setBounds(268, 23, 241, 260);
		btnBookCheckOut.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(btnBookCheckOut);
		contentPane.add(btnMembership);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		
		if (e.getSource() == this.btnMembership) {
			MemberFrame mFrame = new MemberFrame();
			mFrame.setVisible(true);
			return;
		}
		
		if (e.getSource() == this.btnBookCheckOut) {
			CheckoutForm chkForm = new CheckoutForm();
			chkForm.setVisible(true);
			return;
		}
		
		if (e.getSource() == this.btnBookManagement) {
			BookForm.getInstance().setVisible(true);
			return;
		}
	}

}
