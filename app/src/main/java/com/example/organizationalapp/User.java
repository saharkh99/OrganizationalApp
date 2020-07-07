package com.example.organizationalapp;

public class User {
    static String name;
    static String role;

    public static String getRole() {
        return role;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static void setRole(String role) {
        User.role = role;
    }

    public static String getName() {
        return name;
    }
}
