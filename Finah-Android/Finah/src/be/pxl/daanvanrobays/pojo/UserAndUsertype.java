package be.pxl.daanvanrobays.pojo;

public class UserAndUsertype {
	
	private User user;
	private UserType usertype;
	public UserAndUsertype(User user, UserType usertype) {
		super();
		this.user = user;
		this.usertype = usertype;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserType getUsertype() {
		return usertype;
	}
	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

}
