package com.jeahn.skyscanner.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;

public class LoginSuccessActivity extends BaseActivity {
    private static int REGISTER_SUCCESS = 100;
    private static int LOGIN_SUCCESS = 200;

    private TextView mTvMessage;

    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        mTvMessage = findViewById(R.id.login_success_tv_message);

        Intent intent = getIntent();
        if(intent != null){
            String message = intent.getStringExtra("message");
            mTvMessage.setText(message);
            type = intent.getIntExtra("type", 0);
        }

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            finish();
            setResult(type);
//            LoginActivity.mLoginActivity.finish();
//            if(type == LOGIN_SUCCESS){
//                LoginMainActivity.mLogintMainActivity.finish();
//            }
        }, 2000);


    }

    @Override
    public void onBackPressed() {
        //뒤로가기 버튼 못누르게 함
    }
}
