package com.test.cogniwide.ui.screenfirst;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.test.cogniwide.R;
import com.test.cogniwide.data.model.LoginScreenModel;
import com.test.cogniwide.databinding.ActivityLoginBinding;
import com.test.cogniwide.ui.screensecond.MovieListActivity;
import com.test.cogniwide.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    ActivityLoginBinding activityLoginBinding;
    private LoginViewModel mLoginViewModel;
    boolean isEmailCorrect, isPasswordCorrect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLoginModel(mLoginViewModel);
        activityLoginBinding.btnLogin.setEnabled(false);

        mLoginViewModel.getEmailAddress().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    isEmailCorrect = true;
                    setEnableLoginButton();
                } else {
                    isEmailCorrect = false;
                    activityLoginBinding.btnLogin.setBackgroundColor(getResources().getColor(R.color.darker_gray));
                }
            }
        });

        mLoginViewModel.getPassword().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty() && s.length() > 5 && s.length() <= 12) {
                    isPasswordCorrect = true;
                    setEnableLoginButton();
                } else {
                    isPasswordCorrect = false;
                    activityLoginBinding.btnLogin.setBackgroundColor(getResources().getColor(R.color.darker_gray));
                }
            }
        });

        mLoginViewModel.getLoginData().observe(this, new Observer<LoginScreenModel>() {
            @Override
            public void onChanged(LoginScreenModel loginModel) {
                Intent intent = new Intent(getApplicationContext(), MovieListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }

    private void setEnableLoginButton() {
        activityLoginBinding.btnLogin.setEnabled(isEmailCorrect && isPasswordCorrect);
        if (isEmailCorrect && isPasswordCorrect) {
            activityLoginBinding.btnLogin.setBackgroundColor(getResources().getColor(R.color.purple_500));
        }

    }
}