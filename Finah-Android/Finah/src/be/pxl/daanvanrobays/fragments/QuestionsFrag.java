package be.pxl.daanvanrobays.fragments;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import be.pxl.daanvanrobays.custom.CustomAdapter;
import be.pxl.daanvanrobays.pojo.Question;
import be.pxl.daanvanrobays.rest.RestHelper;

public class QuestionsFrag extends ListFragment {
	private List<Question> questionsList = new ArrayList<Question>();
	private CustomAdapter<Question> custAd;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetQuestions(getActivity()).execute();
		
		custAd = new CustomAdapter<Question>(this.getActivity(), this.questionsList);
		setListAdapter(custAd);
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
				Log.d("test", "updated adapter");
				Toast.makeText(mContext,
						"OnpostExecute",
						Toast.LENGTH_LONG).show();
				custAd.notifyDataSetChanged();
			}
		}
	}
}
