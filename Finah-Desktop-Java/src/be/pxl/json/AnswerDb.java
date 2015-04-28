package be.pxl.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import be.pxl.objects.Answer;
import be.pxl.objects.AnswerList;
import be.pxl.objects.Question;

import com.google.gson.Gson;

public class AnswerDb {

	List<Answer> answerList;
	Answer answer;

	public List<Answer> readAnswers() {

		String json;
		try {
			json = new ReadUrl()
					.read("http://finah-backend.cloudapp.net/api/answerlist");

			Gson gson = new Gson();
			// Question questions = gson.fromJson(json, Question.class);

			answerList = Arrays.asList(gson.fromJson(json,
					Answer[].class));
			System.out.println(answerList.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answerList;

	}
	
}
