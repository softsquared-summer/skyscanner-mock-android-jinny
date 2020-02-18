package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("airPortCode")
    private String airPortCode;

    @SerializedName("cityNameKr")
    private String cityNameKr;

    @SerializedName("cityNameEn")
    private String cityNameEn;

    @SerializedName("country")
    private String country;

    public String getAirPortCode() {
        return airPortCode;
    }

    public String getCityNameKr() {
        return cityNameKr;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public String getCountry() {
        return country;
    }
}
