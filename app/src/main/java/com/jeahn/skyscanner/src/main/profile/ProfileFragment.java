package com.jeahn.skyscanner.src.main.profile;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.login.LoginMainActivity;
import com.jeahn.skyscanner.src.login.interfaces.LoginActivityView;
import com.jeahn.skyscanner.src.main.profile.interfaces.ProfileActivityView;
import com.jeahn.skyscanner.src.preferences.PreferencesActivity;

import static com.jeahn.skyscanner.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.jeahn.skyscanner.src.ApplicationClass.sSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements NestedScrollView.OnScrollChangeListener, Button.OnClickListener, ProfileActivityView {
    private static int LOGIN_SUCCESS = 200;
    private static int LOGOUT_SUCCESS = 300;

    private Toolbar mToolbar;
    private NestedScrollView mScrollView;
    private LinearLayout mLinearTraveler;
    private RelativeLayout mRelativePreferences;
    private View mToolbarDivider;
    private Button mBtnLogin;
    private TextView mTvEdit, mTvContent, mTvName;
    private ImageView mIv1;

    private boolean isLoginCheck= false;
    private boolean isLogin = false;
    private String name;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        mToolbar = v.findViewById(R.id.profile_toolbar);
        mScrollView = v.findViewById(R.id.profile_scroll);
        mLinearTraveler = v.findViewById(R.id.profile_linear_traveler);
        mRelativePreferences = v.findViewById(R.id.profile_btn_preferences);
        mToolbarDivider = v.findViewById(R.id.profile_toolbar_divider);
        mBtnLogin = v.findViewById(R.id.profile_btn_login);
        mTvEdit = v.findViewById(R.id.profile_tv_edit);
        mTvContent = v.findViewById(R.id.profile_tv_content);
        mTvName = v.findViewById(R.id.profile_tv_name);
        mIv1 = v.findViewById(R.id.profile_iv_1);

        mBtnLogin.setOnClickListener(this);
        mScrollView.setOnScrollChangeListener(this);
        mRelativePreferences.setOnClickListener(this);

        if(!isLoginCheck){
            tryGetTokenValidate();
        }else{
            setLoginView(isLogin);
        }


        return v;
    }

    private void tryGetTokenValidate() {
        String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);

        if(jwtToken != null){
            ProfileService profileService = new ProfileService(this);
            profileService.getTokenValidate(jwtToken);
        }else{
            mBtnLogin.setVisibility(View.VISIBLE);
            mTvContent.setVisibility(View.VISIBLE);
            mIv1.setVisibility(View.VISIBLE);
            setLoginView(false);
        }
        isLoginCheck = true;
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY < 40) {
            mToolbar.setTitle("");
            mToolbarDivider.setVisibility(View.INVISIBLE);
        } else {
            mToolbar.setTitle(getString(R.string.profile_title));
            mToolbarDivider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_btn_login:
                Intent intent = new Intent(getContext(), LoginMainActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.profile_btn_preferences:
                Intent preferencesIntent = new Intent(getContext(), PreferencesActivity.class);
                preferencesIntent.putExtra("isLogin", isLogin);
                startActivityForResult(preferencesIntent, 0);
        }
    }

    @Override
    public void getTokenValidateSuccess(String email) {
        name = email.substring(0, 2).toUpperCase();

        isLogin = true;
        setLoginView(true);
    }

    @Override
    public void getTokenValidateFailure(String message) {
        isLogin = false;
        setLoginView(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == LOGIN_SUCCESS){ //로그인 성공
            tryGetTokenValidate();
        }else if(resultCode == LOGOUT_SUCCESS){ //로그아웃
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString(X_ACCESS_TOKEN, null);
            editor.commit();
            setLoginView(false);
            isLogin = false;
        }
    }

    private void setLoginView(boolean isLogin){
        if(isLogin){
            mBtnLogin.setVisibility(View.GONE);
            mTvContent.setVisibility(View.GONE);
            mIv1.setVisibility(View.GONE);

            mTvEdit.setVisibility(View.VISIBLE);
            mLinearTraveler.setVisibility(View.VISIBLE);
            mTvName.setText(name);
            mTvName.setVisibility(View.VISIBLE);
        }else{
            mTvEdit.setVisibility(View.GONE);
            mLinearTraveler.setVisibility(View.GONE);
            mTvName.setVisibility(View.GONE);

            mBtnLogin.setVisibility(View.VISIBLE);
            mTvContent.setVisibility(View.VISIBLE);
            mIv1.setVisibility(View.VISIBLE);
        }
    }
}
