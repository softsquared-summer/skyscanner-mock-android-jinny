package com.jeahn.skyscanner.src.flights.interfaces;

import com.jeahn.skyscanner.src.flights.models.DailyOneFlightResponse;
import com.jeahn.skyscanner.src.flights.models.OneFlightResponse;
import com.jeahn.skyscanner.src.flights.models.RoundFlightResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlightsRetrofitInterface {
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

    @GET("/round-flight")
    Call<RoundFlightResponse> getRoundFlight(@Query("deAirPortCode") String deAirPortCode,
                                             @Query("arAirPortCode") String arAirPortCode,
                                             @Query("deDate") String deDate,
                                             @Query("arDate") String arDate,
                                             @Query("seatCode") int seatCode,
                                             @Query("sortBy") String sortBy);
}
