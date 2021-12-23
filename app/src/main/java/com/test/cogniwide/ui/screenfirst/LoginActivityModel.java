package com.test.cogniwide.ui.screenfirst;

public class LoginActivityModel {

    private String mEmail;
    private String mPassword;


    public LoginActivityModel(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public String getEmail() {
        if (mEmail == null) {
            return "";
        }
        return mEmail;
    }


    public String getPassword() {

        if (mPassword == null) {
            return "";
        }
        return mPassword;
    }

}
