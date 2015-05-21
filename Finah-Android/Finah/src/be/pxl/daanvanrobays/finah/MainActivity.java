package be.pxl.daanvanrobays.finah;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import be.pxl.daanvanrobays.pojo.LoginCollection;
import be.pxl.daanvanrobays.pojo.User;
import be.pxl.daanvanrobays.pojo.UserType;
import be.pxl.daanvanrobays.rest.RestHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private List<UserType> usertypeInfoList = new ArrayList<UserType>();
	private List<User> userInfoList = new ArrayList<User>();
	private EditText et_username;
	private EditText et_password;
	private Button btn_login;
	public static int userIdToPassToDeliveryFragment;
	private List<LoginCollection> loginList = new ArrayList<LoginCollection>();
	public static String USER_ID_ARGS = "user-id";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new ButtonHandler());

		et_username = (EditText) findViewById(R.id.et_username);

		et_password = (EditText) findViewById(R.id.et_password);
	}

	private class ButtonHandler implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new getLogin(getApplicationContext()).execute();
		}

	}

	private class getLogin extends
			AsyncTask<Void, Integer, List<LoginCollection>> {
		private Context mContext;

		public getLogin(Context context) {
			mContext = context;
		}

		private String md5(String in) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("MD5");
				digest.reset();
				digest.update(in.getBytes());
				byte[] a = digest.digest();
				int len = a.length;
				StringBuilder sb = new StringBuilder(len << 1);
				for (int i = 0; i < len; i++) {
					sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
					sb.append(Character.forDigit(a[i] & 0x0f, 16));
				}
				return sb.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected List<LoginCollection> doInBackground(Void... params) {
			try {
				List<LoginCollection> logList = loginList;
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Log.d("test", "Retrieving users & usertypes");
					List<User> users = helper.getUsers();
					Log.d("test", "Users & usertypes retrieved");
					for (int i = 0; i < users.size(); i++) {
						User user = users.get(i);
						UserType userType = helper.getUserType(user.getType());
						Log.d("userType",
								user.getLogin() + ": " + userType.getDescription());
						logList.add(new LoginCollection(user, userType));
					}
					return logList;
				} else {
					Toast.makeText(mContext,
							"Check your internet connectivity",
							Toast.LENGTH_LONG).show();
					return null;
				}
			} catch (Exception e) {
				Log.d("ERROR", ""+e.getMessage());
				return null;
			}
		}

		protected void onPostExecute(List<LoginCollection> result) {
			Log.d("test", "adding to list");
			userInfoList = new ArrayList<User>();
			usertypeInfoList = new ArrayList<UserType>();

			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					userInfoList.add(result.get(i).getUser());
					UserType userType = result.get(i).getUserType();
					if (usertypeInfoList.contains(userType)) {
						Log.d("userType", "Duplicate avoided");
					} else {
						usertypeInfoList.add(userType);
						Log.d("userType", "userType added");
					}
				}
				Log.d("userType", "user & userType list filled");

				String[] curLogin = new String[2];
				String[] serverLogin = new String[3];
				curLogin[0] = et_username.getText().toString();
				String passHashed = md5(et_password.getText().toString());
				curLogin[1] = passHashed;
				Log.d("userType", "" + curLogin[1]);
				Intent lvInt = null;

				for (User user : userInfoList) {

					if (user.getLogin().equals(curLogin[0])
							&& user.getPassword().equals(curLogin[1])) {

						serverLogin[0] = user.getLogin();
						serverLogin[1] = user.getPassword();
						serverLogin[2] = user.getType() + "";
						Log.d("userType", "User found: " + serverLogin[0]);
						Log.d("userType", "User found: " + serverLogin[1]);
						Log.d("userType", "User found: " + serverLogin[2]);
						userIdToPassToDeliveryFragment = user.getId();
					}
				}

				if (serverLogin[0] != null || serverLogin[1] != null
						|| serverLogin[2] != null) {
					Log.d("userType", "not null");

					Collections.sort(usertypeInfoList, new Comparator<UserType>() {
						@Override
						public int compare(UserType userType1, UserType userType2) {
							// TODO Auto-generated method stub
							Log.d("userTypes", userType1.getId() + " " + userType2.getId());
							int result;
							if (userType1.getId() == userType2.getId()) {
								result = 0;
							} else if (userType1.getId() > userType2.getId()) {
								result = 1;
							} else {
								result = -1;
							}
							return result;
						}
					});

					if (serverLogin[2].equals(usertypeInfoList.get(0).getId()
							+ "")) {
						lvInt = new Intent(getApplicationContext(),
								AdminActivity.class);
						Log.d("userType", "Calling admin intent from after");
					} else if (serverLogin[2].equals(usertypeInfoList.get(1)
							.getId() + "")) {
						lvInt = new Intent(getApplicationContext(),
								DocterActivity.class);
						lvInt.putExtra(USER_ID_ARGS,
								userIdToPassToDeliveryFragment);
						Log.d("userType", "Calling docter intent from after");
					} else {
						Log.d("userType", "Nothing found");
					}
					if (lvInt != null) {
						startActivity(lvInt);
					}
				} else {
					Toast.makeText(getBaseContext(),
							"Incorrect username or password. Please retry",
							Toast.LENGTH_SHORT).show();
				}
			}
		}

	}
}
