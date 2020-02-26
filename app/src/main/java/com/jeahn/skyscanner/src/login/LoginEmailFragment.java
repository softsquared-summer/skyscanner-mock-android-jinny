package com.jeahn.skyscanner.src.login;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.jeahn.skyscanner.R;

public class LoginEmailFragment extends Fragment {
    private EditText mEtEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_email, container, false);

        mEtEmail = v.findViewById(R.id.login_email_et_email);

        mEtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Patterns.EMAIL_ADDRESS.matcher(charSequence).matches()) {
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
        return mEtEmail.getText().toString();
    }


}
