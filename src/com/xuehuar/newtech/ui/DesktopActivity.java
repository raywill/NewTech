package com.xuehuar.newtech.ui;

import com.agimind.widget.customize.SlideHolder;
import com.xuehuar.newtech.MyApplication;
import com.xuehuar.newtech.R;
import com.xuehuar.newtech.R.id;
import com.xuehuar.newtech.R.layout;
import com.xuehuar.newtech.classroom.Classroom;
import com.xuehuar.newtech.content.Content;
import com.xuehuar.newtech.desktop.Desktop;
import com.xuehuar.newtech.desktop.Desktop.OnChangeViewListener;
import com.xuehuar.newtech.desktop.Playground;
import com.xuehuar.newtech.util.ViewUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
	private SlideHolder mRoot;
	private Desktop mDesktop;
	private Playground mPlayground;
	private Content mContent;
	private Classroom mClassroom;	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        mApplication = (MyApplication)getApplication();
        mContext = this;
        mDesktop = new Desktop(mApplication, mContext);
        mPlayground = new Playground(mApplication, mContext);
        mContent = new Content(mApplication, mContext);
        mClassroom = new Classroom(mApplication, mContext);
        initView();
        initListener();
        init();
    }

	private void init() {
		// TODO Auto-generated method stub
		
	}

	private void initListener() {
	       mDesktop.setOnChangeViewListener(new OnChangeViewListener() {
				@Override
				public void onChangeView(int postion) {
					switch(postion) {
					case ViewUtil.ContentView:
						open(mContent.getView());
						break;
					case ViewUtil.ClassroomView:
						open(mClassroom.getView());
						break;
					default:
						Toast.makeText(DesktopActivity.this,"尚未实现", Toast.LENGTH_SHORT).show();
						break;
					}				
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
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		int width =(int)( wm.getDefaultDisplay().getWidth() * 0.65f);//屏幕宽度
		LayoutParams desktopParams = new LayoutParams(width, LayoutParams.MATCH_PARENT);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mRoot.addView(mDesktop.getView(), desktopParams);
		mRoot.addView(mPlayground.getView(), layoutParams);
		this.open(mContent.getView());
	}





    
}
