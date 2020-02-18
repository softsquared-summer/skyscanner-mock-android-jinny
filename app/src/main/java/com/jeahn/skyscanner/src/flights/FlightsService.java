package com.jeahn.skyscanner.src.flights;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.flights.interfaces.FligthsActivityView;
import com.jeahn.skyscanner.src.flights.interfaces.FligthsRetrofitInterface;
import com.jeahn.skyscanner.src.flights.models.CityResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightsService {
    private final FligthsActivityView mFligthsActivityView;

    public FlightsService(final FligthsActivityView fligthsActivityView){
        this.mFligthsActivityView = fligthsActivityView;
    }

    public void getCityList(){
        final FligthsRetrofitInterface fligthsRetrofitInterface =
                ApplicationClass.getRetrofit().create(FligthsRetrofitInterface.class);
        fligthsRetrofitInterface.getCityList().enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                final CityResponse cityResponse = response.body();
                if(cityResponse ==null){
                    mFligthsActivityView.validateFailure(null);
                    return;
                }

                mFligthsActivityView.validateSuccess(cityResponse.getCityList());
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                mFligthsActivityView.validateFailure(null);
            }
        });
    }
}
