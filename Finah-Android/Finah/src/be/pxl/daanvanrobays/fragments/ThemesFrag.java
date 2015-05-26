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
import be.pxl.daanvanrobays.fragments.UsersFrag.OnUserSelectedListener;
import be.pxl.daanvanrobays.pojo.Theme;
import be.pxl.daanvanrobays.rest.RestHelper;

public class ThemesFrag extends ListFragment {
	OnThemeSelectedListener mCallback;
	public final static String THEME_ID_ARGS = "theme-id";
	int mCurrentPosition = -1;
	private List<Theme> themesList = new ArrayList<Theme>();
	private CustomAdapter<Theme> custAd;
	private ProgressDialog pDialog;

	public interface OnThemeSelectedListener {
		public void onThemeSelected(int position);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new GetThemes(getActivity()).execute();
		
		custAd = new CustomAdapter<Theme>(this.getActivity(), this.themesList);
		setListAdapter(custAd);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mCallback = (OnThemeSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnThemeSelectedListener");
		}
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mCallback.onThemeSelected(themesList.get(position).getId());
		getListView().setItemChecked(position, true);
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
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDialog.setTitle("Please wait");
			pDialog.setMessage("Getting themes..");
			pDialog.show();
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
				custAd.notifyDataSetChanged();
			}
			pDialog.dismiss();
		}
	}
}
