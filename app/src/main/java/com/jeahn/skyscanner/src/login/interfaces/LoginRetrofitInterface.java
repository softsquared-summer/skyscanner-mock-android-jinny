package com.jeahn.skyscanner.src.login.interfaces;

import com.jeahn.skyscanner.src.login.models.EmailResponse;
import com.jeahn.skyscanner.src.login.models.LoginResponse;
import com.jeahn.skyscanner.src.login.models.RegisterResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    @POST("/auth/email")
    Call<EmailResponse> postEmail(@Body RequestBody requestBody);

    @POST("/user")
    Call<RegisterResponse> postRegister(@Body RequestBody requestBody);

    @POST("/auth")
    Call<LoginResponse> postLogin(@Body RequestBody requestBody);
}
