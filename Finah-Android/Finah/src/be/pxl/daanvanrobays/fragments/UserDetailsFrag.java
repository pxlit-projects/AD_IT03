package be.pxl.daanvanrobays.fragments;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import be.pxl.daanvanrobays.pojo.LoginCollection;
import be.pxl.daanvanrobays.pojo.User;
import be.pxl.daanvanrobays.pojo.UserType;
import be.pxl.daanvanrobays.rest.RestHelper;

public class UserDetailsFrag extends Fragment {
	public final static String ARG_POSITION = "position";
	private List<LoginCollection> typesList = new ArrayList<LoginCollection>();
	int mCurrentPosition = -1;
	private EditText et_login;
	private EditText et_name;
	private EditText et_email;
	private EditText et_usertype;
	private EditText et_street;
	private EditText et_town;
	private EditText et_zipcode;
	private EditText et_birthdate;
	private Button btn_edit;
	private View mContentView = null;
	private ProgressDialog pDialog;

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
		et_name = (EditText) mContentView.findViewById(R.id.et_name);
		et_email = (EditText) mContentView.findViewById(R.id.et_email);
		et_usertype = (EditText) mContentView.findViewById(R.id.et_usertype);
		et_street = (EditText) mContentView.findViewById(R.id.et_street);
		et_town = (EditText) mContentView.findViewById(R.id.et_town);
		et_zipcode = (EditText) mContentView.findViewById(R.id.et_zipcode);
		et_birthdate = (EditText) mContentView.findViewById(R.id.et_birthdate);

		et_birthdate.setOnClickListener(new DateHandler());

		btn_edit = (Button) mContentView.findViewById(R.id.btn_edit);

		btn_edit.setOnClickListener(new ButtonHandler());

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

	public void updateEditTexts(LoginCollection types) {
		et_login.setText(types.getUser().getLogin());
		et_name.setText(types.getUser().getLastname() + " " + types.getUser().getFirstname());
		et_email.setText(types.getUser().getEmail());
		et_usertype.setText(types.getUserType().getTypeName());
		et_street.setText(types.getUser().getStreet());
		et_town.setText(types.getUser().getTown());
		et_zipcode.setText(types.getUser().getZipCode()+"");
		et_birthdate.setText(types.getUser().getBirthDate()+"");
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

	private class ButtonHandler implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Button b = (Button) v;
			int buttonID = b.getId();
			if (buttonID == R.id.btn_edit) {
				//new updateDeliveryDetails(getActivity()).execute();
			}
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt(ARG_POSITION, mCurrentPosition);
	}

	private class getUserDetails extends
			AsyncTask<Void, Integer, LoginCollection> {
		private Context mContext;
		private int mUser_id;


		public getUserDetails(Context context, int user_id) {
			mContext = context;
			mUser_id = user_id;
		}

		@Override
		protected LoginCollection doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					User user = helper.getUser(mUser_id);
					UserType usertype = helper.getUserType(user.getType());
					return new LoginCollection(user, usertype);
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

		protected void onPostExecute(LoginCollection result) {
			Log.d("test", "adding to collection");
			if (result != null) {
				Log.d("test", "updated collectionlist");
				updateEditTexts(result);
			}
		}
	}
	/* hier komt een updateMethod voor user details
	private class updateDeliveryDetails extends
			AsyncTask<Void, Integer, Boolean> {
		private Context mContext;

		public updateDeliveryDetails(Context context) {
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
					helper.updateDelivery(delivery);
					helper.updateCustomer(customer);
					helper.updateOrder(order);
					helper.updateContact(contact);
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
