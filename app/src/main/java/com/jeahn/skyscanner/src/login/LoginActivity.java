package com.jeahn.skyscanner.src.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.login.interfaces.LoginActivityView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity implements LoginActivityView {
    private static int REGISTER_SUCCESS = 100;
    private static int LOGIN_SUCCESS = 200;

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
                String password = ((LoginRegisterFragment) fragment).getPassword();
                if(isValidatePassword(password)){
                    String email = ((LoginRegisterFragment) fragment).getEmail();
                    tryPostRegister(email, password);
                }else{
                    //비밀번호 오류
                    ((LoginRegisterFragment) fragment).showPasswordError();
                }
            }else if(fragment instanceof  LoginPasswordFragment){
                String email = ((LoginPasswordFragment) fragment).getEmail();
                String password = ((LoginPasswordFragment) fragment).getPassword();
                tryPostLogtin(email, password);
            }
        });

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.mainTextColor), PorterDuff.Mode.SRC_ATOP);

        mLoginEmailFragment = new LoginEmailFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.login_frame, mLoginEmailFragment).commit();
    }

    private boolean isValidatePassword(String password) {
        String pattern = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,}$";
        Matcher matcher = Pattern.compile(pattern).matcher(password);

        if(matcher.matches() && !password.contains(" ")){
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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

    private void tryPostLogtin(String email, String password) {
        LoginService loginService = new LoginService(this);
        loginService.postLogin(email, password);
    }


    @Override
    public void postEmailSuccess(int result) {
        switch (result) {
            case 100:
                mBtnNext.setText(getString(R.string.login_button_login));
                setButtonEnable(false);
                LoginPasswordFragment loginPasswordFragment = new LoginPasswordFragment(mLoginEmailFragment.getEmail());
                getSupportFragmentManager().beginTransaction().replace(R.id.login_frame, loginPasswordFragment).addToBackStack(null).commit();
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
        showCustomToast("이메일 중복 확인 실패");
    }

    @Override
    public void postRegisterSuccess(int result) {
        switch (result) {
            case 100:
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.login_frame);
                String email = ((LoginRegisterFragment) fragment).getEmail();
                String password = ((LoginRegisterFragment) fragment).getPassword();
                tryPostLogtin(email, password);
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

    @Override
    public void postLoginSuccess(String token) {
        SharedPreferences.Editor editor = ApplicationClass.sSharedPreferences.edit();
        editor.putString(ApplicationClass.X_ACCESS_TOKEN, token);
        editor.commit();

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.login_frame);
        if(fragment instanceof LoginPasswordFragment){
            Intent intent = new Intent(getApplicationContext(), LoginSuccessActivity.class);
            intent.putExtra("type", LOGIN_SUCCESS);
            intent.putExtra("message", getString(R.string.login_success));
            startActivityForResult(intent, LOGIN_SUCCESS);
        }else if(fragment instanceof LoginRegisterFragment){
            Intent intent = new Intent(getApplicationContext(), LoginSuccessActivity.class);
            intent.putExtra("type", REGISTER_SUCCESS);
            intent.putExtra("message", getString(R.string.login_register_success));
            startActivityForResult(intent, REGISTER_SUCCESS);
        }
    }

    @Override
    public void postLoginFailure(String message) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.login_frame);
        ((LoginPasswordFragment)fragment).showPasswordError();
    }

    public void setButtonEnable(boolean isEnable) {
        if (isEnable) {
            mBtnNext.setEnabled(true);
        } else {
            mBtnNext.setEnabled(false);
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.login_frame);
        if (fragment instanceof LoginRegisterFragment || fragment instanceof  LoginPasswordFragment) {
            setButtonEnable(true);
            mBtnNext.setText(getString(R.string.login_button_next));
        }
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REGISTER_SUCCESS){
            setResult(REGISTER_SUCCESS);
            finish();
        }else if(requestCode == LOGIN_SUCCESS){
            setResult(LOGIN_SUCCESS);
            finish();
        }
    }
}
