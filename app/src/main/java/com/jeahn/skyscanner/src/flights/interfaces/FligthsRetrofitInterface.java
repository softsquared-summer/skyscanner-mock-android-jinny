package com.jeahn.skyscanner.src.flights.interfaces;

import com.jeahn.skyscanner.src.flights.models.CityResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FligthsRetrofitInterface {
    @GET("/city")
    Call<CityResponse> getCityList();
}
