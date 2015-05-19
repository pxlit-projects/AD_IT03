package be.pxl.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.pxl.objects.Theme;
import be.pxl.settings.SettingClass;

import com.google.gson.Gson;

public class ThemeDb {

	private String URLTHEME = new SettingClass().getSiteUrl() + "api/theme/";

	public List<Theme> readThemes() {
		List<Theme> themeList = new ArrayList<Theme>();
		String json;
		try {
			json = new ReadUrl().read(URLTHEME);

			Gson gson = new Gson();

			themeList = Arrays.asList(gson.fromJson(json,
					Theme[].class));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return themeList;

	}
	
	public Theme readThemeByTitle(String title) {
		Theme theme = new Theme();
		List<Theme> themeList = readThemes();
		for (Theme t : themeList) {
			if (t.getTitle().equalsIgnoreCase(title)) {
				theme = t;
			}
		}
		return theme;
	}
	
	public boolean addTheme(Theme theme) {
		int resultCode = new WriteToWeb().Add(theme, URLTHEME);
		if (resultCode == 201) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateTheme(Theme theme) {
		int resultCode = new WriteToWeb().Update(theme, URLTHEME, theme.getId());
		if (resultCode == 200) {
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteTheme(List<Theme> themes) {
		List<Integer> ids = new ArrayList<Integer>();
		for (Theme theme : themes) {
			ids.add(theme.getId());
		}
		new WriteToWeb().delete(ids, URLTHEME);
	}
	
	
}
