package com.test.cogniwide.ui.screenfirst;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.test.cogniwide.R;
import com.test.cogniwide.databinding.ActivityLoginBinding;
import com.test.cogniwide.ui.screensecond.MovieListActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    Activity mActivity;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = LoginActivity.this;
        mContext = LoginActivity.this;

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        activityLoginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    Intent movieListIntent = MovieListActivity.startIntent(mContext);
                    startActivity(movieListIntent);
                    finish();
                }
            }
        });


    }

    private boolean validateForm() {
        if (getEmail().isEmpty()) {
            Toast.makeText(this, "Enter email address !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()) {
            Toast.makeText(this, "Enter a valid email address !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (getPassword().isEmpty()) {
            Toast.makeText(this, "Enter password !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!(getPassword().length() > 5 && getPassword().length() <= 12)) {
            Toast.makeText(this, "Password length should be between 6 - 12 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private String getEmail() {
        return Objects.requireNonNull(activityLoginBinding.etEmail.getText()).toString();
    }

    private String getPassword() {
        return Objects.requireNonNull(activityLoginBinding.etPassword.getText()).toString();
    }
}