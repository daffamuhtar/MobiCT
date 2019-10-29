package com.majinor.mobilecomtest.apihelper;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("Title")
    private String title;
    @SerializedName("StatusCode")
    private String statuscode;
    @SerializedName("StatusMessage")
    private String statusmessage;


    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
    public void setBody(String statuscode){
        this.statuscode = statuscode;
    }
    public String getStatuscode(){
        return statuscode;
    }
    public void setUserId(String statusmessage){
        this.statusmessage = statusmessage;
    }
    public String getStatusmessage(){
        return statusmessage;
    }
}
