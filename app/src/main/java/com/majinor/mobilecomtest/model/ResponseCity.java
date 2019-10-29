package com.majinor.mobilecomtest.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseCity {

	@SerializedName("Value")
	private List<SemuacityItem> value;

	@SerializedName("Title")
	private String title;

	@SerializedName("StatusCode")
	private String statusCode;

	@SerializedName("StatusMessage")
	private String statusMessage;

	public void setValue(List<SemuacityItem> value){
		this.value = value;
	}

	public List<SemuacityItem> getValue(){
		return value;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setStatusCode(String statusCode){
		this.statusCode = statusCode;
	}

	public String getStatusCode(){
		return statusCode;
	}

	public void setStatusMessage(String statusMessage){
		this.statusMessage = statusMessage;
	}

	public String getStatusMessage(){
		return statusMessage;
	}

	@Override
 	public String toString(){
		return 
			"ResponseCity{" +
			"value = '" + value + '\'' + 
			",title = '" + title + '\'' + 
			",statusCode = '" + statusCode + '\'' + 
			",statusMessage = '" + statusMessage + '\'' + 
			"}";
		}
}