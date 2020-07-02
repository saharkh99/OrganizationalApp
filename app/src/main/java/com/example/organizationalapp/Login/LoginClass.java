package com.example.organizationalapp.Login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.organizationalapp.BaseActivity;
import com.example.organizationalapp.MainActivity;
import com.example.organizationalapp.NewsPart.NewsActivity;
import com.example.organizationalapp.R;
import com.example.organizationalapp.Token;
import com.example.organizationalapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginClass {
    static String code;

    public static String requestLogin(String username, String passwords, Context context, ProgressBar progressBar) {
        BAseApiService mApiService;
        progressBar.setVisibility(View.VISIBLE);
        mApiService = UtilsApi.getAPIService();
        mApiService.registerRequest(username, passwords)
                .enqueue(new Callback<String>() {

                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        progressBar.setVisibility(View.GONE);
                        Log.d("response", response.toString());
                        if (response.isSuccessful()) {
                            Log.d("suceess", "success");
                            try {
                                if (response.code() == 400) {
                                    Log.v("Error code 400", response.errorBody().string());
                                }
                                String responseBody = response.body();
                                if (!responseBody.equals("false")) {
                                    Log.d("token", responseBody.toString());
                                    Token.setToken(responseBody);
                                    getClaim();

                                    Intent intent = new Intent(context, BaseActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    NewsActivity.code = 5;
                                    context.startActivity(intent);
                                } else {
                                    Dialog dialog1 = new Dialog(context);
                                    dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialog1.setContentView(R.layout.dialog_layout);
                                    TextView ok = dialog1.findViewById(R.id.btn_ok);
                                    ok.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog1.dismiss();
                                        }
                                    });
                                    dialog1.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        t.printStackTrace();

                    }
                });
        return code;

    }

    private static void getClaim() {
        JWT parsedJWT = new JWT(Token.getToken());
        Claim subscriptionMetaData = parsedJWT.getClaim("role");
        Claim subscriptionMetaData2 = parsedJWT.getClaim("unique_name");
        String parsedValue = subscriptionMetaData.asString();
        String parsedValue2 = subscriptionMetaData2.asString();
        User.setRole(parsedValue);
        User.setName(parsedValue2);
        Log.d("role", parsedValue.toString());
    }
}
