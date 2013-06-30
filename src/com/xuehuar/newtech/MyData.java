package com.xuehuar.newtech;

import android.content.Context;

import com.xuehuar.newtech.datasource.MaterialLibrary;

public class MyData {
	public static MaterialLibrary library = null;
	
	public static void initMyData(Context mContext) {
		library = new MaterialLibrary(mContext);
	}

	public static MaterialLibrary getMaterialLibrary() {
		return library;
	}

}
