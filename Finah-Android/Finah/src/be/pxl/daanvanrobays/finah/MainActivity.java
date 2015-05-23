package be.pxl.daanvanrobays.finah;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import be.pxl.daanvanrobays.pojo.User;
import be.pxl.daanvanrobays.pojo.UserAndUsertype;
import be.pxl.daanvanrobays.pojo.UserType;
import be.pxl.daanvanrobays.rest.RestHelper;

public class MainActivity extends Activity {

	private List<UserType> usertypeInfoList = new ArrayList<UserType>();
	private List<User> userInfoList = new ArrayList<User>();
	private EditText et_username;
	private EditText et_password;
	private Button btn_login;
	private ProgressDialog pDialog;
	public static int userIdToPassToDeliveryFragment;
	private List<UserAndUsertype> loginList = new ArrayList<UserAndUsertype>();
	public static String USER_ID_ARGS = "user-id";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pDialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_DARK);
		pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new ButtonHandler());

		et_username = (EditText) findViewById(R.id.et_username);

		et_password = (EditText) findViewById(R.id.et_password);
	}

	public boolean onTouchEvent(MotionEvent event) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		return true;
	}

	private class ButtonHandler implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(TextUtils.isEmpty(et_username.getText().toString()) || TextUtils.isEmpty(et_password.getText().toString())){
				Toast.makeText(getApplicationContext(), "You need a login and password to sign in!", Toast.LENGTH_SHORT).show();
			} else {
				if (chkStatus()) {
					new getLogin(getApplicationContext()).execute();
				}
			}
		}

	}

	private Boolean chkStatus() {
		final ConnectivityManager connMgr = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		final android.net.NetworkInfo wifi = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		final android.net.NetworkInfo mobile = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (wifi.isAvailable()) {
			return true;
		} else if (mobile.isAvailable()) {
			return true;
		} else {
			Toast.makeText(this, "Please connect to Mobile Network or WiFi!", Toast.LENGTH_SHORT).show();
			return false;
		}
	}

	private class getLogin extends
			AsyncTask<Void, Integer, List<UserAndUsertype>> {
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
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog.setTitle("Please wait");
			pDialog.setMessage("Checking login information..");
			pDialog.show();
		}

		@Override
		protected List<UserAndUsertype> doInBackground(Void... params) {
			try {
				List<UserAndUsertype> logList = loginList;
				RestHelper helper = new RestHelper();
				Log.d("test", "Retrieving users & usertypes");
				List<User> users = helper.getUsers();
				Log.d("test", "Users & usertypes retrieved");
				for (int i = 0; i < users.size(); i++) {
					User user = users.get(i);
					UserType userType = helper.getUserType(user.getType());
					Log.d("userType",
							user.getLogin() + ": "
									+ userType.getDescription());
					logList.add(new UserAndUsertype(user, userType));
				}
				return logList;
			} catch (Exception e) {
				Log.d("ERROR", "" + e.getMessage());
				return null;
			}
		}

		protected void onPostExecute(List<UserAndUsertype> result) {
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

					Collections.sort(usertypeInfoList,
							new Comparator<UserType>() {
								@Override
								public int compare(UserType userType1,
										UserType userType2) {
									// TODO Auto-generated method stub
									Log.d("userTypes", userType1.getId() + " "
											+ userType2.getId());
									int result;
									if (userType1.getId() == userType2.getId()) {
										result = 0;
									} else if (userType1.getId() > userType2
											.getId()) {
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
					} else {
						Toast.makeText(getBaseContext(),
								"This user doesn't have permission to log in",
								Toast.LENGTH_SHORT).show();
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
			pDialog.dismiss();
		}
	}
}