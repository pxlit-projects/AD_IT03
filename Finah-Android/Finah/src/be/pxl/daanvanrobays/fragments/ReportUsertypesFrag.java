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
import be.pxl.daanvanrobays.pojo.UserType;
import be.pxl.daanvanrobays.rest.RestHelper;

public class ReportUsertypesFrag extends ListFragment {
	OnReportUserTypeSelectedListener mCallback;
	public final static String REPORTUSERTYPES_HASH_ARGS = "ReportCollection-hash";
	int mCurrentPosition = -1;
	private ProgressDialog pDialog;
	private String hash = "";
	private List<UserType> reportUserTypesList = new ArrayList<UserType>();
	private CustomAdapter<UserType> custAd;

	public interface OnReportUserTypeSelectedListener {
		public void onReportUserTypeSelected(int position, String hash);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		hash = b.getString(REPORTUSERTYPES_HASH_ARGS);
		Log.d("savedInstanceState", hash);
		new GetReportUserTypes(getActivity()).execute();
		
		custAd = new CustomAdapter<UserType>(this.getActivity(), reportUserTypesList);
		setListAdapter(custAd);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mCallback = (OnReportUserTypeSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnUserTypeSelectedListener");
		}
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.d("onReportUserTypeSelected", this.hash + " " + reportUserTypesList.get(position).getId());
		mCallback.onReportUserTypeSelected(reportUserTypesList.get(position).getId(), this.hash);
		getListView().setItemChecked(position, true);
	}
	
	public void updateReportUserTypesView (String hash)
	{
		this.hash = hash; 
		new GetReportUserTypes(getActivity()).execute();
	}
	
	private class GetReportUserTypes extends AsyncTask<Void, Integer, List<UserType>> {
		private Context mContext;

		public GetReportUserTypes(Context context) {
			mContext = context;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDialog.setTitle("Please wait");
			pDialog.setMessage("Getting rapports..");
			pDialog.show();
		}

		@Override
		protected List<UserType> doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					List<UserType> usertypes = new ArrayList<UserType>();
					for (int i = 3; i <= 4; i++) {
						usertypes.add(helper.getUserType(i));
					}
					return usertypes;
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

		protected void onPostExecute(List<UserType> result) {
			Log.d("test", "adding to list");
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					reportUserTypesList.add(result.get(i));
					Log.d("test", "rows: " + i);
				}
				Log.d("test", "updated adapter");
				custAd.notifyDataSetChanged();
				pDialog.dismiss();
			}
		}
	}
}
