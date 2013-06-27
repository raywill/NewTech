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
import android.widget.LinearLayout;

import com.xuehuar.newtech.MyApplication;
import com.xuehuar.newtech.desktop.Desktop.OnChangeViewListener;
import com.xuehuar.newtech.util.ViewUtil;

public class Playground {
	
	public interface OnChangeViewListener {
		public abstract void onChangeView(int arg0);
	}


	// params
	private Context mContext;
	private MyApplication mApplication;
	
	public static  int mChooesId;
	// Views
	private View mPlayground = null;
	private LinearLayout mLayout = null;
	private OnChangeViewListener mOnChangeViewListener = null;
	
	public Playground(MyApplication application, Context context) {
		this.mApplication = application;
		this.mContext = context;
		mPlayground = LayoutInflater.from(context).inflate(R.layout.playground, null);
		mLayout = (LinearLayout) mPlayground.findViewById(R.id.playground);
		initView();
		initListener();
		init();
	}
	
	private void init() {		
	}

	private void initView() {

	}

	private void initListener() {
	}

	public void setOnChangeViewListener(OnChangeViewListener onChangeViewListener) {
		mOnChangeViewListener = onChangeViewListener;
	}
	
	public View getView() {		
		return mPlayground;
	}

	public void setChildView(View view) {
		mLayout.removeAllViewsInLayout();
		mLayout.addView(view);		
	}

}
