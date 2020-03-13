package com.example.bnilist.helper;

import com.example.bnilist.api.ApiClient;
import com.example.bnilist.api.ApiInterface;

public class ConfigHelper {
    public static final String BASEURL = "http://10.12.32.25:8080/ams/amservices/bniasset/signup";

    public static ApiInterface getAPIService(){
        return ApiClient.getRetrofit(BASEURL).create(ApiInterface.class);
    }
}
