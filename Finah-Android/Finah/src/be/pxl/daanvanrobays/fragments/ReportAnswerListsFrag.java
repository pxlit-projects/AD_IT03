package be.pxl.daanvanrobays.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import be.pxl.daanvanrobays.custom.CustomAdapter;
import be.pxl.daanvanrobays.pojo.Answer;
import be.pxl.daanvanrobays.pojo.AnswerList;
import be.pxl.daanvanrobays.pojo.Question;
import be.pxl.daanvanrobays.pojo.ReportCollection;
import be.pxl.daanvanrobays.pojo.UserType;
import be.pxl.daanvanrobays.rest.RestHelper;

public class ReportAnswerListsFrag extends ListFragment {
	OnReportQuestionListener mCallback;
	public final static String REPORTANSWERLIST_ID_ARGS = "ReportCollection-id";
	public final static String REPORTANSWERLIST_HASH_ARGS = "ReportCollection-hash";
	int mCurrentPosition = -1;
	private String hash = "";
	private ProgressDialog pDialog;
	private List<AnswerList> answerListList = new ArrayList<AnswerList>();
	private CustomAdapter<AnswerList> custAd;

	public interface OnReportQuestionListener {
		public void onReportQuestionSelected(int position);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Bundle b = getArguments();
		mCurrentPosition = b.getInt(REPORTANSWERLIST_ID_ARGS);
		hash = b.getString(REPORTANSWERLIST_HASH_ARGS);

		new GetReportCollection(getActivity(),mCurrentPosition, hash).execute();

		custAd = new CustomAdapter<AnswerList>(this.getActivity(), answerListList);
		setListAdapter(custAd);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mCallback = (OnReportQuestionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnReportQuestionListener");
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mCallback.onReportQuestionSelected(answerListList.get(position).getId());
		getListView().setItemChecked(position, true);
	}

	public void updateReportCollectionsView(int usertype, String hash) {
		new GetReportCollection(getActivity(), usertype, hash).execute();
	}

	private class GetReportCollection extends
			AsyncTask<Void, Integer, List<AnswerList>> {
		private Context mContext;
		private int mUsertype;
		private String mHash;

		public GetReportCollection(Context context, int usertype, String hash) {
			mContext = context;
			mUsertype = usertype;
			mHash = hash;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity(),
					ProgressDialog.THEME_HOLO_DARK);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDialog.setTitle("Please wait");
			pDialog.setMessage("Getting rapport questions..");
			pDialog.show();
		}

		@Override
		protected List<AnswerList> doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Log.d("REPORT", "Getting types");
					UserType usertype = helper.getUserType(mUsertype);
					Log.d("REPORT", "Getting by hash: " + mHash);
					List<AnswerList> answerLists = helper
							.getAnswerListsByHash(mHash);
					Log.d("REPORT", "got by hash");

					List<AnswerList> answerListByUserType = new ArrayList<AnswerList>();
					for (AnswerList answerList : answerLists) {
						if (answerList.getUsertype() == mUsertype) {
							answerListByUserType.add(answerList);
							Log.d("REPORT", "AnswerList by type Object found");
						}
					}
					Log.d("REPORT", "Collection made");
					return answerListByUserType;
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
					answerListList.add(result.get(i));
					Log.d("test", "rows: " + i);
				}
				custAd.notifyDataSetChanged();
				Log.d("test", "updated adapter");
				pDialog.dismiss();
			}
		}
	}
}
