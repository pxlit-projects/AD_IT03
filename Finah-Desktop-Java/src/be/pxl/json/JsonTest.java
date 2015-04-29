package be.pxl.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
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
//		try {
//			String json = new ReadUrl().read("http://finah-backend.cloudapp.net/api/user/108");
//			Gson gson = new Gson();
//			User user = gson.fromJson(json, User.class);
//			System.out.println(user.toString());
			
//			QuestionDb test = new QuestionDb();
//			test.getQuestionByThemeId(2);
			
//			QuestionDb test = new QuestionDb();
//			test.readQuestions();
			
//			AnswerDb test = new AnswerDb();
//			test.readAnswers();
			
			User resu = new User();
			resu.setFirstname("json");
			resu.setLastname("json");
			resu.setEmail("json@json.json");
			resu.setBirthDate(new Date());
			resu.setType(1);
			resu.setLogin("json");
			resu.setPassword("json");
			resu.setStreet("json");
			resu.setTown("json");
			resu.setZipCode(5000);
			resu.setProfilePicture(null);
			new UserDb().addUser(resu);
			System.out.println("Gelukt?");
			System.out.println(resu.toString());
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
