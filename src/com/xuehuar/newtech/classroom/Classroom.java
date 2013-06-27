package com.xuehuar.newtech.classroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.xuehuar.newtech.MyApplication;
import com.xuehuar.newtech.R;

public class Classroom {
	// params
	private Context mContext;
	private MyApplication mApplication;
	// Views
	private View mClassroom = null;

	public Classroom(MyApplication application, Context context) {
		this.mApplication = application;
		this.mContext = context;
		mClassroom = LayoutInflater.from(context).inflate(R.layout.classroom, null);
	}

	public View getView() {		
		return mClassroom;
	}
}
