package com.xuehuar.newtech.ui;

import com.agimind.widget.SlideHolder;
import com.xuehuar.newtech.MyApplication;
import com.xuehuar.newtech.R;
import com.xuehuar.newtech.R.id;
import com.xuehuar.newtech.R.layout;
import com.xuehuar.newtech.content.Content;
import com.xuehuar.newtech.desktop.Desktop;

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
import android.support.v4.app.NavUtils;

public class RootActivity extends Activity {
	private Context mContext;
	private MyApplication mApplication;
	private SlideHolder mRoot;
	private Desktop mDesktop;
	private Content mContent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        mApplication = (MyApplication)getApplication();
        mContext = this;
        mDesktop = new Desktop(mApplication, mContext);
        mContent = new Content(mApplication, mContext);
        initView();
        initListener();
    }

	private void initListener() {
				
	}

	private void initView() {
		mRoot = (SlideHolder)this.findViewById(R.id.slider);
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();//屏幕宽度
		LayoutParams desktopParams = new LayoutParams(width - 100, LayoutParams.FILL_PARENT);
		LayoutParams contentParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		mRoot.addView(mDesktop.getView(), desktopParams);
		mRoot.addView(mContent.getView(), contentParams);
	}





    
}
