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
import be.pxl.daanvanrobays.pojo.Hashes;
import be.pxl.daanvanrobays.rest.RestHelper;

public class ReportFrag extends ListFragment {
	OnReportSelectedListener mCallback;
	public final static String Hashes_ID_ARGS = "Hashes-id";
	int mCurrentPosition = -1;
	private ProgressDialog pDialog;
	private List<Hashes> hashesList = new ArrayList<Hashes>();
	private CustomAdapter<Hashes> custAd;

	public interface OnReportSelectedListener {
		public void onReportSelected(String hash);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetHashes(getActivity()).execute();
		
		custAd = new CustomAdapter<Hashes>(this.getActivity(), this.hashesList);
		setListAdapter(custAd);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mCallback = (OnReportSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnReportSelectedListener");
		}
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		if (hashesList.get(position).getStatus() == 1 ) {
			mCallback.onReportSelected(hashesList.get(position).getHash());
			getListView().setItemChecked(position, true);
		} else {
			Toast.makeText(getActivity(),
					"No report available yet!",
					Toast.LENGTH_SHORT).show();
		}
	}
	
	public void updateReportView ()
	{
		new GetHashes(getActivity()).execute();
	}
	
	private class GetHashes extends AsyncTask<Void, Integer, List<Hashes>> {
		private Context mContext;

		public GetHashes(Context context) {
			mContext = context;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDialog.setTitle("Please wait");
			pDialog.setMessage("Getting reports..");
			pDialog.show();
		}

		@Override
		protected List<Hashes> doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Log.d("test", "Retrieving Hashes");
					List<Hashes> Hashes = helper.getHashes();
					Log.d("test", "Hashes retrieved");
					return Hashes;
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

		protected void onPostExecute(List<Hashes> result) {
			Log.d("test", "adding to list");
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					hashesList.add(result.get(i));
					Log.d("test", "rows: " + i);
				}
				Log.d("test", "updated adapter");
				custAd.notifyDataSetChanged();
				pDialog.dismiss();
			}
		}
	}
}
