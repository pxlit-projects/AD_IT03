package be.pxl.unitTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import be.pxl.json.ReadUrl;
import be.pxl.objects.UserType;
import be.pxl.settings.SettingClass;

import com.google.gson.Gson;

public class UserTypeUnitTest {
	List<UserType> userTypeList;
	UserType userType;
	
	private String ANSWERURL = new SettingClass().getSiteUrl()+"api/usertype";
	
	
	@Test
	public void test() {

		String result = readtype(4);
		
		assertEquals("Patient", result);
	}
	
	
	
	public String readtype( int number) {

		String json;
		try {
			json = new ReadUrl()
					.read(ANSWERURL);

			Gson gson = new Gson();

			userTypeList = Arrays.asList(gson.fromJson(json,
					UserType[].class));
			userType = userTypeList.get(number-1);
			
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		return userType.getTypeName();

	}

}
