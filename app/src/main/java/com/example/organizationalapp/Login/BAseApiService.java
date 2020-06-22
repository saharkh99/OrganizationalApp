package com.example.organizationalapp.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BAseApiService {
    @FormUrlEncoded
    @POST("Home/Login")
    Call<String> registerRequest(@Field("username") String username,
                                 @Field("passwords") String passwords);

}
