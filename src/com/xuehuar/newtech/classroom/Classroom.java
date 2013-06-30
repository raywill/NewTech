package com.xuehuar.newtech.classroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xuehuar.newtech.MyApplication;
import com.xuehuar.newtech.MyData;
import com.xuehuar.newtech.R;
import com.xuehuar.newtech.datasource.MaterialLibrary;

public class Classroom {
	// params
	private Context mContext;
	private MyApplication mApplication;
	// Views
	private View mClassroom = null;
	private TextView mQuizView = null;
	private TextView mMaterialView = null;
	// data
	private int mCurrentChapter = 0;
	private int mCurrentLesson = 0;
	private int mCurrentUnit = 0;
	private MaterialLibrary mLibrary = null;

	public Classroom(MyApplication application, Context context) {
		this.mApplication = application;
		this.mContext = context;
		mClassroom = LayoutInflater.from(context).inflate(R.layout.classroom, null);
		mLibrary = MyData.getMaterialLibrary();
		initView();
		initListener();
	}

	private void initListener() {
		mQuizView.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Toast.makeText(mContext, "Conguraturelations!",Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void initView() {
		mMaterialView = (TextView) mClassroom.findViewById(R.id.material);		
		mQuizView = (TextView) mClassroom.findViewById(R.id.quiz);		
	}

	public View getView() {
		updateView();
		return mClassroom;
	}

	private void updateView() {
		mMaterialView.setText(
				mLibrary.getMaterial(mCurrentUnit, 0)
				);		
	}

	public void setUnit(int unit) {
		mCurrentUnit  = unit;
	}	
}
