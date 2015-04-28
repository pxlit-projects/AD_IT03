package be.pxl.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import be.pxl.objects.User;
import be.pxl.objects.UserType;

public class UserDb {

	public UserDb() {
		// TODO Auto-generated constructor stub
	}

	public List<User> readUsers() {
		List<User> users = new ArrayList<User>();
		String json;
		try {
			json = new ReadUrl()
					.read("http://finah-backend.cloudapp.net/api/user");
			Gson gson = new Gson();
			users = Arrays.asList(gson.fromJson(json, User[].class));
			for (User user : users) {
				System.out.println(user.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public List<UserType> readUserTypes() {
		List<UserType> userTypes = new ArrayList<UserType>();
		String json;
		try {
			json = new ReadUrl()
					.read("http://finah-backend.cloudapp.net/api/usertype");
			Gson gson = new Gson();
			userTypes = Arrays.asList(gson.fromJson(json, UserType[].class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (UserType userType : userTypes) {
			System.out.println(userType.toString());
		}
		return userTypes;
		
	}
	
	public int getTypeIdByLogin(String login) {
		int typeId = 0;
		List<User> users = readUsers();
		for (User user : users) {
			if(user.getLogin().equals(login)) {
				typeId = user.getType();
			}
		}
		return typeId;
	}

	public void addUser(User user) {

	}

	public void deleteUsers(List<User> users) {

	}

	public void updateUser(User user) {

	}

}
