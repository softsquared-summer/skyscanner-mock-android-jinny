package com.jeahn.skyscanner.src.main.exploreCity.interfaces;

import com.jeahn.skyscanner.src.main.exploreCity.models.FlightResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExploreCityRetrofitInterface {
    @GET("/flight")
    Call<FlightResponse> getFlightList(@Query("case") String caseOorR,
                                       @Query("deAirPortCode") String deAirPortCode,
                                       @Query("country") String country);
}
