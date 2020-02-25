package com.jeahn.skyscanner.src.main.profile.login;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.main.profile.login.interfaces.LoginActivityView;
import com.jeahn.skyscanner.src.main.profile.login.interfaces.LoginRetrofitInterface;
import com.jeahn.skyscanner.src.main.profile.login.models.EmailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {
    private final LoginActivityView mLoginActivityView;

    public LoginService(LoginActivityView mLoginActivityView) {
        this.mLoginActivityView = mLoginActivityView;
    }

    public void postEmail(String email) {
        final LoginRetrofitInterface loginRetrofitInterface =
                ApplicationClass.getRetrofit().create(LoginRetrofitInterface.class);

        loginRetrofitInterface.postEmail(email).enqueue(new Callback<EmailResponse>() {
            @Override
            public void onResponse(Call<EmailResponse> call, Response<EmailResponse> response) {
                EmailResponse emailResponse = response.body();
                if (emailResponse == null) {
                    mLoginActivityView.postEmailFailure(null);
                    return;
                }

                mLoginActivityView.postEmailSuccess(emailResponse.getCode());
            }

            @Override
            public void onFailure(Call<EmailResponse> call, Throwable t) {
                mLoginActivityView.postEmailFailure(null);
            }
        });
    }
}
