package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

public class RoundFlightResponse {
    @SerializedName("result")
    private RoundFlightResult result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public RoundFlightResult getResult() {
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
