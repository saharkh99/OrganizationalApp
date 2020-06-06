package com.example.organizationalapp;

public class User {
    static String name;
    String password;

    public static String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
