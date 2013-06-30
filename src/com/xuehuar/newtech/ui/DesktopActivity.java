package com.xuehuar.newtech.ui;

import java.util.Stack;

import com.agimind.widget.customize.SlideHolder;
import com.xgb.animations.customize.AnimationController;
import com.xuehuar.newtech.MyApplication;
import com.xuehuar.newtech.MyData;
import com.xuehuar.newtech.R;
import com.xuehuar.newtech.R.id;
import com.xuehuar.newtech.R.layout;
import com.xuehuar.newtech.bookunit.BookUnit;
import com.xuehuar.newtech.classroom.Classroom;
import com.xuehuar.newtech.desktop.Desktop;
import com.xuehuar.newtech.desktop.Desktop.OnChangeViewListener;
import com.xuehuar.newtech.desktop.Playground;
import com.xuehuar.newtech.util.ViewUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class DesktopActivity extends Activity {
	private Context mContext;
	private MyApplication mApplication;
	private MyData mData;
	private SlideHolder mRoot;
	private Desktop mDesktop;
	private Playground mPlayground;
	private Classroom mClassroom;	
	private BookUnit mBookUnit;
	private AnimationController animationController = new AnimationController();
	private Stack mViewStack = new Stack();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        mApplication = (MyApplication)getApplication();
        mContext = this;
        MyData.initMyData(mContext);
        mDesktop = new Desktop(mApplication, mContext);
        mPlayground = new Playground(mApplication, mContext);
        mClassroom = new Classroom(mApplication, mContext);
        mBookUnit = new BookUnit(mApplication, mContext);
        initView();
        initListener();
        init();
    }

	private void init() {
		// TODO Auto-generated method stub
		
	}

	private void initListener() {
	       mDesktop.setOnChangeViewListener(new Desktop.OnChangeViewListener() {
				@Override
				public void onChangeView(int postion) {
					switch(postion) {
					case ViewUtil.UnitView:
						open(mBookUnit.getView());
						break;
					case ViewUtil.ClassroomView:
						mClassroom.setUnit(mBookUnit.getChoosedItem());
						open(mClassroom.getView());
						break;
					case ViewUtil.SettingView:
						finish();
						break;
					default:
						Toast.makeText(DesktopActivity.this,"尚未实现", Toast.LENGTH_SHORT).show();
						break;
					}				
				}	        
	        });	
	       mBookUnit.setOnChangeViewListener(new BookUnit.OnChangeViewListener() {
				@Override
				public void onChangeView(int unitId) {
					mViewStack.push(mBookUnit.getView());
					mClassroom.setUnit(unitId);
					open(mClassroom.getView());
				}	        
	        });	
	       
	}

	protected void open(View view) {
		mPlayground.setChildView(view);
		mRoot.close();
	}

	private void initView() {
		mRoot = (SlideHolder)this.findViewById(R.id.slider);
		mRoot.setSpeed(0.9f);
		mRoot.setEnabled(false);
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		int width =(int)( wm.getDefaultDisplay().getWidth() * 0.65f);//屏幕宽度
		LayoutParams desktopParams = new LayoutParams(width, LayoutParams.MATCH_PARENT);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mRoot.addView(mDesktop.getView(), desktopParams);
		mRoot.addView(mPlayground.getView(), layoutParams);
		this.open(mBookUnit.getView());
	}

	 @Override
	 public boolean onKeyDown(int keyCode,KeyEvent event){
	    if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
	    	
	    	if (mViewStack.isEmpty()) {
	    		mRoot.open();
	    	} else {	    		
	    		long durationMillis = 1000, delayMillis = 0;
	    		animationController.scaleRotateOut(mPlayground.getChildView(), durationMillis, delayMillis);	  
	    		
	    		View lastView = (View) mViewStack.pop();	    				
	    		this.open(lastView);
	    	}
	    	return false;
	    }
	    return false;
	  }



    
}
