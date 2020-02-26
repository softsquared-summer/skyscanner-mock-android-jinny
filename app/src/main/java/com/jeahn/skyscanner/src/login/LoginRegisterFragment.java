package com.jeahn.skyscanner.src.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.jeahn.skyscanner.R;

public class LoginRegisterFragment extends Fragment {
    private EditText mEtEmail, mEtPassword;
    private TextView mTvPassword;
    private ImageView mIvError;
    private LinearLayout mLinearError;
    private TextInputLayout mPasswordLayout;

    private String mStrEmail;

    public LoginRegisterFragment(String strEmail) {
        this.mStrEmail = strEmail;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_register, container, false);

        mEtEmail = v.findViewById(R.id.login_register_et_email);
        mEtPassword = v.findViewById(R.id.login_register_et_password);
        mTvPassword = v.findViewById(R.id.login_register_tv_password_title);
        mIvError = v.findViewById(R.id.login_register_iv_error);
        mLinearError = v.findViewById(R.id.login_register_linear_error);
        mPasswordLayout = v.findViewById(R.id.login_register_et_password_layout);

        mEtEmail.setText(mStrEmail);
        mEtPassword.requestFocus();

        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 > 0) {
                    ((LoginActivity) getActivity()).setButtonEnable(true);
                } else {
                    ((LoginActivity) getActivity()).setButtonEnable(false);
                }
                hidePasswordError();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return v;
    }

    public String getEmail() {
        return mStrEmail;
    }

    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    public void showPasswordError() {
        mEtPassword.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.loginErrorColor));
        mTvPassword.setTextColor(getResources().getColor(R.color.loginErrorColor));
        mIvError.setVisibility(View.VISIBLE);
        mLinearError.setVisibility(View.VISIBLE);
        mPasswordLayout.setPasswordVisibilityToggleEnabled(false);
    }

    public void hidePasswordError(){
        mEtPassword.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.skyscannerColor));
        mTvPassword.setTextColor(getResources().getColor(R.color.skyscannerColor));
        mIvError.setVisibility(View.INVISIBLE);
        mLinearError.setVisibility(View.INVISIBLE);
        mPasswordLayout.setPasswordVisibilityToggleEnabled(true);
    }
}
