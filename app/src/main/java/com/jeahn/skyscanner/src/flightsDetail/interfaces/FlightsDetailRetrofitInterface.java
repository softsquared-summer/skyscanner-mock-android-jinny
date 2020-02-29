package com.jeahn.skyscanner.src.flightsDetail.interfaces;

import com.jeahn.skyscanner.src.flightsDetail.models.AddScheduleResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FlightsDetailRetrofitInterface {
    @POST("/schedule")
    Call<AddScheduleResponse> postAddSchedule(@Body RequestBody requestBody);
}
