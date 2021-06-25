package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyRoundAirLine {
    @SerializedName("airLineKr")
    private String airLineKr;

    @SerializedName("airLineEn")
    private String airLineEn;

    @SerializedName("airLineImgUrl")
    private String airLineImgUrl;

    @SerializedName("deMinAdultPrice")
    private int deMinAdultPrice;

    @SerializedName("deMinInfantPrice")
    private int deMinInfantPrice;

    @SerializedName("deMinChildPrice")
    private int deMinChildPrice;

    @SerializedName("reMinAdultPrice")
    private int reMinAdultPrice;

    @SerializedName("reMinInfantPrice")
    private int reMinInfantPrice;

    @SerializedName("reMinChildPrice")
    private int reMinChildPrice;

    @SerializedName("deTicketList")
    private List<Ticket> deTicketList;

    @SerializedName("reTicketList")
    private List<Ticket> reTicketList;

    public String getAirLineKr() {
        return airLineKr;
    }

    public String getAirLineEn() {
        return airLineEn;
    }

    public String getAirLineImgUrl() {
        return airLineImgUrl;
    }

    public int getDeMinAdultPrice() {
        return deMinAdultPrice;
    }

    public int getDeMinInfantPrice() {
        return deMinInfantPrice;
    }

    public int getDeMinChildPrice() {
        return deMinChildPrice;
    }

    public int getReMinAdultPrice() {
        return reMinAdultPrice;
    }

    public int getReMinInfantPrice() {
        return reMinInfantPrice;
    }

    public int getReMinChildPrice() {
        return reMinChildPrice;
    }

    public List<Ticket> getDeTicketList() {
        return deTicketList;
    }

    public List<Ticket> getReTicketList() {
        return reTicketList;
    }
}
