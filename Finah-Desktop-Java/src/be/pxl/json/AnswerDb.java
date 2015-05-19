package be.pxl.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import be.pxl.objects.Answer;
import be.pxl.objects.AnswerList;
import be.pxl.objects.Question;

import com.google.gson.Gson;

public class AnswerDb {

	List<AnswerList> answerList;

	public List<AnswerList> readAnswers() {

		String json;
		try {
			json = new ReadUrl()
					.read("http://finah-backend.cloudapp.net/api/answerlist");

			Gson gson = new Gson();

			answerList = Arrays.asList(gson.fromJson(json,
					AnswerList[].class));
			
		

		} catch (IOException e) {
			e.printStackTrace();
		}
		return answerList;

	}
	
}
