package com.xuehuar.newtech.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.xuehuar.newtech.MyApplication;
import com.xuehuar.newtech.R;

public class Content {
	// params
	private Context mContext;
	private MyApplication mApplication;
	// Views
	private View mContent = null;
	private ExpandableListView mDisplay;

	public Content(MyApplication application, Context context) {
		this.mApplication = application;
		this.mContext = context;
		mContent = LayoutInflater.from(context).inflate(R.layout.content, null);
	}

	public View getView() {		
		return mContent;
	}
}
