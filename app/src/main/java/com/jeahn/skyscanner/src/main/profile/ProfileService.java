package com.jeahn.skyscanner.src.main.profile;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.main.profile.interfaces.ProfileActivityView;
import com.jeahn.skyscanner.src.main.profile.interfaces.ProfileRetrofitInterface;
import com.jeahn.skyscanner.src.main.profile.models.TokenValidateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileService {
    private final ProfileActivityView mProfileActivityView;

    public ProfileService(ProfileActivityView mProfileActivityView) {
        this.mProfileActivityView = mProfileActivityView;
    }

    public void getTokenValidate(String token) {
        final ProfileRetrofitInterface profileRetrofitInterface =
                ApplicationClass.getRetrofit().create(ProfileRetrofitInterface.class);
        profileRetrofitInterface.getTokenValidate(token).enqueue(new Callback<TokenValidateResponse>() {
            @Override
            public void onResponse(Call<TokenValidateResponse> call, Response<TokenValidateResponse> response) {
                TokenValidateResponse tokenValidateResponse = response.body();
                if (tokenValidateResponse == null) {
                    mProfileActivityView.getTokenValidateFailure(null);
                    return;
                }
                switch (tokenValidateResponse.getCode()) {
                    case 100:
                        mProfileActivityView.getTokenValidateSuccess(tokenValidateResponse.getResult().getEmail());
                        break;
                    case 200:
                        mProfileActivityView.getTokenValidateFailure(null);
                        break;
                }

            }

            @Override
            public void onFailure(Call<TokenValidateResponse> call, Throwable t) {
                mProfileActivityView.getTokenValidateFailure(null);
            }
        });
    }
}
