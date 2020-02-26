package com.jeahn.skyscanner.src.main.profile.login;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;

public class LoginRegisterSuccessActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_success);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            finish();
            LoginActivity.mLoginActivity.finish();
        }, 3000);


    }

    @Override
    public void onBackPressed() {
        //뒤로가기 버튼 못누르게 함
    }
}
