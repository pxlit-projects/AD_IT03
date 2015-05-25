package be.pxl.daanvanrobays.rest;

public class URLS {
	
	private static final String REST_URL = "http://finah-webapi-appdevit03.azurewebsites.net/api";
	
	// GET URLS
	public static final String USERS_URL = REST_URL + "/user";
	public static final String USERTYPES_URL = REST_URL + "/usertype";
	public static final String QUESTIONS_URL = REST_URL + "/question";
	public static final String QUESTIONLISTS_URL = REST_URL + "/questionlist";
	public static final String ANSWERS_URL = REST_URL + "/answer";
	public static final String ANSWERLISTS_URL = REST_URL + "/answerlist";
	public static final String THEMES_URL = REST_URL + "/theme";
	public static final String HASHES_URL = REST_URL + "/hashes";
	
	// GET BY ID URLS
	public static final String USERBYID_URL = REST_URL + "/user/";
	public static final String USERTYPEBYID_URL = REST_URL + "/usertype/";
	public static final String QUESTIONBYID_URL = REST_URL + "/question/";
	public static final String QUESTIONLISTBYID_URL = REST_URL + "/questionlist/";
	public static final String ANSWERBYID_URL = REST_URL + "/answer/";
	public static final String ANSWERLISTBYID_URL = REST_URL + "/answerlist/";
	public static final String THEMEBYID_URL = REST_URL + "/theme/";
	public static final String HASHESBYID_URL = REST_URL + "/hashes/";
	
	
	//GET BY HASH URLS
	public static final String ANSWERLISTBYHASH_URL = REST_URL + "/answerlist/getanswerlistbyhash/";
}
