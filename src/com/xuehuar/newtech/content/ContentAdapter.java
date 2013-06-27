package com.xuehuar.newtech.content;

import java.util.List;
import java.util.Map;

import com.xuehuar.newtech.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentAdapter extends BaseExpandableListAdapter {

	private Context mContext = null;
	private List<Map<String, Object>> mGroup = null;
	private List<List<Map<String, Object>>> mChild = null;
	private LayoutInflater mInflater = null;

	public ContentAdapter(Context context, List<Map<String, Object>> group,
			List<List<Map<String, Object>>> child) {
		mContext = context;
		mGroup = group;
		mChild = child;
		mInflater = LayoutInflater.from(mContext);
	}

	public Object getChild(int groupPosition, int childPosition) {
		return mChild.get(groupPosition).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.content_list_child, null);
			holder = new ViewHolder();
			holder.mChildIcon = (ImageView) convertView
					.findViewById(R.id.content_list_child_icon);
			holder.mChildName = (TextView) convertView
					.findViewById(R.id.content_list_child_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mChildIcon.setImageDrawable(mContext.getResources().getDrawable(
				Integer.parseInt(mChild.get(groupPosition).get(childPosition)
						.get("icon").toString())));
		holder.mChildName.setText(mChild.get(groupPosition).get(childPosition)
				.get("name").toString());
		if (childPosition == Content.mChosedChildId && groupPosition == Content.mChosedGroupId){
			convertView.setBackgroundResource(R.drawable.content_selected_list_item);		
		} else {
			convertView.setBackgroundResource(R.drawable.content_list_item);
		}
		return convertView;
	}

	public int getChildrenCount(int groupPosition) {
		return mChild.get(groupPosition).size();
	}

	public Object getGroup(int groupPosition) {
		return mGroup.get(groupPosition);
	}

	public int getGroupCount() {
		return mGroup.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.content_list_group, null);
			holder = new ViewHolder();
			holder.mGroupName = (TextView) convertView
					.findViewById(R.id.content_list_group_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mGroupName.setText(mGroup.get(groupPosition).get("name")
				.toString());
		return convertView;
	}

	public boolean hasStableIds() {
		return false;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	private class ViewHolder {
		private TextView mGroupName;
		private ImageView mChildIcon;
		private TextView mChildName;
	}
}
