package be.pxl.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import be.pxl.objects.Question;
import be.pxl.objects.QuestionList;
import be.pxl.objects.User;
import be.pxl.settings.SettingClass;

import com.google.gson.Gson;

public class QuestionDb {

	List<Question> questionList;
	Question question;
	String URLQUESTION = new SettingClass().getSiteUrl() + "api/question/";

	public List<Question> readQuestions() {

		String json;
		try {
			json = new ReadUrl()
					.read(URLQUESTION);

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

	public Question getQuestion(int id) {

		String json;
		try {
			json = new ReadUrl()
					.read(URLQUESTION + id);

			Gson gson = new Gson();

			question = gson.fromJson(json, Question.class);
			System.out.println(question.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return question;
	}
	

	public List<Question>  getQuestionByThemeId(int themeId) {
		List<Question> questionByTheme = new ArrayList<Question>();
		String json;
		try {
			json = new ReadUrl()
					.read(URLQUESTION);

			Gson gson = new Gson();

			List<Question> questionList = Arrays.asList(gson.fromJson(json,Question[].class));
			
			for (Question question : questionList) {
				if(question.getThemeId() == themeId){
					questionByTheme.add(question);
				}
			}
									
			
			System.out.println(questionByTheme.toString());
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionByTheme;
	}
	
	public <T> boolean addQuestion(T question) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(URLQUESTION);
		post.setHeader("content-type", "application/json");

		HttpResponse resp;
		int resultCode = 0;
		try {
			StringEntity entity = new StringEntity(convertToJSON(question));
			post.setEntity(entity);

			resp = httpClient.execute(post);
			resultCode = resp.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (resultCode == 201) {
			return true;
		}
		return false;
	}
	
	private String convertToJSON(Object toConvert) {
		Gson gson = new Gson();
		String json = gson.toJson(toConvert);
		return json;
	}
}
