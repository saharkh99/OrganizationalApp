package com.example.organizationalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organizationalapp.ForntPart.BaseContext;
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
                String uname = username.getText().toString().trim();
                String upass = password.getText().toString().trim();

                if (checkNotEmpty(uname, upass)) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    User.name=uname;
                    intent.putExtra("name", uname);
                    startActivity(intent);
                }
            }
        });
    }

    private void findView() {
        login = findViewById(R.id.login);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
    }

    private boolean checkNotEmpty(String uname, String upass) {
        if (uname.isEmpty() || upass.isEmpty()) {
            login.setEnabled(false);
            if (uname.isEmpty())
                username.setError("نام کاربری خود را وارد کنید");
            if (upass.isEmpty())
                password.setError(" رمز عبور خود را وارد کنید");
            return false;
        } else {
            return true;

        }

    }
}