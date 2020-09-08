package com.byethost17.pocketful.travels;

import android.content.Context;
import android.content.SharedPreferences; //

public class Register {

    private String username, password, password2, email;
    private static final String PREFERENCES_FILE_NAME = "com.byethost17.pocketful.travels";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBER_ME_KEY = "rememberMe";

    private SharedPreferences sharedPrefs;
    public Register(String username, String password, String password2, String email) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.email = email;
    }

    public Register(Context context) {
        this.sharedPrefs = context.getSharedPreferences(Register.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getUsername() {
        return this.sharedPrefs.getString(USERNAME_KEY, "");
    }

    public void setUsername(String username) {
        this.sharedPrefs.edit().putString(USERNAME_KEY, username).apply();
    }

    public String getPassword() {
        return this.sharedPrefs.getString(PASSWORD_KEY, "");
    }

    public void setPassword(String password) {
        this.sharedPrefs.edit().putString(PASSWORD_KEY, password).apply();
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRemembered() {
        return this.sharedPrefs.getBoolean(REMEMBER_ME_KEY, false);
    }

    public void setRemembered(boolean remembered) {
        this.sharedPrefs.edit().putBoolean(REMEMBER_ME_KEY, remembered).apply();
    }
}
