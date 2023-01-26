package user_mgmt;

import javax.swing.JOptionPane;

public class UserController {
	public static void logIn(String id, String password) {
		
		//TODO: Add Validation
		
		User user = User.login(id, password);
		
		if (user == null) {
			JOptionPane.showMessageDialog(null, "Invalid login credentials. Please try again.");
			return;
		}
		
		JOptionPane.showMessageDialog(null, "Login successful.");
		//TODO: Show appropriate window
	}
}
