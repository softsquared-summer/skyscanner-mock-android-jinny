package com.jeahn.skyscanner.src.main.profile.login;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.jeahn.skyscanner.R;

public class LoginRegisterFragment extends Fragment {
    private EditText mEtEmail, mEtPassword;

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

        mEtEmail.setText(mStrEmail);

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
}
