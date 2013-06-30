package com.xuehuar.newtech.datasource;

import java.util.ArrayList;

import android.content.Context;

import com.xuehuar.newtech.R;
import com.xuehuar.newtech.MyApplication;

public class MaterialLibrary {
	private Context mContext;
	private ArrayList mList;
	
	public  MaterialLibrary(Context context) {
			this.mContext = context;
			init();
			createDemoMaterial();
			createIndex();
	}
	
	void init() {
		mList = new ArrayList(10);  // commonly at most 10 unit
	}
	
	void createDemoMaterial() {
		int unitId = 0;
		int classId = -1; /* ignore */
		String [] material = mContext.getResources().getStringArray(
				R.array.reading_material);
		for (int m=0; m < material.length; m++)
		{
			if (m > 0 && m%3 == 0) {
				unitId++;
			}
			addMaterial(new MaterialBean(m , unitId,  classId, material[m]));
		}
	}

	private void addMaterial(MaterialBean materialBean) {
		int unit = materialBean.getUnitId();
		ArrayList unitList = null;
		// When add a new Unit
		if (mList.size() <= unit) {
			unitList = new ArrayList(10);  // each unit usually has at most 10 material
			mList.add(unitList);
		} else {
			unitList = (ArrayList) mList.get(unit);
		}
		unitList.add(materialBean);
		return;	
	}

	private void createIndex() {
		// todo		
	}
	
	public  int getUnitCount() {
		return mList.size();
	}
	

	public  int getMaterialCount(int unitId) {
		int count = 0;
		if (unitId >= 0 &&  unitId < getUnitCount() )  {
			count = ((ArrayList)mList.get(unitId)).size();
		}
		return count;
	}
	
	public String getMaterial(int unitId, int materialIndex) {
		int count = 0;
		String material = null;
		if ((count = getMaterialCount(unitId)) > 0) {
			if (materialIndex >= 0 && materialIndex < count) {
				MaterialBean bean = (MaterialBean) ((ArrayList)mList.get(unitId)).get(materialIndex);
				material = bean.getMaterialContent();
			}
		}
		return material;
	}	
}
