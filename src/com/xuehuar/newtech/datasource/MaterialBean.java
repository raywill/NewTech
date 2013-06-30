package com.xuehuar.newtech.datasource;

public class MaterialBean {
		private int mUnitId;
		private int mClassId;
		private int mMaterialId;
		private String mMaterialContent;
		
		MaterialBean() {
			
		}
		
		MaterialBean(int materialId, int unitId, int classId, String materialContent) {
			setUnitId(unitId);
			setClassId(classId);
			setMaterialId(materialId);
			setMaterialContent(materialContent);			
		}
		public int getUnitId() {
			return mUnitId;
		}

		public void setUnitId(int mUnitId) {
			this.mUnitId = mUnitId;
		}

		public int getClassId() {
			return mClassId;
		}

		public void setClassId(int mClassId) {
			this.mClassId = mClassId;
		}

		public int getMaterialId() {
			return mMaterialId;
		}

		public void setMaterialId(int mMaterialId) {
			this.mMaterialId = mMaterialId;
		}

		public String getMaterialContent() {
			return mMaterialContent;
		}

		public void setMaterialContent(String mMaterialContent) {
			this.mMaterialContent = mMaterialContent;
		}		
	}