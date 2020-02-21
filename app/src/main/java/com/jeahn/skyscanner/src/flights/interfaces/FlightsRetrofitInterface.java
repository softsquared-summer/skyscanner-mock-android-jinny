package com.jeahn.skyscanner.src.flights.interfaces;

import com.jeahn.skyscanner.src.flights.models.CityResponse;
import com.jeahn.skyscanner.src.flights.models.DailyOneFlightResponse;
import com.jeahn.skyscanner.src.flights.models.OneFlightResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlightsRetrofitInterface {
    @GET("/city")
    Call<CityResponse> getCityList();

    @GET("/one-flight")
    Call<OneFlightResponse> getOneFlight(@Query("deAirPortCode") String deAirPortCode,
                                         @Query("arAirPortCode") String arAirPortCode,
                                         @Query("deDate") String deDate,
                                         @Query("seatCode") int seatCode,
                                         @Query("sortBy") String sortBy);

    @GET("/daily-one-flight")
    Call<DailyOneFlightResponse> getDailyOneFlight(@Query("deAirPortCode") String deAirPortCode,
                                                   @Query("arAirPortCode") String arAirPortCode,
                                                   @Query("deDate") String deDate,
                                                   @Query("seatCode") int seatCode);
}
