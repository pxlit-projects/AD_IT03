package be.pxl.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import be.pxl.objects.Question;
import be.pxl.objects.Theme;
import be.pxl.settings.SettingClass;

import com.google.gson.Gson;

public class ThemeDb {

	private List<Theme> themeList;
	private Theme theme;
	private String URLTHEME = new SettingClass().getSiteUrl() + "api/theme/";

	public List<Theme> readThemes() {

		String json;
		try {
			json = new ReadUrl().read(URLTHEME);

			Gson gson = new Gson();
			// Question questions = gson.fromJson(json, Question.class);

			themeList = Arrays.asList(gson.fromJson(json,
					Theme[].class));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return themeList;

	}
	
	
}
