package com.jeahn.skyscanner.src.main.exploreCountry.interfaces;

import com.jeahn.skyscanner.src.main.exploreCountry.models.FlightResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExploreCountryRetrofitInterface {
    @GET("/flight")
    Call<FlightResponse> getFlightList(@Query("case") String caseOorR,
                                       @Query("deAirPortCode") String deAirPortCode);
}
