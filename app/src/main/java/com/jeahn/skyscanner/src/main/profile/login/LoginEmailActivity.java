package com.jeahn.skyscanner.src.main.profile.login;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.main.profile.login.interfaces.LoginActivityView;

public class LoginEmailActivity extends BaseActivity implements LoginActivityView {
    private EditText mEtEmail;
    private Button mBtnNext;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        mToolbar = findViewById(R.id.login_email_toolbar);
        mEtEmail = findViewById(R.id.login_email_et_email);
        mBtnNext = findViewById(R.id.login_email_btn_next);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.mainTextColor), PorterDuff.Mode.SRC_ATOP);

        mEtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Patterns.EMAIL_ADDRESS.matcher(charSequence).matches()){
                    mBtnNext.setEnabled(true);
                }else{
                    mBtnNext.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBtnNext.setOnClickListener(view -> {
            tryPostEmail();
        });
    }

    private void tryPostEmail() {
        LoginService loginService = new LoginService(this);
        loginService.postEmail(mEtEmail.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //툴바의 <- 버튼 누르면 끝내기
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void postEmailSuccess(int result) {
        switch (result){
            case 100:
                showCustomToast("해당 이메일이 존재합니다");
                break;
            case 200:
                showCustomToast("해당 이메일이 존재하지 않습니다");
                break;
            case 300:
                showCustomToast("이메일형식을 다시 확인해주세요.");
                break;
        }
    }

    @Override
    public void postEmailFailure(String message) {
        showCustomToast("이메일 인증 실패");
    }
}
