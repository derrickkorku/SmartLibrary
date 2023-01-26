package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import user_mgmt.Auth;
import user_mgmt.User;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainAppFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private User user;

	/**
	 * Create the frame.
	 */
	public MainAppFrame(User user) {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setTitle("Main Application Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		if (this.getUser().getAuthorization() == Auth.ADMIN || this.getUser().getAuthorization() == Auth.BOTH) {
			JButton btnMembership = new JButton("MEMBERSHIP MANAGEMENT");
			btnMembership.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnMembership.addActionListener(this);
			
			contentPane.add(btnMembership);
		}
		
		if (this.getUser().getAuthorization() == Auth.ADMIN || this.getUser().getAuthorization() == Auth.BOTH) {
			JButton btnBookManagement = new JButton("BOOK MANAGEMENT");
			btnBookManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnBookManagement.addActionListener(this);
			
			contentPane.add(btnBookManagement);
		}
		
		
		if (this.getUser().getAuthorization() == Auth.LIBRARIAN || this.getUser().getAuthorization() == Auth.BOTH) {
			JButton btnBookCheckOut = new JButton("BOOK CHECKOUTS");
			btnBookCheckOut.setFont(new Font("Tahoma", Font.BOLD, 16));
			
			btnBookCheckOut.addActionListener(this);
			contentPane.add(btnBookCheckOut);
		}
		
		this.setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
