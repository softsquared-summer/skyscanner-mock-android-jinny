package com.jeahn.skyscanner.src.flightsDetail.interfaces;

import com.jeahn.skyscanner.src.flightsDetail.models.AddScheduleResponse;
import com.jeahn.skyscanner.src.flightsDetail.models.DeleteScheduleResponse;
import com.jeahn.skyscanner.src.flightsDetail.models.IsSavedFlightResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FlightsDetailRetrofitInterface {
    @POST("/schedule")
    Call<AddScheduleResponse> postAddSchedule(@Body RequestBody requestBody);

    @GET("/user/flight")
    Call<IsSavedFlightResponse> getIsSavedFlight(@Query("deFlightId") int deFlightId);

    @PATCH("/schedule/item")
    Call<DeleteScheduleResponse> deleteSchedule(@Body RequestBody requestBody);
}
