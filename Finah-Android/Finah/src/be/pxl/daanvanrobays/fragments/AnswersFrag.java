package be.pxl.daanvanrobays.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import be.pxl.daanvanrobays.custom.CustomAdapter;
import be.pxl.daanvanrobays.pojo.Answer;
import be.pxl.daanvanrobays.rest.RestHelper;

public class AnswersFrag extends ListFragment {
	private List<Answer> answersList = new ArrayList<Answer>();
	private CustomAdapter<Answer> custAd;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetAnswers(getActivity()).execute();
		
		custAd = new CustomAdapter<Answer>(this.getActivity(), this.answersList);
		setListAdapter(custAd);
	}
	
	public void updateAnswersView ()
	{
		new GetAnswers(getActivity()).execute();
	}
	
	private class GetAnswers extends AsyncTask<Void, Integer, List<Answer>> {
		private Context mContext;

		public GetAnswers(Context context) {
			mContext = context;
		}

		@Override
		protected List<Answer> doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Log.d("test", "Retrieving Answers");
					List<Answer> answers = helper.getAnswers();
					Log.d("test", "Answers retrieved");
					return answers;
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

		protected void onPostExecute(List<Answer> result) {
			Log.d("test", "adding to list");
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					answersList.add(result.get(i));
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
