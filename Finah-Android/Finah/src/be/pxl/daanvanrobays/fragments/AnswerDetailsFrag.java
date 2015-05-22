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
import be.pxl.daanvanrobays.pojo.Answer;
import be.pxl.daanvanrobays.rest.RestHelper;

public class AnswerDetailsFrag extends Fragment {
	public final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;
	private EditText et_title;
	private Button btn_edit;
	private ProgressDialog pDialog;
	private View mContentView = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		inflater = getActivity().getLayoutInflater();
		mContentView = inflater
				.inflate(R.layout.answer_details, container, false);

		if (savedInstanceState != null) {
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		}
		return mContentView;
	}

	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);

		et_title = (EditText) mContentView.findViewById(R.id.et_title);

		btn_edit = (Button) mContentView.findViewById(R.id.btn_edit);
		btn_edit.setOnClickListener(new ButtonHandler());

	}

	@Override
	public void onStart() {
		super.onStart();

		Bundle args = getArguments();
		if (args != null) {
			updateAnswerView(args.getInt(ARG_POSITION));
		} else if (mCurrentPosition != -1) {
			updateAnswerView(mCurrentPosition);
		}
	}

	public void updateAnswerView(int answer_id) {
		new getAnswerDetails(getActivity(), answer_id).execute();
		mCurrentPosition = answer_id;
	}

	public void updateEditTexts(Answer Answer) {
		et_title.setText(Answer.getTitle());
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

	private class getAnswerDetails extends
			AsyncTask<Void, Integer, Answer> {
		private Context mContext;
		private int mAnswer_id;


		public getAnswerDetails(Context context, int answer_id) {
			mContext = context;
			mAnswer_id = answer_id;
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
		protected Answer doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Answer Answer = helper.getAnswer(mAnswer_id);
					return Answer;
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

		protected void onPostExecute(Answer result) {
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
