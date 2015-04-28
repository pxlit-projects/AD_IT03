package be.pxl.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import be.pxl.objects.User;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void addUser(User user) {

	}

	public void deleteUsers(List<User> users) {

	}

	public void updateUser(User user) {

	}

}
