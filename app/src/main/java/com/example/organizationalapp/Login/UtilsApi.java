package com.example.organizationalapp.Login;

public class UtilsApi {
    public static final String BASE_URL_API = "http://10.0.2.2:37668/";

    public static BAseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BAseApiService.class);
    }
}
