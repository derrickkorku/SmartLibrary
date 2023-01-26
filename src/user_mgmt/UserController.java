package user_mgmt;

import javax.swing.JOptionPane;

public class UserController {
	public static User logIn(String id, String password) {
		
		//TODO: Add Validation
		
		User user = User.login(id, password);
		
		if (user == null) {
			JOptionPane.showMessageDialog(null, "Invalid login credentials. Please try again.");
			return null;
		}
		
		return user;
	}
}
