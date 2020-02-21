package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyOneFlightResult {
    @SerializedName("totalTicketCount")
    private int totalTicketCount;

    @SerializedName("timeGapAvg")
    private String timeGapAvg;

    @SerializedName("airLineList")
    private List<AirLine> airLineList;

    public int getTotalTicketCount() {
        return totalTicketCount;
    }

    public String getTimeGapAvg() {
        return timeGapAvg;
    }

    public List<AirLine> getAirLineList() {
        return airLineList;
    }
}
