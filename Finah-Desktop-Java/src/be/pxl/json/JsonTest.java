package be.pxl.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import be.pxl.json.ReadUrl;
import be.pxl.objects.User;
import be.pxl.objects.UserType;

import com.google.gson.Gson;

public class JsonTest {

	public JsonTest() {
		// TODO Auto-generated constructor stub
	}
	
	static class Item {
	    String title;
	    String link;
	    String description;
	}

	static class Page {
	    String title;
	    String link;
	    String description;
	    String language;
	    List<Item> items;
	}

	public static void main(String[] args) {
		try {
			String json = new ReadUrl().read("http://finah-backend.cloudapp.net/api/user/88");
			Gson gson = new Gson();
			User user = gson.fromJson(json, User.class);
			System.out.println(user.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
