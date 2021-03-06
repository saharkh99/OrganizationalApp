package com.example.organizationalapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.fragment.app.FragmentTransaction;

import com.example.organizationalapp.ForntPart.BaseContext;
import com.example.organizationalapp.Login.LoginClass;
import com.example.organizationalapp.NewsPart.NewsActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends BaseContext {

    TextInputEditText username;
    TextInputEditText password;
    MaterialButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login.setEnabled(true);
                String uName = username.getText().toString().trim();
                String uPass = password.getText().toString().trim();

                if (checkNotEmpty(uName, uPass)) {
                    ProgressBar progressBar = findViewById(R.id.progress_circular);
                    progressBar.setVisibility(View.VISIBLE);
                    LoginClass.requestLogin(uName, uPass, MainActivity.this, progressBar);

                }
            }
        });
    }

    private void findView() {
        login = findViewById(R.id.login);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
    }

    private boolean checkNotEmpty(String uName, String uPass) {
        if (uName.isEmpty() || uPass.isEmpty()) {
            login.setEnabled(false);
            if (uName.isEmpty())
                username.setError("نام کاربری خود را وارد کنید");
            if (uPass.isEmpty())
                password.setError(" رمز عبور خود را وارد کنید");
            return false;
        } else {
            return true;

        }

    }
}