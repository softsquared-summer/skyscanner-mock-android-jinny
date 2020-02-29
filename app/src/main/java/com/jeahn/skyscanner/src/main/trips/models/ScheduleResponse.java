package com.jeahn.skyscanner.src.main.trips.models;

import com.google.gson.annotations.SerializedName;
import com.jeahn.skyscanner.src.flights.models.DailyOneFlightResult;

import java.util.List;

public class ScheduleResponse {
    @SerializedName("result")
    private List<Schedule> result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public List<Schedule> getResult() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
