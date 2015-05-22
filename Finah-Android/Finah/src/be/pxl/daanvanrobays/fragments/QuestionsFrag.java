package be.pxl.daanvanrobays.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.support.v4.app.ListFragment;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import be.pxl.daanvanrobays.custom.CustomAdapter;
import be.pxl.daanvanrobays.pojo.Question;
import be.pxl.daanvanrobays.pojo.UserType;
import be.pxl.daanvanrobays.rest.RestHelper;

public class QuestionsFrag extends ListFragment {
	OnQuestionSelectedListener mCallback;
	public final static String QUESTION_ID_ARGS = "question-id";
	int mCurrentPosition = -1;
	private List<Question> questionsList = new ArrayList<Question>();
	private CustomAdapter<Question> custAd;

	public interface OnQuestionSelectedListener {
		public void onQuestionSelected(int position);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetQuestions(getActivity()).execute();
		
		custAd = new CustomAdapter<Question>(this.getActivity(), this.questionsList);
		setListAdapter(custAd);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mCallback = (OnQuestionSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnUserSelectedListener");
		}
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mCallback.onQuestionSelected(questionsList.get(position).getId());
		getListView().setItemChecked(position, true);
	}
	
	public void updateQuestionsView ()
	{
		new GetQuestions(getActivity()).execute();
	}
	
	private class GetQuestions extends AsyncTask<Void, Integer, List<Question>> {
		private Context mContext;

		public GetQuestions(Context context) {
			mContext = context;
		}

		@Override
		protected List<Question> doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Log.d("test", "Retrieving questions");
					List<Question> questions = helper.getQuestions();
					Log.d("test", "questions retrieved");
					return questions;
				} else {
					Toast.makeText(mContext,
							"Check your internet connectivity",
							Toast.LENGTH_LONG).show();
					return null;
				}
			} catch (Exception e) {
				Log.d("ERROR", e.getMessage());
				return null;
			}
		}

		protected void onPostExecute(List<Question> result) {
			Log.d("test", "adding to list");
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					questionsList.add(result.get(i));
					Log.d("test", "rows: " + i);
				}
				Collections.sort(questionsList, new Comparator<Question>() {
					@Override
					public int compare(Question question1, Question question2) {
						// TODO Auto-generated method stub
						int result;
						if (question1.getId() == question2.getId()) {
							result = 0;
						} else if (question1.getId() > question2.getId()) {
							result = 1;
						} else {
							result = -1;
						}
						return result;
					}
				});
				Log.d("test", "updated adapter");
				Toast.makeText(mContext,
						"OnpostExecute",
						Toast.LENGTH_LONG).show();
				custAd.notifyDataSetChanged();
			}
		}
	}
}
