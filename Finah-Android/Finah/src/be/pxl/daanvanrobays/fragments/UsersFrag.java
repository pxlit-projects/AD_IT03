package be.pxl.daanvanrobays.fragments;

import java.util.ArrayList;
import java.util.List;

import be.pxl.daanvanrobays.custom.CustomAdapter;
import be.pxl.daanvanrobays.pojo.User;
import be.pxl.daanvanrobays.rest.RestHelper;

import android.support.v4.app.ListFragment;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class UsersFrag extends ListFragment {
	OnUserSelectedListener mCallback;
	public final static String USER_ID_ARGS = "user-id";
	int mCurrentPosition = -1;
	private List<User> usersList = new ArrayList<User>();
	private CustomAdapter<User> custAd;

	public interface OnUserSelectedListener {
		public void onUserSelected(int position);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetUsers(getActivity()).execute();
		
		custAd = new CustomAdapter<User>(this.getActivity(), this.usersList);
		setListAdapter(custAd);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mCallback = (OnUserSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnUserSelectedListener");
		}
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mCallback.onUserSelected(usersList.get(position).getId());
		getListView().setItemChecked(position, true);
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
