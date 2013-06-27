package com.xuehuar.newtech.classroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xuehuar.newtech.MyApplication;
import com.xuehuar.newtech.R;
import com.xuehuar.newtech.content.ContentResult;

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
	private ContentResult mContentResult = null;

	public Classroom(MyApplication application, Context context) {
		this.mApplication = application;
		this.mContext = context;
		mClassroom = LayoutInflater.from(context).inflate(R.layout.classroom, null);
		mContentResult = new ContentResult();
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
		mMaterialView.setText(mContentResult.mChapter[mCurrentChapter].mLesson[mCurrentLesson].mMaterial.mMaterial);		
	}

	public void setLesson(int chapter, int lesson) {
		if (chapter >= 0 && chapter < mContentResult.mChapter.length) {
			if (lesson >= 0 && lesson < mContentResult.mChapter[chapter].mLesson.length) {
				mCurrentLesson = lesson;
				mCurrentChapter = chapter;
			}				
		}
	}
}
