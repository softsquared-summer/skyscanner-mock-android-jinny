package com.jeahn.skyscanner.src.main.trips.interfaces;

import com.jeahn.skyscanner.src.flights.models.DailyOneFlightResponse;
import com.jeahn.skyscanner.src.main.trips.models.ScheduleResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TripsRetrofitInterface {
    @GET("/schedule")
    Call<ScheduleResponse> getSchedule();
}
