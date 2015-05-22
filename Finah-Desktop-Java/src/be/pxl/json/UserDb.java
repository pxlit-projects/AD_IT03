package be.pxl.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import be.pxl.objects.User;
import be.pxl.objects.UserType;
import be.pxl.settings.SettingClass;
import be.pxl.windows.UsersPanel;

import com.google.gson.Gson;

public class UserDb {
	
	private String URLUSER = new SettingClass().getSiteUrl() + "api/user/";
	private String URLUSERTYPE = new SettingClass().getSiteUrl() + "api/usertype/";

	public UserDb() {
	}

	public List<User> readUsers() {
		List<User> users = new ArrayList<User>();
		String json;
		try {
			json = new ReadUrl().read(URLUSER);
			Gson gson = new Gson();
			users = Arrays.asList(gson.fromJson(json, User[].class));

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Server ligt uit, neemt contact op met een admin", "Fout", JOptionPane.ERROR_MESSAGE);
		}
		return users;
	}

	public List<UserType> readUserTypes() {
		
		List<UserType> userTypes = new ArrayList<UserType>();
		String json;
		try {
			json = new ReadUrl().read(URLUSERTYPE);
			Gson gson = new Gson();
			userTypes = Arrays.asList(gson.fromJson(json, UserType[].class));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return userTypes;
	}
	
	public int getTypeIdByLogin(String login) {
		int typeId = 0;
		List<User> users = readUsers();
		for (User user : users) {
			if (user.getLogin().equals(login)) {
				typeId = user.getType();
			}
		}
		return typeId;
	}
	
	public boolean addUser(User user) {
		int resultCode = new WriteToWeb().Add(user, URLUSER);
		if (resultCode == 201) {
			return true;
		}
		return false;
	}
	
	public void deleteUser(List<User> users, UsersPanel usersPanel) {
		
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < users.size(); i++) {
			ids.add(users.get(i).getId());
		}
		new WriteToWeb().delete(ids, URLUSER);
		usersPanel.refreshTable();
		
	}

	public void updateUser(User user) {
		new WriteToWeb().Update(user, URLUSER, user.getId());
	}

}