package com.xuehuar.newtech.bookunit;

import java.util.List;
import java.util.Map;

import com.xuehuar.newtech.R;
import com.xuehuar.newtech.datasource.MaterialLibrary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UnitAdapter extends BaseAdapter {

	private Context mContext = null;
	private MaterialLibrary mUnits = null;
	private LayoutInflater mInflater = null;

	public UnitAdapter(Context context, MaterialLibrary units) {
		mContext = context;
		mUnits = units;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mUnits.getUnitCount();		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.unit_item, null);
			holder = new ViewHolder();
			holder.mUnitIcon = (ImageView) convertView
					.findViewById(R.id.unit_item_icon);
			holder.mUnitName = (TextView) convertView
					.findViewById(R.id.unit_item_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (position < 2) 
			holder.mUnitIcon.setImageResource(R.drawable.unit_cover_done);
		else if (position < 3)
			holder.mUnitIcon.setImageResource(R.drawable.unit_cover_doing);
		else
			holder.mUnitIcon.setImageResource(R.drawable.unit_cover_todo);
		holder.mUnitName.setText("第 "+position+" 章");
		if (position == BookUnit.mChosedItem){
			//convertView.setBackgroundResource(R.drawable.content_selected_list_item);
			//convertView.setBackgroundColor(Color.WHITE);
		} else {
			// convertView.setBackgroundResource(R.drawable.content_list_item);
			//convertView.setBackgroundColor(Color.WHITE);
		}
		return convertView;
	}

	private class ViewHolder {
		private TextView mUnitName;
		private ImageView mUnitIcon;
	}

}
