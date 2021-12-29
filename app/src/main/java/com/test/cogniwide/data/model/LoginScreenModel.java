package com.test.cogniwide.data.model;

import android.util.Patterns;

public class LoginScreenModel {

    private String email;
    private String password;

    public LoginScreenModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthBetween6and12() {
        return getPassword().length() > 5 && getPassword().length() <= 12;
    }
}
