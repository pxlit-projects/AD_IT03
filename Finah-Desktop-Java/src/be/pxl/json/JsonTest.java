package be.pxl.json;

import java.util.Date;
import java.util.List;

import be.pxl.objects.User;


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
	
	}

}
