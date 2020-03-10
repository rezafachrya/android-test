package com.example.bnilist.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    // Fungsi ini untuk memanggil API http://10.0.2.2/ams/amservices/bniasset/signup
    @FormUrlEncoded
    @POST("signup")
    Call<ResponseBody> loginRequest(@Field("phonenumber") String phonenumber);

}
