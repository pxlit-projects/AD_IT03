package be.pxl.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

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

		} catch (IOException e) {
			System.out.println("Server is offline! Neem contact op met de admin.");
//			e.printStackTrace();
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

	public void addUser(User user) {
		URL url;
		try {
			url = new URL("http://finah-backend.cloudapp.net/api/user/"
					+ user.getId());
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			OutputStream out = httpCon.getOutputStream();

			Gson gson = new Gson();
			JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
			writer.setIndent("  ");
//			writer.beginArray();
			gson.toJson(user, User.class, writer);

//			writer.endArray();
			writer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}


	public void deleteUsers(List<User> users) {
		
	}

	public void updateUser(User user) {
		
	}

}
