package com.example.organizationalapp.Login;

import com.example.organizationalapp.ServicePart.Services;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BAseApiService {
    @FormUrlEncoded
    @POST("Home/Login")
    Call<String> registerRequest(@Field("username") String username,
                                 @Field("passwords") String passwords);

    @FormUrlEncoded
    @POST("Home/Get")
    Call<List<Services>> getService(@Field("token") String token);


}
