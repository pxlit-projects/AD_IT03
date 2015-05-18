package be.pxl.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import be.pxl.objects.Answer;
import be.pxl.objects.AnswerList;
import be.pxl.objects.Question;
import be.pxl.objects.Rapport;
import be.pxl.objects.Theme;
import be.pxl.objects.User;
import be.pxl.objects.UserType;

public class RapportDB {

	private String URLANSWER = "http://finah-webapi-appdevit03.azurewebsites.net//api/answer";
	private String URLANSWERLIST = "http://finah-webapi-appdevit03.azurewebsites.net//api/answerlist";
	private String URLTHEME = "http://finah-webapi-appdevit03.azurewebsites.net//api/theme";
	private String URLQUESTION = "http://finah-webapi-appdevit03.azurewebsites.net//api/question";
	private String URLUSERTYPE = "http://finah-webapi-appdevit03.azurewebsites.net//api/usertype";

	public RapportDB(){
		
	}
	
	public List<Rapport> getRapports(){
		String jsonAnswer, jsonAnswerList, jsonTheme, jsonQuestion, jsonUserType;
		List<Answer> answers = new ArrayList<Answer>();
		List<AnswerList> answerLists = new ArrayList<AnswerList>();
		List<Theme> themes = new ArrayList<Theme>();
		List<Question> questions =  new ArrayList<Question>();
		List<UserType> usertypes =  new ArrayList<UserType>();
		
		try {
			jsonAnswer = new ReadUrl().read(URLANSWER);
			jsonAnswerList = new ReadUrl().read(URLANSWERLIST);
			jsonTheme = new ReadUrl().read(URLTHEME);
			jsonQuestion = new ReadUrl().read(URLQUESTION);
			jsonUserType = new ReadUrl().read(URLUSERTYPE);
			
			
			
			Gson gson = new Gson();
			answers = Arrays.asList(gson.fromJson(jsonAnswer, Answer[].class));
			answerLists =  Arrays.asList(gson.fromJson(jsonAnswerList, AnswerList[].class));
			themes =  Arrays.asList(gson.fromJson(jsonTheme, Theme[].class));
			questions =  Arrays.asList(gson.fromJson(jsonQuestion, Question[].class));
			usertypes =  Arrays.asList(gson.fromJson(jsonUserType, UserType[].class));
			




		} catch (IOException e) {
			e.printStackTrace();
		}
		return rapports;

	}
	
}
