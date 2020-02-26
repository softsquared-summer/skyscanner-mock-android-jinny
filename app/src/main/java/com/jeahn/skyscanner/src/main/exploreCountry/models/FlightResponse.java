package com.jeahn.skyscanner.src.main.exploreCountry.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlightResponse {
    @SerializedName("result")
    private List<Country> result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public List<Country> getResult() {
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
