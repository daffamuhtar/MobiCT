package com.majinor.mobilecomtest.apihelper;

import com.google.gson.JsonObject;
import com.majinor.mobilecomtest.model.ResponseCity;
import com.majinor.mobilecomtest.model.ResponseProvince;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("login")
    Call<LoginResult> postData(@Body JsonObject body);

    @POST("getprovince")
    Call<ResponseProvince> getSemuaProvince();

    @POST("getcity")
    Call<ResponseCity> getSemuaCity(@Body JsonObject body);

}
