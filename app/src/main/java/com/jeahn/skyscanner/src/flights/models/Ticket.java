package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

public class Ticket {
    @SerializedName("flightId")
    private int flightId;

    @SerializedName("airPlaneCode")
    private String airPlaneCode;

    @SerializedName("airLineKr")
    private String airLineKr;

    @SerializedName("airLineEn")
    private String airLineEn;

    @SerializedName("deTime")
    private String deTime;

    @SerializedName("arTime")
    private String arTime;

    @SerializedName("price")
    private String price;

    @SerializedName("timeGap")
    private String timeGap;

    public int getFlightId() {
        return flightId;
    }

    public String getAirPlaneCode() {
        return airPlaneCode;
    }

    public String getAirLineKr() {
        return airLineKr;
    }

    public String getAirLineEn() {
        return airLineEn;
    }

    public String getDeTime() {
        return deTime;
    }

    public String getArTime() {
        return arTime;
    }

    public String getPrice() {
        return price;
    }

    public String getTimeGap() {
        return timeGap;
    }
}
