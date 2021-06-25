package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

public class OneFlightResponse {
    @SerializedName("result")
    private OneFlightResult result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public OneFlightResult getResult() {
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
