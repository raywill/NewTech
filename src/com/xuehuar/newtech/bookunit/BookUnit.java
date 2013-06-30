package com.xuehuar.newtech.bookunit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xuehuar.newtech.MyData;
import com.xuehuar.newtech.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.GridView;

import com.xuehuar.newtech.MyApplication;
import com.xuehuar.newtech.datasource.MaterialLibrary;
import com.xuehuar.newtech.desktop.Desktop.OnChangeViewListener;
import com.xuehuar.newtech.util.ViewUtil;

public class BookUnit {
	
	public interface OnChangeViewListener {
		public abstract void onChangeView(int unit);
	}


	// params
	private Context mContext;
	private MyApplication mApplication;
	public static  int mChosedItem = 0;
	// data
	private MaterialLibrary mUnit = null;
	//private List<List<Map<String, Object>>> mChild = new ArrayList<List<Map<String, Object>>>();
	
	// Views
	private View mUnitView = null;
	private GridView mDisplay = null;
	private OnChangeViewListener mOnChangeViewListener = null;
	private UnitAdapter mAdapter = null;
	
	
	public BookUnit(MyApplication application, Context context) {
		this.mApplication = application;
		this.mContext = context;
		mUnitView = LayoutInflater.from(context).inflate(R.layout.unit, null);
		mUnit = MyData.getMaterialLibrary();
		initView();
		initListener();
		init();
	}
	
	private void init() {		
		initData();
		mAdapter  = new UnitAdapter(mContext, mUnit);
		mDisplay.setAdapter(mAdapter);
	}

	private void initData() {
	}
	
	private void initView() {
		mDisplay = (GridView) mUnitView.findViewById(R.id.unit_list);
		mDisplay.setSelector(new ColorDrawable(Color.TRANSPARENT)); 
	}

	private void initListener() {
		mDisplay.setOnItemClickListener  (new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (null == mOnChangeViewListener) {
					return;
				}
				mChosedItem =arg2 ;
				mAdapter.notifyDataSetChanged();
				mOnChangeViewListener.onChangeView(mChosedItem);				
				
			}					
		});		
	}

	public View getView() {		
		return mUnitView;
	}

	public int getChoosedItem() {
		return mChosedItem;
	}
	
	public void setOnChangeViewListener(BookUnit.OnChangeViewListener onChangeViewListener) {
		mOnChangeViewListener = onChangeViewListener;		
	}
}
