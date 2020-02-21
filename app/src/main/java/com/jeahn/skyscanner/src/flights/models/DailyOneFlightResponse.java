package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

public class DailyOneFlightResponse {
    @SerializedName("result")
    private DailyOneFlightResult result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public DailyOneFlightResult getResult() {
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
