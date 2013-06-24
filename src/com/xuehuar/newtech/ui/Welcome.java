package com.xuehuar.newtech.ui;

import com.xuehuar.newtech.R;
import com.xuehuar.newtech.R.id;
import com.xuehuar.newtech.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Welcome extends Activity {
	private Button mLoginButton = null;
	private Button mRegisteButton = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        initListener();        
    }
    
	private void initView() {
		mLoginButton = (Button)this.findViewById(R.id.login_button);
		mRegisteButton = (Button)this.findViewById(R.id.register_button);
	}
	private void initListener() {
		mLoginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent();
				Welcome.this.startActivity(
						new Intent(Welcome.this, RootActivity.class)
				);
			}
		});
		
		mRegisteButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Toast.makeText(Welcome.this, "Regiter", Toast.LENGTH_SHORT).show();			}
		});		
	}    
}
