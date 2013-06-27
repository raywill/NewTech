package com.xuehuar.newtech.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xuehuar.newtech.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.xuehuar.newtech.MyApplication;
import com.xuehuar.newtech.util.ViewUtil;

public class Content {
	
	public interface OnChangeViewListener {
		public abstract void onChangeView(int arg0);
	}


	// params
	private Context mContext;
	private MyApplication mApplication;
	public static  int mChosedChildId;
	public static  int mChosedGroupId;
	// data
	private List<Map<String, Object>> mGroup = new ArrayList<Map<String, Object>>();
	private List<List<Map<String, Object>>> mChild = new ArrayList<List<Map<String, Object>>>();
	
	// Views
	private View mContent = null;
	private ExpandableListView mDisplay = null;
	private OnChangeViewListener mOnChangeViewListener = null;
	private ContentAdapter mAdapter = null;
	
	
	public Content(MyApplication application, Context context) {
		this.mApplication = application;
		this.mContext = context;
		mContent = LayoutInflater.from(context).inflate(R.layout.content, null);
		initView();
		initListener();
		init();
	}
	
	private void init() {		
		initData();
		mAdapter  = new ContentAdapter(mContext, mGroup, mChild);
		mDisplay.setAdapter(mAdapter);
		for (int i = 0; i < mGroup.size(); i++) {
			mDisplay.expandGroup(i);
		}	
	}

	private void initData() {

		String[] mGroupName = new String[] {
				"第1章",
				"第2章",
				"第3章",
				"第4章",
				"第5章",
				"第6章",
		};
		for (int i = 0; i < mGroupName.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", mGroupName[i]);
			mGroup.add(map);
		}

		for (int i = 0; i < mGroupName.length; i++) {
			if (i == 0) {
				String [] childNames = new String[]  { "课程目录", "学习课堂", "进度报告", "排行榜" };
				int [] childIcons = new int[] {
						R.drawable.content_done,
						R.drawable.content_done,
						R.drawable.content_done,
						R.drawable.content_doing,					
				};
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for (int j = 0; j < childNames.length; j++)
				{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("icon", childIcons[j]);
					map.put("name", childNames[j]);
					map.put("click", false);
					list.add(map);	
				}
				mChild.add(list);
			} else {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("icon", R.drawable.content_todo);
				map.put("name", "设置设置设置设置设置设置设置设置设置设置设置设置设置");
				map.put("click", false);
				list.add(map);
				list.add(map);
				list.add(map);
				list.add(map);				
				mChild.add(list);
			}
		}
	}
	
	private void initView() {
		mDisplay = (ExpandableListView) mContent.findViewById(R.id.content_list);		
	}

	private void initListener() {
		mDisplay.setOnGroupClickListener(new OnGroupClickListener() {
			public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
				return true;				
			}
		});
		
		mDisplay.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
				
				if (null == mOnChangeViewListener) {
					return true;
				}
				
				mChosedGroupId = groupPosition;
				mChosedChildId = childPosition;
				mAdapter.notifyDataSetChanged();
				mOnChangeViewListener.onChangeView(ViewUtil.ContentView);
				return true;				
			}
		});
		
	}

	public void setOnChangeViewListener(OnChangeViewListener onChangeViewListener) {
		mOnChangeViewListener = onChangeViewListener;
	}
	
	public View getView() {		
		return mContent;
	}

}
