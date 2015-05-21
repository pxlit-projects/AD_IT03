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
import be.pxl.daanvanrobays.pojo.AnswerList;
import be.pxl.daanvanrobays.rest.RestHelper;

public class AnswerListsFrag extends ListFragment {
	private List<AnswerList> answerListsList = new ArrayList<AnswerList>();
	private CustomAdapter<AnswerList> custAd;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetAnswerLists(getActivity()).execute();
		
		custAd = new CustomAdapter<AnswerList>(this.getActivity(), this.answerListsList);
		setListAdapter(custAd);
	}
	
	public void updateAnswerListsView ()
	{
		new GetAnswerLists(getActivity()).execute();
	}
	
	private class GetAnswerLists extends AsyncTask<Void, Integer, List<AnswerList>> {
		private Context mContext;

		public GetAnswerLists(Context context) {
			mContext = context;
		}

		@Override
		protected List<AnswerList> doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Log.d("test", "Retrieving AnswerLists");
					List<AnswerList> answerLists = helper.getAnswerLists();
					Log.d("test", "AnswerLists retrieved");
					return answerLists;
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

		protected void onPostExecute(List<AnswerList> result) {
			Log.d("test", "adding to list");
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					answerListsList.add(result.get(i));
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
