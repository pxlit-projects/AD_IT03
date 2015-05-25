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
import android.widget.EditText;
import android.widget.Toast;
import be.pxl.daanvanrobays.finah.R;
import be.pxl.daanvanrobays.pojo.Answer;
import be.pxl.daanvanrobays.pojo.AnswerList;
import be.pxl.daanvanrobays.pojo.Question;
import be.pxl.daanvanrobays.pojo.ReportCollection;
import be.pxl.daanvanrobays.pojo.UserType;
import be.pxl.daanvanrobays.rest.RestHelper;

public class ReportQuestionDetailsFrag extends Fragment {
	public final static String ARG_POSITION = "position";
	int mCurrentPosition = -1;
	private EditText et_question;
	private EditText et_answer;
	private EditText et_workpoint;
	private EditText et_questionlist;
	private EditText et_usertype;
	private UserType currentUserType;
	private ProgressDialog pDialog;
	private View mContentView = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		inflater = getActivity().getLayoutInflater();
		mContentView = inflater
				.inflate(R.layout.report_question_details, container, false);

		if (savedInstanceState != null) {
			mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
		}
		return mContentView;
	}

	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);

		et_question = (EditText) mContentView.findViewById(R.id.et_question);
		et_answer = (EditText) mContentView.findViewById(R.id.et_answer);
		et_workpoint = (EditText) mContentView.findViewById(R.id.et_workpoint);
		et_questionlist = (EditText) mContentView.findViewById(R.id.et_questionlist);
		et_usertype = (EditText) mContentView.findViewById(R.id.et_usertype);
	}
	
	@Override
	public void onStart() {
		super.onStart();

		Bundle args = getArguments();
		if (args != null) {
			updateQuestionDetailsView(args.getInt(ARG_POSITION));
		} else if (mCurrentPosition != -1) {
			updateQuestionDetailsView(mCurrentPosition);
		}
	}

	public void updateQuestionDetailsView(int answerlist_id) {
		mCurrentPosition = answerlist_id;
		new getAnswerListDetails(getActivity(), answerlist_id).execute();

	}

	public void updateEditTexts(ReportCollection objects) {
		et_question.setText(objects.getQuestion().getTitle());
		et_answer.setText(objects.getAnswer().getTitle());
		
		switch (objects.getAnswerList().getWorkpoint()) {
		case 0:
			et_workpoint.setText("No help needed");
			break;
		case 1:
			et_workpoint.setText("Help needed");
			break;
		default:
			break;
		}
		et_questionlist.setText("List: " + objects.getAnswerList().getList());
		et_usertype.setText(objects.getUserType().getTypeName());
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt(ARG_POSITION, mCurrentPosition);
	}

	private class getAnswerListDetails extends
			AsyncTask<Void, Integer, ReportCollection> {
		private Context mContext;
		private int mAnswerlist_id;


		public getAnswerListDetails(Context context, int answerlist_id) {
			mContext = context;
			mAnswerlist_id = answerlist_id;
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
		protected ReportCollection doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					AnswerList answerlist = helper.getAnswerList(mAnswerlist_id);
					UserType usertype = helper.getUserType(answerlist.getUsertype());
					Question question = helper.getQuestion(answerlist.getQuestion());
					Answer answer = helper.getAnswer(answerlist.getAnswer());
					return new ReportCollection(question, usertype, answer, answerlist);
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

		protected void onPostExecute(ReportCollection result) {
			Log.d("test", "adding to collection");
			if (result != null) {
				updateEditTexts(result);
				pDialog.dismiss();
			} else {
				Toast.makeText(mContext,
						"Failed to get details",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

}
