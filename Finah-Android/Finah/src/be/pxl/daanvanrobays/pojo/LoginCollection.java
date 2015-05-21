package be.pxl.daanvanrobays.pojo;

public class LoginCollection {

	private User user;
	private UserType userType;
	public LoginCollection(User user, UserType userType) {
		super();
		this.user = user;
		this.userType = userType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserType getRole() {
		return userType;
	}
	public void setRole(UserType userType) {
		this.userType = userType;
	}
}
