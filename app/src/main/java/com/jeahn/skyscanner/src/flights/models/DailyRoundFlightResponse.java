package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

public class DailyRoundFlightResponse {
    @SerializedName("result")
    private DailyRoundFlightResult result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public DailyRoundFlightResult getResult() {
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
