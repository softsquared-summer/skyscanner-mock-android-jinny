package com.jeahn.skyscanner.src.flights.flightsSearch.city.interfaces;

import com.jeahn.skyscanner.src.flights.flightsSearch.city.models.CityResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CityRetrofitInterface {
    @GET("/city")
    Call<CityResponse> getCityList();
}
