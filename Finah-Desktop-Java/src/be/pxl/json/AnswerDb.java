package be.pxl.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import be.pxl.objects.AnswerList;
import be.pxl.settings.SettingClass;

import com.google.gson.Gson;

public class AnswerDb {

	List<AnswerList> answerList;
	private String ANSWERURL = new SettingClass().getSiteUrl()+"api/answerlist";
	public List<AnswerList> readAnswers() {

		String json;
		try {
			json = new ReadUrl()
					.read(ANSWERURL);

			Gson gson = new Gson();

			answerList = Arrays.asList(gson.fromJson(json,
					AnswerList[].class));
			
		

		} catch (IOException e) {
			e.printStackTrace();
		}
		return answerList;

	}
	
}
