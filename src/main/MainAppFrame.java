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

public class MainAppFrame extends JFrame {

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
		
		JButton btnMembership = new JButton("MEMBERSHIP MANAGEMENT");
		btnMembership.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMembership.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnBookManagement = new JButton("BOOK MANAGEMENT");
		btnBookManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(btnBookManagement);
		
		JButton btnBookCheckOut = new JButton("BOOK CHECKOUTS");
		btnBookCheckOut.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(btnBookCheckOut);
		contentPane.add(btnMembership);
		
		this.setUser(user);
		System.out.println(user.getAuthorization());
		System.out.println(Auth.ADMIN);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
