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
import be.pxl.daanvanrobays.pojo.QuestionList;
import be.pxl.daanvanrobays.rest.RestHelper;

public class QuestionListsFrag extends ListFragment {
	private List<QuestionList> questionListsList = new ArrayList<QuestionList>();
	private CustomAdapter<QuestionList> custAd;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetQuestionLists(getActivity()).execute();
		
		custAd = new CustomAdapter<QuestionList>(this.getActivity(), this.questionListsList);
		setListAdapter(custAd);
	}
	
	public void updateQuestionListsView ()
	{
		new GetQuestionLists(getActivity()).execute();
	}
	
	private class GetQuestionLists extends AsyncTask<Void, Integer, List<QuestionList>> {
		private Context mContext;

		public GetQuestionLists(Context context) {
			mContext = context;
		}

		@Override
		protected List<QuestionList> doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Log.d("test", "Retrieving QuestionLists");
					List<QuestionList> questionLists = helper.getQuestionLists();
					Log.d("test", "QuestionLists retrieved");
					return questionLists;
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

		protected void onPostExecute(List<QuestionList> result) {
			Log.d("test", "adding to list");
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					questionListsList.add(result.get(i));
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
