package be.pxl.daanvanrobays.pojo;

public class UserAndUsertype {

	private User user;
	private UserType userType;
	public UserAndUsertype(User user, UserType userType) {
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
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
