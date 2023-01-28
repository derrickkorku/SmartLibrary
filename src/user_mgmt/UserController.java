package user_mgmt;

public class UserController {
	public static User logIn(String id, String password) {
		return User.login(id, password);
	}
}
