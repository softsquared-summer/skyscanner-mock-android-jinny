package com.jeahn.skyscanner.src.flights.flightsSearch.city.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResponse {
    @SerializedName("result")
    private List<City> cityList;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public List<City> getCityList() {
        return cityList;
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
