package be.pxl.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import be.pxl.objects.Question;
import be.pxl.objects.Theme;

import com.google.gson.Gson;

public class ThemeDb {

	List<Theme> themeList;
	Theme theme;

	public List<Theme> readThemes() {

		String json;
		try {
			json = new ReadUrl()
					.read("http://finah-backend.cloudapp.net/api/theme");

			Gson gson = new Gson();
			// Question questions = gson.fromJson(json, Question.class);

			themeList = Arrays.asList(gson.fromJson(json,
					Theme[].class));
			System.out.println(themeList.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return themeList;

	}
	
	
}
