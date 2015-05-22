package be.pxl.daanvanrobays.fragments;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AdminListFrag extends ListFragment {
	private List<String> adminList;
	private onStringSelectedListener mCallback;

	public interface onStringSelectedListener {
		public void onStringSelected(String value);
	}

	public AdminListFrag() {
		this.adminList = new ArrayList<String>();
		this.adminList.add("Users");
		this.adminList.add("Usertypes");
		this.adminList.add("Themes");
		this.adminList.add("Questions");
		this.adminList.add("Answers");
		this.adminList.add("Hashes");
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, this.adminList);
		setListAdapter(adapter);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception.
		try {
			mCallback = (onStringSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement onStringSelectedListener");
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Notify the parent activity of selected item
		if(this.mCallback != null){
			this.mCallback.onStringSelected(this.adminList.get(position));
		}
		// Set the item as checked to be highlighted when in two-pane layout
		getListView().setItemChecked(position, true);
	}

}
