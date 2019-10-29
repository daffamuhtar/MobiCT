package com.majinor.mobilecomtest.model;

import com.google.gson.annotations.SerializedName;

public class SemuaprovinceItem {

	@SerializedName("ProvinceName")
	private String provinceName;

	@SerializedName("ProvinceID")
	private String provinceID;

	public void setProvinceName(String provinceName){
		this.provinceName = provinceName;
	}

	public String getProvinceName(){
		return provinceName;
	}

	public void setProvinceID(String provinceID){
		this.provinceID = provinceID;
	}

	public String getProvinceID(){
		return provinceID;
	}

	@Override
 	public String toString(){
		return 
			"SemuaprovinceItem{" +
			"provinceName = '" + provinceName + '\'' + 
			",provinceID = '" + provinceID + '\'' + 
			"}";
		}
}