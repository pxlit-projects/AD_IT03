package be.pxl.daanvanrobays.fragments;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import be.pxl.daanvanrobays.finah.DateDialogFragment;
import be.pxl.daanvanrobays.finah.R;
import be.pxl.daanvanrobays.pojo.UserAndUsertype;
import be.pxl.daanvanrobays.pojo.UsersAndUsertypes;
import be.pxl.daanvanrobays.pojo.User;
import be.pxl.daanvanrobays.pojo.UserType;
import be.pxl.daanvanrobays.rest.RestHelper;

public class UserDetailsFrag extends Fragment {
	public final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;
	private EditText et_login;
	private EditText et_lastname;
	private EditText et_firstname;
	private EditText et_email;
	private EditText et_usertype;
	private EditText et_street;
	private EditText et_town;
	private EditText et_zipcode;
	private EditText et_birthdate;
	private Button btn_edit;
	private User currentUser;
	private UserType currentUserType;
	private ProgressDialog pDialog;
	private View mContentView = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		inflater = getActivity().getLayoutInflater();
		mContentView = inflater
				.inflate(R.layout.user_details, container, false);

		if (savedInstanceState != null) {
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		}
		return mContentView;
	}

	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);

		et_login = (EditText) mContentView.findViewById(R.id.et_login);
		et_lastname = (EditText) mContentView.findViewById(R.id.et_lastname);
		et_firstname = (EditText) mContentView.findViewById(R.id.et_firstname);
		et_email = (EditText) mContentView.findViewById(R.id.et_email);
		et_usertype = (EditText) mContentView.findViewById(R.id.et_usertype);
		et_street = (EditText) mContentView.findViewById(R.id.et_street);
		et_town = (EditText) mContentView.findViewById(R.id.et_town);
		et_zipcode = (EditText) mContentView.findViewById(R.id.et_zipcode);
		et_birthdate = (EditText) mContentView.findViewById(R.id.et_birthdate);
		
		//btn_edit = (Button) mContentView.findViewById(R.id.btn_edit);

		//btn_edit.setOnClickListener(new ButtonHandler());

	}

	@Override
	public void onStart() {
		super.onStart();

		Bundle args = getArguments();
		if (args != null) {
			updateUserView(args.getInt(ARG_POSITION));
		} else if (mCurrentPosition != -1) {
			updateUserView(mCurrentPosition);
		}
	}

	public void updateUserView(int user_id) {
		new getUserDetails(getActivity(), user_id).execute();
		mCurrentPosition = user_id;
	}

	public void updateEditTexts(UserAndUsertype types) {
		currentUser = types.getUser();
		currentUserType = types.getUsertype();
		
		et_login.setText(currentUser.getLogin());
		et_lastname.setText(currentUser.getLastname());
		et_firstname.setText(currentUser.getFirstname());
		et_email.setText(currentUser.getEmail());
		et_usertype.setText(currentUserType.getTypeName());
		et_street.setText(currentUser.getStreet());
		et_town.setText(currentUser.getTown());
		et_zipcode.setText(currentUser.getZipcode()+"");
		et_birthdate.setText(currentUser.getBirthdate()+"");
		
		String myFormat = "dd-MM-yyyy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		et_birthdate.setText(sdf.format(currentUser.getBirthdate()));
	}

	private class DateHandler implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText currentEditText = (EditText) v;
			DateDialogFragment datePicker = new DateDialogFragment(
					currentEditText);
			datePicker.show(getActivity().getFragmentManager(), "showDate");
		}
	}
	/*
	private class ButtonHandler implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Button b = (Button) v;
			int buttonID = b.getId();
			if (buttonID == R.id.btn_edit) {
				int usertypeId = currentUserType.getId();
				currentUser.setLogin(et_login.getText().toString());
				currentUser.setLastname(et_lastname.getText().toString());
				currentUser.setFirstname(et_firstname.getText().toString());
				currentUser.setEmail(et_email.getText().toString());
				currentUser.setType(usertypeId);
				currentUser.setStreet(et_street.getText().toString());
				currentUser.setTown(et_town.getText().toString());
				//currentUser.setLogin(et_login.getText().toString());
				//currentUser.setLogin(et_login.getText().toString());
				
				new updateUserDetails(getActivity()).execute();
			}
		}
	}
	*/

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt(ARG_POSITION, mCurrentPosition);
	}

	private class getUserDetails extends
			AsyncTask<Void, Integer, UserAndUsertype> {
		private Context mContext;
		private int mUser_id;


		public getUserDetails(Context context, int user_id) {
			mContext = context;
			mUser_id = user_id;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDialog.setTitle("Please wait");
			pDialog.setMessage("Getting user details..");
			pDialog.show();
		}
		
		@Override
		protected UserAndUsertype doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					User user = helper.getUser(mUser_id);
					UserType usertype = helper.getUserType(user.getType());
					return new UserAndUsertype(user, usertype);
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

		protected void onPostExecute(UserAndUsertype result) {
			Log.d("test", "adding to collection");
			if (result != null) {
				Log.d("test", Integer.toString(result.getUser().getZipcode()));
				updateEditTexts(result);
				pDialog.dismiss();
			} else {
				Toast.makeText(mContext,
						"Failed to get details",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
	/*
	private class updateUserDetails extends
			AsyncTask<Void, Integer, Boolean> {
		private Context mContext;

		public updateUserDetails(Context context) {
			mContext = context;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Updating information");
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Log.d("Updating", "Updating user");
					helper.updateUser(currentUser);
					Log.d("Updating", "Updated user");
					return true;
				} else {
					Toast.makeText(mContext,
							"Check your internet connectivity",
							Toast.LENGTH_LONG).show();
					return false;
				}
			} catch (Exception e) {
				Log.d("ERROR", "err; " + e.getMessage());
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(mContext, "Changes saved", Toast.LENGTH_SHORT)
					.show();
			pDialog.dismiss();
		}
	}
	*/

}
