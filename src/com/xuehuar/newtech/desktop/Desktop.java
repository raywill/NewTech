package com.xuehuar.newtech.desktop;

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

public class Desktop {
	
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
	private View mDesktop = null;
	private ExpandableListView mDisplay = null;
	private OnChangeViewListener mOnChangeViewListener = null;
	private DesktopAdapter mAdapter = null;
	private String[] mGroupName;
	
	
	public Desktop(MyApplication application, Context context) {
		this.mApplication = application;
		this.mContext = context;
		mDesktop = LayoutInflater.from(context).inflate(R.layout.desktop, null);
		initView();
		initListener();
		init();
	}
	
	private void init() {		
		initData();
		mAdapter  = new DesktopAdapter(mContext, mGroup, mChild);
		mDisplay.setAdapter(mAdapter);
		for (int i = 0; i < mGroup.size(); i++) {
			mDisplay.expandGroup(i);
		}	
	}

	private void initData() {
		mGroupName = mContext.getResources().getStringArray(
				R.array.desktop_list_head_strings);
		getGroupList();
		getChildList();
	}

	private void getGroupList() {
		for (int i = 0; i < mGroupName.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", mGroupName[i]);
			mGroup.add(map);
		}
	}

	private void getChildList() {
		for (int i = 0; i < mGroupName.length; i++) {
			if (i == 0) {
				String [] childNames = new String[]  { "课程目录", "学习课堂", "进度报告", "排行榜" };
				int [] childIcons = new int[] {
						R.drawable.desktop_icon_content_v1_24,
						R.drawable.desktop_icon_classroom_v1_24,
						R.drawable.desktop_icon_report_v1_24,
						R.drawable.desktop_icon_disscussion_v1_24					
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
			} else if (i == 1) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("icon", R.drawable.desktop_icon_setting_v1_24);
				map.put("name", "设置");
				map.put("click", false);
				list.add(map);
				mChild.add(list);
			}
		}
	}
	
	private void initView() {
		mDisplay = (ExpandableListView) mDesktop.findViewById(R.id.desktop_list);		
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
				if (0 == groupPosition) {
					switch (childPosition) {
					case 0:
						mOnChangeViewListener.onChangeView(ViewUtil.ContentView);
						break;
					case 1:
						mOnChangeViewListener.onChangeView(ViewUtil.ClassroomView);
						break;
					case 2:
						mOnChangeViewListener.onChangeView(ViewUtil.ReportView);
						break;
					case 3:
						mOnChangeViewListener.onChangeView(ViewUtil.DiscussionView);
						break;						
					default:
						break;
					}
				} else if (1 == groupPosition) {
					switch (childPosition) {
					case 0:
						mOnChangeViewListener.onChangeView(ViewUtil.SettingView);
						break;
					default:
						break;
					}
				}
				return true;				
			}
		});
		
	}

	public void setOnChangeViewListener(OnChangeViewListener onChangeViewListener) {
		mOnChangeViewListener = onChangeViewListener;
	}
	
	public View getView() {		
		return mDesktop;
	}

}
