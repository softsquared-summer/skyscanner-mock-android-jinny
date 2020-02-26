package com.jeahn.skyscanner.src.main.profile.login;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.main.profile.login.interfaces.LoginActivityView;
import com.jeahn.skyscanner.src.main.profile.login.interfaces.LoginRetrofitInterface;
import com.jeahn.skyscanner.src.main.profile.login.models.EmailResponse;
import com.jeahn.skyscanner.src.main.profile.login.models.RegisterResponse;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json; charset=utf-8"));
        loginRetrofitInterface.postEmail(requestBody).enqueue(new Callback<EmailResponse>() {
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

    public void postRegister(String email, String password) {
        final LoginRetrofitInterface loginRetrofitInterface =
                ApplicationClass.getRetrofit().create(LoginRetrofitInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json; charset=utf-8"));
        loginRetrofitInterface.postRegister(requestBody).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if (registerResponse == null) {
                    mLoginActivityView.postRegisterFailure(null);
                    return;
                }

                mLoginActivityView.postRegisterSuccess(registerResponse.getCode());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                mLoginActivityView.postRegisterFailure(null);
            }
        });
    }
}
