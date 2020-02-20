package com.jeahn.skyscanner.src.flights;

import android.util.Log;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.flights.interfaces.FlightsActivityView;
import com.jeahn.skyscanner.src.flights.interfaces.FlightsRetrofitInterface;
import com.jeahn.skyscanner.src.flights.models.CityResponse;
import com.jeahn.skyscanner.src.flights.models.OneFlightResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightsService {
    private final FlightsActivityView mFlightsActivityView;

    public FlightsService(final FlightsActivityView flightsActivityView){
        this.mFlightsActivityView = flightsActivityView;
    }

    //도시리스트 조회 api
    public void getCityList(){
        final FlightsRetrofitInterface flightsRetrofitInterface =
                ApplicationClass.getRetrofit().create(FlightsRetrofitInterface.class);
        flightsRetrofitInterface.getCityList().enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                final CityResponse cityResponse = response.body();
                if(cityResponse ==null){
                    mFlightsActivityView.validateFailure(null);
                    return;
                }

                mFlightsActivityView.validateSuccess(cityResponse.getCityList());
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                mFlightsActivityView.validateFailure(null);
            }
        });
    }

    //직항 항공편 리스트 조회 api
    public void getOneFlight(String deAirPortCode, String arAirPortCode, String deDate, int seatCode, String sortBy) {
        Log.d("JEAHN", "oneflight");
        final FlightsRetrofitInterface flightsRetrofitInterface =
                ApplicationClass.getRetrofit().create(FlightsRetrofitInterface.class);
        flightsRetrofitInterface.getOneFlight(deAirPortCode, arAirPortCode, deDate, seatCode, sortBy).enqueue(new Callback<OneFlightResponse>() {
            @Override
            public void onResponse(Call<OneFlightResponse> call, Response<OneFlightResponse> response) {
                OneFlightResponse oneFlightResponse = response.body();
                if(oneFlightResponse == null){
                    mFlightsActivityView.validateFailure(null);
                    return;
                }

                mFlightsActivityView.validateSuccess(oneFlightResponse.getResult());
            }

            @Override
            public void onFailure(Call<OneFlightResponse> call, Throwable t) {
                mFlightsActivityView.validateFailure(null);
            }
        });
    }
}
