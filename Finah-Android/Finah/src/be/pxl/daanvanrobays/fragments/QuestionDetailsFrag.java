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
import be.pxl.daanvanrobays.pojo.Question;
import be.pxl.daanvanrobays.pojo.Question;
import be.pxl.daanvanrobays.pojo.QuestionAndTheme;
import be.pxl.daanvanrobays.pojo.Theme;
import be.pxl.daanvanrobays.rest.RestHelper;

public class QuestionDetailsFrag extends Fragment {
	public final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;
	private EditText et_title;
	private EditText et_description;
	private EditText et_theme;
	private Button btn_edit;
	private ProgressDialog pDialog;
	private View mContentView = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		inflater = getActivity().getLayoutInflater();
		mContentView = inflater
				.inflate(R.layout.question_details, container, false);

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
		et_theme = (EditText) mContentView.findViewById(R.id.et_theme);
		
		//btn_edit = (Button) mContentView.findViewById(R.id.btn_edit);
		//btn_edit.setOnClickListener(new ButtonHandler());

	}

	@Override
	public void onStart() {
		super.onStart();

		Bundle args = getArguments();
		if (args != null) {
			updateQuestionView(args.getInt(ARG_POSITION));
		} else if (mCurrentPosition != -1) {
			updateQuestionView(mCurrentPosition);
		}
	}

	public void updateQuestionView(int question_id) {
		new getQuestionDetails(getActivity(), question_id).execute();
		mCurrentPosition = question_id;
	}

	public void updateEditTexts(QuestionAndTheme questionAndTheme) {
		et_title.setText(questionAndTheme.getQuestion().getTitle());
		et_description.setText(questionAndTheme.getQuestion().getDescription());
		et_theme.setText(questionAndTheme.getTheme().getTitle());
	}

	/*
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
	*/
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt(ARG_POSITION, mCurrentPosition);
	}

	private class getQuestionDetails extends
			AsyncTask<Void, Integer, QuestionAndTheme> {
		private Context mContext;
		private int mQuestion_id;


		public getQuestionDetails(Context context, int Question_id) {
			mContext = context;
			mQuestion_id = Question_id;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDialog.setTitle("Please wait");
			pDialog.setMessage("Getting question details..");
			pDialog.show();
		}

		@Override
		protected QuestionAndTheme doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Question question = helper.getQuestion(mQuestion_id);
					Theme theme = helper.getTheme(question.getThemeId());
					return new QuestionAndTheme(question, theme);
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

		protected void onPostExecute(QuestionAndTheme result) {
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
