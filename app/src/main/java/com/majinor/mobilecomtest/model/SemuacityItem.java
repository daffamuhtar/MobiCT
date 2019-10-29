package com.majinor.mobilecomtest.model;

import com.google.gson.annotations.SerializedName;

public class SemuacityItem {

	@SerializedName("ProvinceID")
	private String provinceID;

	@SerializedName("CityID")
	private String cityID;

	@SerializedName("CityName")
	private String cityName;

	public void setProvinceID(String provinceID){
		this.provinceID = provinceID;
	}

	public String getProvinceID(){
		return provinceID;
	}

	public void setCityID(String cityID){
		this.cityID = cityID;
	}

	public String getCityID(){
		return cityID;
	}

	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	public String getCityName(){
		return cityName;
	}

	@Override
 	public String toString(){
		return 
			"SemuacityItem{" +
			"provinceID = '" + provinceID + '\'' + 
			",cityID = '" + cityID + '\'' + 
			",cityName = '" + cityName + '\'' + 
			"}";
		}
}