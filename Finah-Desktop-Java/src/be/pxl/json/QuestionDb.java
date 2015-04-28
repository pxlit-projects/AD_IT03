package be.pxl.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import be.pxl.objects.Question;
import be.pxl.objects.QuestionList;
import be.pxl.objects.User;

import com.google.gson.Gson;

public class QuestionDb {

	List<Question> questionList;
	Question question;

	public List<Question> readQuestions() {

		String json;
		try {
			json = new ReadUrl()
					.read("http://finah-backend.cloudapp.net/api/question");

			Gson gson = new Gson();
			// Question questions = gson.fromJson(json, Question.class);

			List<Question> questionList = Arrays.asList(gson.fromJson(json,
					Question[].class));
			System.out.println(questionList.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionList;

	}

	public Question getQuestion(int Id) {

		String json;
		try {
			json = new ReadUrl()
					.read("http://finah-backend.cloudapp.net/api/question/"
							+ Id);

			Gson gson = new Gson();

			question = gson.fromJson(json, Question.class);
			System.out.println(question.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return question;
	}

}
