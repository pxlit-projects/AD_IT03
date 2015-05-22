package be.pxl.daanvanrobays.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import be.pxl.daanvanrobays.finah.R;
import be.pxl.daanvanrobays.pojo.Theme;
import be.pxl.daanvanrobays.rest.RestHelper;

public class ThemeDetailsFrag extends Fragment {
	public final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;
	private EditText et_title;
	private EditText et_description;
	private Button btn_edit;
	private ProgressDialog pDialog;
	private View mContentView = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		inflater = getActivity().getLayoutInflater();
		mContentView = inflater
				.inflate(R.layout.theme_details, container, false);

		if (savedInstanceState != null) {
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		}
		return mContentView;
	}

	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);

		et_title = (EditText) mContentView.findViewById(R.id.et_title);
		et_description = (EditText) mContentView.findViewById(R.id.et_description);

		btn_edit = (Button) mContentView.findViewById(R.id.btn_edit);
		btn_edit.setOnClickListener(new ButtonHandler());

	}

	@Override
	public void onStart() {
		super.onStart();

		Bundle args = getArguments();
		if (args != null) {
			updateThemeView(args.getInt(ARG_POSITION));
		} else if (mCurrentPosition != -1) {
			updateThemeView(mCurrentPosition);
		}
	}

	public void updateThemeView(int theme_id) {
		new getThemeDetails(getActivity(), theme_id).execute();
		mCurrentPosition = theme_id;
	}

	public void updateEditTexts(Theme Theme) {
		et_title.setText(Theme.getTitle());
		et_description.setText(Theme.getDescription());
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

	private class getThemeDetails extends
			AsyncTask<Void, Integer, Theme> {
		private Context mContext;
		private int mTheme_id;


		public getThemeDetails(Context context, int theme_id) {
			mContext = context;
			mTheme_id = theme_id;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Updating details");
			pDialog.show();
		}
		
		@Override
		protected Theme doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Theme Theme = helper.getTheme(mTheme_id);
					return Theme;
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

		protected void onPostExecute(Theme result) {
			Log.d("test", "adding to collection");
			if (result != null) {
				updateEditTexts(result);
			}
			pDialog.dismiss();
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
