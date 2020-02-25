package com.jeahn.skyscanner.src.main.explore.exploreCountry;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.main.explore.exploreCountry.interfaces.ExploreCountryActivityView;
import com.jeahn.skyscanner.src.main.explore.exploreCountry.interfaces.ExploreCountryRetrofitInterface;
import com.jeahn.skyscanner.src.main.explore.exploreCountry.models.FlightResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreCountryService {
    private final ExploreCountryActivityView mExploreCountryActivityView;

    public ExploreCountryService(ExploreCountryActivityView mExploreCountryActivityView) {
        this.mExploreCountryActivityView = mExploreCountryActivityView;
    }

    //둘러보기 api
    public void getFlightList(String caseOorR, String deAirPortCode) {
        final ExploreCountryRetrofitInterface exploreCountryRetrofitInterface =
                ApplicationClass.getRetrofit().create(ExploreCountryRetrofitInterface.class);
        exploreCountryRetrofitInterface.getFlightList(caseOorR, deAirPortCode).enqueue(new Callback<FlightResponse>() {
            @Override
            public void onResponse(Call<FlightResponse> call, Response<FlightResponse> response) {
                FlightResponse flightResponse = response.body();
                if (flightResponse == null) {
                    mExploreCountryActivityView.getFlightListFailure(null);
                    return;
                }

                mExploreCountryActivityView.getFlightListSuccess(flightResponse.getResult());
            }

            @Override
            public void onFailure(Call<FlightResponse> call, Throwable t) {
                mExploreCountryActivityView.getFlightListFailure(null);
            }
        });
    }
}
