package com.jeahn.skyscanner.src.main.exploreCity;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.main.exploreCity.interfaces.ExploreCityActivityView;
import com.jeahn.skyscanner.src.main.exploreCity.interfaces.ExploreCityRetrofitInterface;
import com.jeahn.skyscanner.src.main.exploreCity.models.FlightResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreCityService {
    private final ExploreCityActivityView mExploreCityActivityView;

    public ExploreCityService(ExploreCityActivityView mExploreCityActivityView) {
        this.mExploreCityActivityView = mExploreCityActivityView;
    }

    public void getFlightList(String caseOorR, String deAirPortCode, String country) {
        final ExploreCityRetrofitInterface exploreCityRetrofitInterface =
                ApplicationClass.getRetrofit().create(ExploreCityRetrofitInterface.class);
        exploreCityRetrofitInterface.getFlightList(caseOorR, deAirPortCode, country).enqueue(new Callback<FlightResponse>() {
            @Override
            public void onResponse(Call<FlightResponse> call, Response<FlightResponse> response) {
                FlightResponse flightResponse = response.body();
                if (flightResponse == null) {
                    mExploreCityActivityView.getFlightListFailure(null);
                    return;
                }

                mExploreCityActivityView.getFlightListSuccess(flightResponse.getResult());
            }

            @Override
            public void onFailure(Call<FlightResponse> call, Throwable t) {
                mExploreCityActivityView.getFlightListFailure(null);
            }
        });
    }
}
