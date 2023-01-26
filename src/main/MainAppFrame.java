package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mem_mgmt.MemberFrame;
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
	JButton btnMembership = new JButton("MEMBERSHIP MANAGEMENT");

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
		
	
		btnMembership.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMembership.addActionListener(this);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnMembership) {
			this.dispose();
			MemberFrame mFrame = new MemberFrame();
			mFrame.setVisible(true);
		}
	}

}
