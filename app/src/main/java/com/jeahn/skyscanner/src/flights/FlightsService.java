package com.jeahn.skyscanner.src.flights;

import android.util.Log;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.flights.interfaces.FlightsActivityView;
import com.jeahn.skyscanner.src.flights.interfaces.FlightsRetrofitInterface;
import com.jeahn.skyscanner.src.flights.models.DailyOneFlightResponse;
import com.jeahn.skyscanner.src.flights.models.OneFlightResponse;
import com.jeahn.skyscanner.src.flights.models.RoundFlightResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightsService {
    private final FlightsActivityView mFlightsActivityView;

    public FlightsService(final FlightsActivityView flightsActivityView) {
        this.mFlightsActivityView = flightsActivityView;
    }


    //일일 편도 항공편 리스트 조회 api
    public void getDailyOneFlight(String deAirPortCode, String arAirPortCode, String deDate, int seatCode) {
        final FlightsRetrofitInterface flightsRetrofitInterface =
                ApplicationClass.getRetrofit().create(FlightsRetrofitInterface.class);
        flightsRetrofitInterface.getDailyOneFlight(deAirPortCode, arAirPortCode, deDate, seatCode).enqueue(new Callback<DailyOneFlightResponse>() {
            @Override
            public void onResponse(Call<DailyOneFlightResponse> call, Response<DailyOneFlightResponse> response) {
                DailyOneFlightResponse oneDailyFlightResponse = response.body();
                if (oneDailyFlightResponse == null) {
                    mFlightsActivityView.getDailyOneFlightFailure(null);
                    return;
                }

                mFlightsActivityView.getDailyOneFlightSuccess(oneDailyFlightResponse.getResult());
            }

            @Override
            public void onFailure(Call<DailyOneFlightResponse> call, Throwable t) {
                mFlightsActivityView.getDailyOneFlightFailure(null);
            }
        });
    }

    //편도 항공편 리스트 조회 api
    public void getOneFlight(String deAirPortCode, String arAirPortCode, String deDate, int seatCode, String sortBy) {
        final FlightsRetrofitInterface flightsRetrofitInterface =
                ApplicationClass.getRetrofit().create(FlightsRetrofitInterface.class);
        flightsRetrofitInterface.getOneFlight(deAirPortCode, arAirPortCode, deDate, seatCode, sortBy).enqueue(new Callback<OneFlightResponse>() {
            @Override
            public void onResponse(Call<OneFlightResponse> call, Response<OneFlightResponse> response) {
                OneFlightResponse oneFlightResponse = response.body();
                if (oneFlightResponse == null) {
                    mFlightsActivityView.getOneFlightFailure(null);
                    return;
                }

                mFlightsActivityView.getOneFlightSuccess(oneFlightResponse.getResult());
            }

            @Override
            public void onFailure(Call<OneFlightResponse> call, Throwable t) {
                mFlightsActivityView.getOneFlightFailure(null);
            }
        });
    }

    //왕복 항공편 리스트 조회 api
    public void getRoundFlight(String deAirPortCode, String arAirPortCode, String deDate, String arDate,int seatCode, String sortBy) {
        final FlightsRetrofitInterface flightsRetrofitInterface =
                ApplicationClass.getRetrofit().create(FlightsRetrofitInterface.class);
        flightsRetrofitInterface.getRoundFlight(deAirPortCode, arAirPortCode, deDate, arDate, seatCode, sortBy).enqueue(new Callback<RoundFlightResponse>() {
            @Override
            public void onResponse(Call<RoundFlightResponse> call, Response<RoundFlightResponse> response) {
                RoundFlightResponse roundFlightResponse = response.body();
                if (roundFlightResponse == null) {
                    mFlightsActivityView.getRoundFlightFailure(null);
                    return;
                }

                mFlightsActivityView.getRoundFlightSuccess(roundFlightResponse.getResult());
            }

            @Override
            public void onFailure(Call<RoundFlightResponse> call, Throwable t) {
                mFlightsActivityView.getRoundFlightFailure(null);
            }
        });
    }
}
