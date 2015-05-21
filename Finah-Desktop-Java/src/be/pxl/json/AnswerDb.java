package be.pxl.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.pxl.objects.AnswerList;
import be.pxl.settings.SettingClass;

import com.google.gson.Gson;

public class AnswerDb {

	private String ANSWERURL = new SettingClass().getSiteUrl()+"api/answerlist";
		
	public List<AnswerList> readAnswers() {
		List<AnswerList> answerList = new ArrayList<AnswerList>();
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
	
	public List<AnswerList> readAnswersByHash(String hash) {
		List<AnswerList> answerListAll = readAnswers();
		List<AnswerList> answerList = new ArrayList<AnswerList>();
		
		
		for (AnswerList answer : answerListAll) {
			if(hash.equalsIgnoreCase(answer.getHash())) {
				answerList.add(answer);
			}
		}
		return answerList;
	}
	
}
