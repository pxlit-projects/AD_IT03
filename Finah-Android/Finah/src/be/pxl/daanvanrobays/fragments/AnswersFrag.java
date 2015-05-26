package be.pxl.daanvanrobays.fragments;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.ListFragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import be.pxl.daanvanrobays.custom.CustomAdapter;
import be.pxl.daanvanrobays.fragments.UsertypesFrag.OnUsertypeSelectedListener;
import be.pxl.daanvanrobays.pojo.Answer;
import be.pxl.daanvanrobays.rest.RestHelper;

public class AnswersFrag extends ListFragment {
	OnAnswerSelectedListener mCallback;
	public final static String ANSWER_ID_ARGS = "answer-id";
	int mCurrentPosition = -1;
	private ProgressDialog pDialog;
	private List<Answer> answersList = new ArrayList<Answer>();
	private CustomAdapter<Answer> custAd;

	public interface OnAnswerSelectedListener {
		public void onAnswerSelected(int position);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetAnswers(getActivity()).execute();
		
		custAd = new CustomAdapter<Answer>(this.getActivity(), this.answersList);
		setListAdapter(custAd);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mCallback = (OnAnswerSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnAnswerSelectedListener");
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mCallback.onAnswerSelected(answersList.get(position).getId());
		getListView().setItemChecked(position, true);
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
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDialog.setTitle("Please wait");
			pDialog.setMessage("Getting answers..");
			pDialog.show();
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
				custAd.notifyDataSetChanged();
			}
			pDialog.dismiss();
		}
	}
}
