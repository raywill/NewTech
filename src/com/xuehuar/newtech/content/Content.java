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
import com.xuehuar.newtech.desktop.Desktop.OnChangeViewListener;
import com.xuehuar.newtech.util.ViewUtil;

public class Content {
	
	public interface OnChangeViewListener {
		public abstract void onChangeView(int chapter, int lesson);
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
		ContentResult content = new ContentResult();		
		
		for (int i = 0; i < content.mChapter.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", content.mChapter[i].mChapterName);
			mGroup.add(map);
		}

		for (int i = 0; i < content.mChapter.length; i++) {					
			   int [] childIcons = new int[] {
						R.drawable.content_todo,
						R.drawable.content_doing,
						R.drawable.content_done
				};
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for (int j = 0; j < content.mChapter[i].mLesson.length; j++)
				{
					Map<String, Object> map = new HashMap<String, Object>();
					if (content.mChapter[i].mLesson[j].mStatus >= 0 && content.mChapter[i].mLesson[j].mStatus <=3) {
						map.put("icon", childIcons[content.mChapter[i].mLesson[j].mStatus-1]);
					} else {
						map.put("icon", childIcons[0]);
					}
					map.put("name", content.mChapter[i].mLesson[j].mMaterialName);
					map.put("click", false);
					list.add(map);	
				}
				mChild.add(list);			
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
				mOnChangeViewListener.onChangeView(mChosedGroupId,  mChosedChildId);
				return true;				
			}
		});
		
	}

 
	
	public View getView() {		
		return mContent;
	}

	public int getChapter() {
		return mChosedGroupId;
	}
	public int getLesson() {
		return mChosedChildId;
	}

	public void setOnChangeViewListener(
			OnChangeViewListener onChangeViewListener) {
		mOnChangeViewListener = onChangeViewListener;		
	}
}
