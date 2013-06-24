package com.xuehuar.newtech.desktop;

import com.xuehuar.newtech.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import com.xuehuar.newtech.MyApplication;

public class Desktop {
	// params
	private Context mContext;
	private MyApplication mApplication;
	// Views
	private View mDesktop = null;
	private ExpandableListView mDisplay;

	public Desktop(MyApplication application, Context context) {
		this.mApplication = application;
		this.mContext = context;
		mDesktop = LayoutInflater.from(context).inflate(R.layout.desktop, null);
	}

	public View getView() {		
		return mDesktop;
	}

}
