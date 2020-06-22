package com.example.organizationalapp.Login;

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

import com.example.organizationalapp.BaseActivity;
import com.example.organizationalapp.MainActivity;
import com.example.organizationalapp.NewsPart.NewsActivity;
import com.example.organizationalapp.R;
import com.example.organizationalapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginClass {
    static String code = "wrong";

    // Context mContext;
    public static String requestLogin(String username, String passwords, Context context, ProgressBar progressBar) {
        BAseApiService mApiService;
        progressBar.setVisibility(View.VISIBLE);
        mApiService = UtilsApi.getAPIService();
        mApiService.registerRequest(username, passwords)
                .enqueue(new Callback<String>() {

                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
//                        Dialog dialog = new Dialog(context);
//                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                        dialog.setContentView(R.layout.dialog);
//                        dialog.show();
                          progressBar.setVisibility(View.GONE);
                        Log.d("response", response.toString());
                        if (response.isSuccessful()) {
                            Log.d("suceess", "success");
                            try {
                                if (response.code() == 400) {
                                    Log.v("Error code 400", response.errorBody().string());
                                }
                                String responseBody = response.body();
                                if (responseBody.equals("true")) {
                                  //  dialog.dismiss();
                                    Intent intent = new Intent(context, BaseActivity.class);
                                    //overridePendingTransition(R.animator.enter_from_left, R.animator.exit_to_right);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    NewsActivity.code=5;
                                    User.name=username;
                                    context.startActivity(intent);
                                } else {
//                                    dialog.dismiss();
//                                    Dialog dialog1 = new Dialog(context);
//                                    dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                                    dialog1.setContentView(R.layout.dialog_information);
//                                    TextView ok = dialog1.findViewById(R.id.btn_ok);
//                                    ok.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            dialog1.dismiss();
//                                        }
//                                    });
//                                    dialog1.show();
//                                    code = responseBody;
//                                    Log.d("incorrect password", "correct");
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
}
