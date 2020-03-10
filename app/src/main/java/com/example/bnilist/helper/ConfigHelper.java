package com.example.bnilist.helper;

import com.example.bnilist.api.ApiClient;
import com.example.bnilist.api.ApiInterface;

public class ConfigHelper {
    public static final String BASEURL = "http://192.168.43.181/ams/amservices/bniasset/";

    public static ApiInterface getAPIService(){
        return ApiClient.getRetrofit(BASEURL).create(ApiInterface.class);
    }
}
