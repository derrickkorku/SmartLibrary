package user_mgmt;

import java.io.Serializable;
import java.util.HashMap;

import dataaccess.DataAccessFacade;

final public class User implements Serializable {
	
	private static final long serialVersionUID = 5147265048973262104L;

	private String id;
	private String password;
	private Auth authorization;
	private static HashMap<String, User> users = new DataAccessFacade().readUserMap();
	
	public User(String id, String pass, Auth  auth) {
		this.id = id;
		this.password = pass;
		this.authorization = auth;
	}
	
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public Auth getAuthorization() {
		return authorization;
	}
	
	public static User login(String id, String password) {
		for (String userId : users.keySet()) {
			if (userId.equals(id) && password.equals(users.get(userId).getPassword())) {
				return users.get(userId);
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return "[" + id + ":" + password + ", " + authorization.toString() + "]";
	}
	
}
