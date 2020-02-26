package com.jeahn.skyscanner.src.main.profile.login;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.main.profile.login.interfaces.LoginActivityView;

public class LoginActivity extends BaseActivity implements LoginActivityView {
    public static LoginActivity mLoginActivity;

    private Toolbar mToolbar;

    private Button mBtnNext;

    private LoginEmailFragment mLoginEmailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginActivity = this;

        mToolbar = findViewById(R.id.login_email_toolbar);
        mBtnNext = findViewById(R.id.login_email_btn_next);

        mBtnNext.setOnClickListener(view -> {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.login_frame);
            if (fragment instanceof LoginEmailFragment) {
                tryPostEmail();
            } else if (fragment instanceof LoginRegisterFragment) {
                String email = ((LoginRegisterFragment) fragment).getEmail();
                String password = ((LoginRegisterFragment) fragment).getPassword();
                tryPostRegister(email, password);
            }
        });

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.mainTextColor), PorterDuff.Mode.SRC_ATOP);

        mLoginEmailFragment = new LoginEmailFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.login_frame, mLoginEmailFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //툴바의 <- 버튼 누르면 끝내기
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void tryPostEmail() {
        LoginService loginService = new LoginService(this);
        loginService.postEmail(mLoginEmailFragment.getEmail());
    }

    private void tryPostRegister(String email, String password) {
        LoginService loginService = new LoginService(this);
        loginService.postRegister(email, password);
    }

    @Override
    public void postEmailSuccess(int result) {
        switch (result) {
            case 100:
                showCustomToast("해당 이메일이 존재합니다");
                break;
            case 200:
                mBtnNext.setText(getString(R.string.login_button_register));
                setButtonEnable(false);
                LoginRegisterFragment loginRegisterFragment = new LoginRegisterFragment(mLoginEmailFragment.getEmail());
                getSupportFragmentManager().beginTransaction().replace(R.id.login_frame, loginRegisterFragment).addToBackStack(null).commit();
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

    @Override
    public void postRegisterSuccess(int result) {
        switch (result) {
            case 100:
                Intent intent = new Intent(getApplicationContext(), LoginRegisterSuccessActivity.class);
                startActivity(intent);
                break;
            case 201:
                showCustomToast("비밀번호 오류");
                break;
            case 300:
                showCustomToast("회원가입 실패");
                break;
        }
    }

    @Override
    public void postRegisterFailure(String message) {
        showCustomToast("가입 실패");
    }

    public void setButtonEnable(boolean isEnable) {
        if (isEnable) {
            mBtnNext.setEnabled(true);
        } else {
            mBtnNext.setEnabled(false);
        }
    }
}
