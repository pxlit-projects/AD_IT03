package be.pxl.daanvanrobays.pojo;

import java.util.List;

public class UsersAndUsertypes {

	private List<User> users;
	private List<UserType> userTypes;
	public UsersAndUsertypes(List<User> users, List<UserType> userTypes) {
		super();
		this.users = users;
		this.userTypes = userTypes;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<UserType> getUserTypes() {
		return userTypes;
	}
	public void setUserTypes(List<UserType> userTypes) {
		this.userTypes = userTypes;
	}
}
