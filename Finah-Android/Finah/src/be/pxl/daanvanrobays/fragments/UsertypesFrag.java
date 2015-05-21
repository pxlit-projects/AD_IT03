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
import be.pxl.daanvanrobays.pojo.UserType;
import be.pxl.daanvanrobays.rest.RestHelper;

public class UsertypesFrag extends ListFragment {

	private List<UserType> usertypesList = new ArrayList<UserType>();
	private CustomAdapter<UserType> custAd;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetUsertypes(getActivity()).execute();
		
		custAd = new CustomAdapter<UserType>(this.getActivity(), this.usertypesList);
		setListAdapter(custAd);
	}
	
	public void updateUsertypesView ()
	{
		new GetUsertypes(getActivity()).execute();
	}
	
	private class GetUsertypes extends AsyncTask<Void, Integer, List<UserType>> {
		private Context mContext;

		public GetUsertypes(Context context) {
			mContext = context;
		}

		@Override
		protected List<UserType> doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Log.d("test", "Retrieving Usertypes");
					List<UserType> usertypes = helper.getUserTypes();
					Log.d("test", "Usertypes retrieved");
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
					usertypesList.add(result.get(i));
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
