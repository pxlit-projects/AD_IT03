package be.pxl.daanvanrobays.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import be.pxl.daanvanrobays.pojo.Answer;
import be.pxl.daanvanrobays.pojo.AnswerList;
import be.pxl.daanvanrobays.pojo.Question;
import be.pxl.daanvanrobays.pojo.QuestionList;
import be.pxl.daanvanrobays.pojo.Theme;
import be.pxl.daanvanrobays.pojo.User;
import be.pxl.daanvanrobays.pojo.UserType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class RestHelper {

	// methods for retrieving Lists from REST
	public ArrayList<User> getUsers() {
		String url = URLS.USERS_URL;
		ArrayList<User> result = null;
		Object bestand = retrieveObject(url);
		if (bestand != null && bestand instanceof Object) {
			result = (ArrayList<User>) bestand;
		}
		return result;
	}

	public ArrayList<UserType> getUserTypes() {
		String url = URLS.USERTYPES_URL;
		ArrayList<UserType> result = null;
		Object bestand = retrieveObject(url);
		if (bestand != null && bestand instanceof Object) {
			result = (ArrayList<UserType>) bestand;
		}
		return result;
	}
	
	public ArrayList<Theme> getThemes() {
		String url = URLS.THEMES_URL;
		ArrayList<Theme> result = null;
		Object bestand = retrieveObject(url);
		if (bestand != null && bestand instanceof Object) {
			result = (ArrayList<Theme>) bestand;
		}
		return result;
	}
	
	public ArrayList<Question> getQuestions() {
		String url = URLS.QUESTIONS_URL;
		ArrayList<Question> result = null;
		Object bestand = retrieveObject(url);
		if (bestand != null && bestand instanceof Object) {
			result = (ArrayList<Question>) bestand;
		}
		return result;
	}
	
	public ArrayList<QuestionList> getQuestionLists() {
		String url = URLS.QUESTIONLISTS_URL;
		ArrayList<QuestionList> result = null;
		Object bestand = retrieveObject(url);
		if (bestand != null && bestand instanceof Object) {
			result = (ArrayList<QuestionList>) bestand;
		}
		return result;
	}
	
	public ArrayList<Answer> getAnswers() {
		String url = URLS.ANSWERS_URL;
		ArrayList<Answer> result = null;
		Object bestand = retrieveObject(url);
		if (bestand != null && bestand instanceof Object) {
			result = (ArrayList<Answer>) bestand;
		}
		return result;
	}
	
	public ArrayList<AnswerList> getAnswerLists() {
		String url = URLS.ANSWERLISTS_URL;
		ArrayList<AnswerList> result = null;
		Object bestand = retrieveObject(url);
		if (bestand != null && bestand instanceof Object) {
			result = (ArrayList<AnswerList>) bestand;
		}
		return result;
	}

	// methods for retrieving single values from REST
	public User getUser(int ID) {
		String url = URLS.USERBYID_URL;
		url += ID;
		User result = null;
		Object bestand = retrieveObject(url);
		if (bestand != null && bestand instanceof User) {
			result = (User) bestand;
		}
		Log.d("Checking date", result.getBirthDate()+"");
		return result;
	}

	public UserType getUserType(int ID) {
		String url = URLS.USERTYPEBYID_URL;
		url += ID;
		UserType result = null;
		Object bestand = retrieveObject(url);
		if (bestand != null && bestand instanceof UserType) {
			result = (UserType) bestand;
		}
		return result;
	}
	
	public Question getQuestion(int ID) {
		String url = URLS.QUESTIONBYID_URL;
		url += ID;
		Question result = null;
		Object bestand = retrieveObject(url);
		if (bestand != null && bestand instanceof Question) {
			result = (Question) bestand;
		}
		return result;
	}
	

	// methods for posting new data;
	public boolean addUser(User newUser) {
		String url = URLS.USERS_URL;
		String json = convertToJSON(newUser);
		boolean result = POST(json, url);
		return result;
	}

	// methods for updating data;
	public boolean updateUser(User oldUser, User newUser) {
		String url = URLS.USERS_URL + oldUser.getId();
		String json = convertToJSON(newUser);
		boolean result = PUT(json, url);
		return result;
	}

	// methods for deleting data;
	public boolean deleteUser(User deleteUser) {
		String url = URLS.USERS_URL + deleteUser.getId();
		boolean result = DELETE(url);
		return result;
	}

	// method for retrieving the JSON object en converting it
	protected Object retrieveObject(String url) {
		String result = GET(url);
		Log.d("result", "" + result);
		Object converted = convertToObject(result, url);

		return converted;
	}

	// method for retrieving inputstream and converting it to JSON String
	public static String GET(String url) {
		InputStream inputStream = null;
		String result = "";
		try {
			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();
			// convert inputstream to string
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}

	// method for posting JSON to given URL
	public static boolean POST(String JSON, String URL) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(URL);
		post.setHeader("content-type", "application/json");

		HttpResponse resp;
		int resultCode = 0;
		try {
			StringEntity entity = new StringEntity(JSON);
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

	// method for putting JSON to given URL
	public static boolean PUT(String JSON, String URL) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPut put = new HttpPut(URL);
		put.setHeader("content-type", "application/json");

		HttpResponse resp;
		int resultCode = 0;
		try {
			StringEntity entity = new StringEntity(JSON);
			put.setEntity(entity);

			resp = httpClient.execute(put);
			resultCode = resp.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (resultCode == 200) {
			return true;
		}
		return false;
	}

	// method for deleting record to given URL
	public static boolean DELETE(String URL) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpDelete delete = new HttpDelete(URL);
		delete.setHeader("content-type", "application/json");

		HttpResponse resp;
		int resultCode = 0;
		try {
			resp = httpClient.execute(delete);
			resultCode = resp.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (resultCode == 200) {
			return true;
		}
		return false;
	}

	// method for getting inputstream to JSON String
	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null) {
			result += line;
		}
		inputStream.close();
		return result;

	}

	// method for converting JSON string to Object;
	private Object convertToObject(String result, String url) {
		Gson gson = new Gson();
		Object converted = null;
		JsonParser parser = new JsonParser();
		JsonArray jArray;
		//check if JSON string is an Array of objects
		if (result.startsWith("[{\"id")){
			if (url == URLS.USERS_URL) {
				Log.d("test", "Converting users");
				jArray = parser.parse(result).getAsJsonArray();
				ArrayList<User> userList = new ArrayList<User>();
				for (JsonElement obj : jArray) {
					User user = gson.fromJson(obj, User.class);
					userList.add(user);
				}
				Log.d("test", "Converted users");
				converted = userList;
			} else if (url == URLS.USERTYPES_URL) {
				jArray = parser.parse(result).getAsJsonArray();
				Log.d("test", "Converting usertypes");
				ArrayList<UserType> userTypeList = new ArrayList<UserType>();
				for (JsonElement obj : jArray) {
					UserType userType = gson.fromJson(obj, UserType.class);
					userTypeList.add(userType);
				}
				Log.d("test", "Converted usertypes");
				converted = userTypeList;
			} else if (url == URLS.THEMES_URL) {
				jArray = parser.parse(result).getAsJsonArray();
				Log.d("test", "Converting themes");
				ArrayList<Theme> themesList = new ArrayList<Theme>();
				for (JsonElement obj : jArray) {
					Theme theme = gson.fromJson(obj, Theme.class);
					themesList.add(theme);
				}
				Log.d("test", "Converted themes");
				converted = themesList;
			} else if (url == URLS.QUESTIONS_URL) {
				jArray = parser.parse(result).getAsJsonArray();
				Log.d("test", "Converting questions");
				ArrayList<Question> questions = new ArrayList<Question>();
				for (JsonElement obj : jArray) {
					Question question = gson.fromJson(obj, Question.class);
					questions.add(question);
				}
				Log.d("test", "Converted questions");
				converted = questions;
			} else if (url == URLS.QUESTIONLISTS_URL) {
				jArray = parser.parse(result).getAsJsonArray();
				Log.d("test", "Converting questionlists");
				ArrayList<QuestionList> questionLists = new ArrayList<QuestionList>();
				for (JsonElement obj : jArray) {
					QuestionList questionlist = gson.fromJson(obj, QuestionList.class);
					questionLists.add(questionlist);
				}
				Log.d("test", "Converted questionlists");
				converted = questionLists;
			} else if (url == URLS.ANSWERS_URL) {
				jArray = parser.parse(result).getAsJsonArray();
				Log.d("test", "Converting answers");
				ArrayList<Answer> answers = new ArrayList<Answer>();
				for (JsonElement obj : jArray) {
					Answer answer = gson.fromJson(obj, Answer.class);
					answers.add(answer);
				}
				Log.d("test", "Converted answers");
				converted = answers;
			} else if (url == URLS.ANSWERLISTS_URL) {
				jArray = parser.parse(result).getAsJsonArray();
				Log.d("test", "Converting answerlists");
				ArrayList<AnswerList> answerLists = new ArrayList<AnswerList>();
				for (JsonElement obj : jArray) {
					AnswerList answerlist = gson.fromJson(obj, AnswerList.class);
					answerLists.add(answerlist);
				}
				Log.d("test", "Converted answerlists");
				converted = answerLists;
			} 
			//Check for single objects
		} else {
			if (url.startsWith(URLS.USERBYID_URL)
					&& result.startsWith("{\"id")) {
				Log.d("test", "Converting one user");
				User user = gson.fromJson(result, User.class);
				converted = user;
				Log.d("test", "Converted one user");
				Log.d("testBirthDate", user.getBirthDate()+"");
			} else if (url.startsWith(URLS.USERTYPEBYID_URL)
					&& result.startsWith("{\"id")) {
				Log.d("test", "Converting one usertype");
				UserType userType = gson.fromJson(result, UserType.class);
				converted = userType;
				Log.d("test", "Converted one usertype");
			} else if (url.startsWith(URLS.QUESTIONBYID_URL)
					&& result.startsWith("{\"id")) {
				Log.d("test", "Converting one question");
				Question question = gson.fromJson(result, Question.class);
				converted = question;
				Log.d("test", "Converted one question");
			}
		}
		return converted;
	}

	// method for converting Object string to JSON;
	private String convertToJSON(Object toConvert) {
		Gson gson = new Gson();
		String json = gson.toJson(toConvert);
		return json;
	}

	public boolean isConnected(Context c) {
		ConnectivityManager connMgr = (ConnectivityManager) c
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		} else {
			return false;
		}
	}
}
