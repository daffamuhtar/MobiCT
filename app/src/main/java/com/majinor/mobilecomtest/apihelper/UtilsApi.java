package com.majinor.mobilecomtest.apihelper;

public class UtilsApi {
    public static final String BASE_URL_API = "http://35.240.192.212:8997/InventFoodCulinary/service/";

    // Mendeklarasikan Interface BaseApiService
    public static ApiInterface getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(ApiInterface.class);
    }
}
