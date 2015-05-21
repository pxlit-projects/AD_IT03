package be.pxl.daanvanrobays.fragments;

import java.util.ArrayList;
import java.util.List;

import be.pxl.daanvanrobays.custom.CustomAdapter;
import be.pxl.daanvanrobays.pojo.User;
import be.pxl.daanvanrobays.rest.RestHelper;

import android.app.ListFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class UsersFrag extends ListFragment {
	private List<User> usersList = new ArrayList<User>();
	private CustomAdapter<User> custAd;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetUsers(getActivity()).execute();
		
		custAd = new CustomAdapter<User>(this.getActivity(), this.usersList);
		setListAdapter(custAd);
	}
	
	public void updateUsersView ()
	{
		new GetUsers(getActivity()).execute();
	}
	
	private class GetUsers extends AsyncTask<Void, Integer, List<User>> {
		private Context mContext;

		public GetUsers(Context context) {
			mContext = context;
		}

		@Override
		protected List<User> doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Log.d("test", "Retrieving users");
					List<User> users = helper.getUsers();
					Log.d("test", "Users retrieved");
					return users;
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

		protected void onPostExecute(List<User> result) {
			Log.d("test", "adding to list");
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					usersList.add(result.get(i));
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
