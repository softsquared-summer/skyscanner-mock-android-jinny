package com.jeahn.skyscanner.src.flights.flightsSearch.city;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.flights.flightsSearch.city.interfaces.CityActivityView;
import com.jeahn.skyscanner.src.flights.flightsSearch.city.interfaces.CityRetrofitInterface;
import com.jeahn.skyscanner.src.flights.flightsSearch.city.models.CityResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityService {
    private final CityActivityView mCityActivityView;

    public CityService(CityActivityView mCityActivityView) {
        this.mCityActivityView = mCityActivityView;
    }

    //도시리스트 조회 api
    public void getCityList() {
        final CityRetrofitInterface cityRetrofitInterface =
                ApplicationClass.getRetrofit().create(CityRetrofitInterface.class);
        cityRetrofitInterface.getCityList().enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                final CityResponse cityResponse = response.body();
                if (cityResponse == null) {
                    mCityActivityView.getCityListFailure(null);
                    return;
                }

                mCityActivityView.getCityListSuccess(cityResponse.getCityList());
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                mCityActivityView.getCityListFailure(null);
            }
        });
    }
}
