package be.pxl.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.pxl.objects.Question;
import be.pxl.settings.SettingClass;

import com.google.gson.Gson;

public class QuestionDb {

	private List<Question> questionList;
	private Question question;
	private String URLQUESTION = new SettingClass().getSiteUrl() + "api/question/";

	public List<Question> readQuestions() {

		String json;
		try {
			json = new ReadUrl()
					.read(URLQUESTION);

			Gson gson = new Gson();
			// Question questions = gson.fromJson(json, Question.class);

			questionList = Arrays.asList(gson.fromJson(json,
					Question[].class));

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
									
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionByTheme;
	}
	
	public boolean addQuestion(Question question) {
		int resultCode = new WriteToWeb().Add(question, URLQUESTION);
		if (resultCode == 201) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void addQuestions(List<Question> questions) {
		for (Question question : questions) {
			addQuestion(question);
		}
	}
	
	public boolean updateQuestion(Question question) {
		int resultCode = new WriteToWeb().Update(question, URLQUESTION, question.getId());
		if (resultCode == 200) {
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteQuestions(List<Question> questions) {
		List<Integer> ids = new ArrayList<Integer>();
		for (Question question : questions) {
			ids.add(question.getId());
		}
		new WriteToWeb().delete(ids, URLQUESTION);
	}
}
