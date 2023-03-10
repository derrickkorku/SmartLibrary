package user_mgmt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import checkout.CheckoutForm;
import dataaccess.DefaultDataInitializer;
import main.AdminDashboard;
import main.MainAppFrame;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame implements ActionListener{
	private static final long serialVersionUID = 3191103133799175689L;
	private JPanel contentPane;
	private JTextField txtLoginID;
	private JPasswordField passwordField;
	private static LoginForm frame;
	private static MainAppFrame mainAppFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Initialize default data
		DefaultDataInitializer.initializeDefaultData();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setResizable(false);
		setTitle("Smart Library LogIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtLoginID = new JTextField();
		txtLoginID.setBounds(61, 61, 316, 36);
		contentPane.add(txtLoginID);
		txtLoginID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(61, 43, 61, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(62, 131, 128, 14);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(61, 149, 316, 45);
		contentPane.add(passwordField);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogIn.setBounds(61, 215, 316, 45);
		btnLogIn.addActionListener(this);
		contentPane.add(btnLogIn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.txtLoginID.getText().trim().equals("") || this.passwordField.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Login Pin and Password are required field");
			return;
		}
		
		User user = UserController.logIn(this.txtLoginID.getText(), this.passwordField.getText());
		
		if (user == null) {
			JOptionPane.showMessageDialog(null, "Invalid login credentials. Please try again.");
			return;
		}
		
		frame.dispose();
		this.showUserFormByAuthorization(user);
	}
	
	private void showUserFormByAuthorization(User user) {
		if (user.getAuthorization().equals(Auth.LIBRARIAN)) {
			CheckoutForm checkoutForm = new CheckoutForm();
			checkoutForm.setVisible(true);
			return;
		}
		
		if (user.getAuthorization().equals(Auth.ADMIN)) {
			AdminDashboard adminForm = new AdminDashboard();
			adminForm.setVisible(true);
			return;
		}
		
		if (user.getAuthorization().equals(Auth.BOTH)) {
			mainAppFrame = new MainAppFrame();
			mainAppFrame.setVisible(true);
			return;
		}
	}
}
