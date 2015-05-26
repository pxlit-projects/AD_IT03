package be.pxl.json;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import be.pxl.objects.User;
import be.pxl.settings.SettingClass;

public class LoginService {
	public LoginService() {
	}

	public boolean loginCheck(String login, String password) {
		boolean state = false;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format for md5
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			password = hexString.toString();
		} catch (Exception ex) {
			System.out.print("exx");
		}

		try {

			UserDb users = new UserDb();
			List<User> userList = new ArrayList<User>();
			userList = users.readUsers();

			for (User user : userList) {

				if (user.getLogin().equalsIgnoreCase(login)
						&& user.getPassword().equalsIgnoreCase(password)) {
					new SettingClass().setUser(user.getId());
					state = true;
					return state;
				}
				state = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return state;
	}
}
