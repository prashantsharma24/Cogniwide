package com.test.cogniwide.viewmodels;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.cogniwide.data.model.LoginScreenModel;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    MutableLiveData<LoginScreenModel> mLoginScreenModelMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<LoginScreenModel> getLoginData() {
        return mLoginScreenModelMutableLiveData;
    }

    public void onClick(View v) {
        LoginScreenModel model = new LoginScreenModel(emailAddress.getValue(), password.getValue());
        mLoginScreenModelMutableLiveData.setValue(model);
    }

    public MutableLiveData<String> getEmailAddress() {
        return emailAddress;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }
}
