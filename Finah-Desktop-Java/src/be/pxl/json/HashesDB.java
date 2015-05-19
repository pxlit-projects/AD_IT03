package be.pxl.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import be.pxl.objects.AnswerList;
import be.pxl.objects.Hashes;
import be.pxl.settings.SettingClass;

public class HashesDB {
	
	List<Hashes> hashesList;
	private String ANSWERURL = new SettingClass().getSiteUrl()+"api/hashes";

	
	public List<Hashes> readHashes() {

		String json;
		try {
			json = new ReadUrl()
					.read(ANSWERURL);

			Gson gson = new Gson();

			hashesList = Arrays.asList(gson.fromJson(json,
					Hashes[].class));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hashesList;

	}

	
}
