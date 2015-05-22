package be.pxl.daanvanrobays.custom;
import java.util.List;

import be.pxl.daanvanrobays.finah.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CustomAdapter<T> extends ArrayAdapter<T> {

	public CustomAdapter(Context context, List<T> list) {
		super(context, R.layout.custom_listitem_view, list);
		// TODO Auto-generated constructor stub
	}

	class ViewHolder {
		public CustomListItemView customview;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View rowView = convertView;
		ViewHolder holder = new ViewHolder();

		if (rowView == null) {
			LayoutInflater infl = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = infl.inflate(R.layout.custom_listitem_view, null);

			holder.customview = (CustomListItemView) rowView
					.findViewById(R.id.customitem);
			rowView.setTag(holder);

		}else {
			holder = (ViewHolder) rowView.getTag();
		}
		holder.customview.SetInfo(getItem(position));

		return rowView;
	}
}