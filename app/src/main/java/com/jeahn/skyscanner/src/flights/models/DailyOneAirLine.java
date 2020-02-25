package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyOneAirLine {
    @SerializedName("airLineKr")
    private String airLineKr;

    @SerializedName("airLineEn")
    private String airLineEn;

    @SerializedName("airLineImgUrl")
    private String airLineImgUrl;

    @SerializedName("minAdultPrice")
    private int minAdultPrice;

    @SerializedName("minInfantPrice")
    private int minInfantPrice;

    @SerializedName("minChildPrice")
    private int minChildPrice;

    @SerializedName("ticketList")
    private List<Ticket> ticketList;

    public String getAirLineKr() {
        return airLineKr;
    }

    public String getAirLineEn() {
        return airLineEn;
    }

    public String getAirLineImgUrl() {
        return airLineImgUrl;
    }

    public int getMinAdultPrice() {
        return minAdultPrice;
    }

    public int getMinInfantPrice() {
        return minInfantPrice;
    }

    public int getMinChildPrice() {
        return minChildPrice;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }
}
