package com.jeahn.skyscanner.src.main.profile.interfaces;

import com.jeahn.skyscanner.src.main.profile.models.TokenValidateResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProfileRetrofitInterface {
    @GET("/auth/jwt")
    Call<TokenValidateResponse> getTokenValidate(@Query("x-access-token") String token);
}
