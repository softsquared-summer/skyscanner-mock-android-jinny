package com.jeahn.skyscanner.src.main.exploreCity.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlightResponse {
    @SerializedName("result")
    private List<City> result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public List<City> getResult() {
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
