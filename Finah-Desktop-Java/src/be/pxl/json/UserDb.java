package be.pxl.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import be.pxl.objects.User;
import be.pxl.objects.UserType;
import be.pxl.settings.SettingClass;
import be.pxl.windows.UsersPanel;

import com.google.gson.Gson;

public class UserDb {
	
	private String URLUSER = new SettingClass().getSiteUrl() + "api/user/";
	private String URLUSERTYPE = new SettingClass().getSiteUrl() + "api/usertype/";

	public UserDb() {
		// TODO Auto-generated constructor stub 
	}

	public List<User> readUsers() {
		List<User> users = new ArrayList<User>();
		String json;
		try {
			json = new ReadUrl().read(URLUSER);
			Gson gson = new Gson();
			users = Arrays.asList(gson.fromJson(json, User[].class));

		} catch (IOException e) {
			e.printStackTrace();
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
//		HttpClient httpClient = HttpClientBuilder.create().build();
//		HttpPost post = new HttpPost(URLUSER);
//		post.setHeader("content-type", "application/json");
//
//		HttpResponse resp;
//		int resultCode = 0;
//		try {
//			StringEntity entity = new StringEntity(convertToJSON(user));
//			post.setEntity(entity);
//
//			resp = httpClient.execute(post);
//			resultCode = resp.getStatusLine().getStatusCode();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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
//		HttpClient httpClient = HttpClientBuilder.create().build();
//		HttpPost post = new HttpPost(URLUSER + "/" + user.getId());
//		post.setHeader("content-type", "application/json");
//
//		try {
//			StringEntity entity = new StringEntity(convertToJSON(user));
//			post.setEntity(entity);
//			httpClient.execute(post);
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}