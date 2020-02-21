package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AirLine {
    @SerializedName("airLineKr")
    private String airLineKr;

    @SerializedName("airLineEn")
    private String airLineEn;

    @SerializedName("airLineImgUrl")
    private String airLineImgUrl;

    @SerializedName("minPrice")
    private String minPrice;

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

    public String getMinPrice() {
        return minPrice;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }
}
