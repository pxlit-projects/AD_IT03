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
import be.pxl.daanvanrobays.pojo.Theme;
import be.pxl.daanvanrobays.rest.RestHelper;

public class ThemesFrag extends ListFragment {
	private List<Theme> themesList = new ArrayList<Theme>();
	private CustomAdapter<Theme> custAd;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetThemes(getActivity()).execute();
		
		custAd = new CustomAdapter<Theme>(this.getActivity(), this.themesList);
		setListAdapter(custAd);
	}
	
	public void updateThemesView ()
	{
		new GetThemes(getActivity()).execute();
	}
	
	private class GetThemes extends AsyncTask<Void, Integer, List<Theme>> {
		private Context mContext;

		public GetThemes(Context context) {
			mContext = context;
		}

		@Override
		protected List<Theme> doInBackground(Void... params) {
			try {
				RestHelper helper = new RestHelper();
				if (helper.isConnected(mContext)) {
					Log.d("test", "Retrieving themes");
					List<Theme> themes = helper.getThemes();
					Log.d("test", "themes retrieved");
					return themes;
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

		protected void onPostExecute(List<Theme> result) {
			Log.d("test", "adding to list");
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					themesList.add(result.get(i));
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
