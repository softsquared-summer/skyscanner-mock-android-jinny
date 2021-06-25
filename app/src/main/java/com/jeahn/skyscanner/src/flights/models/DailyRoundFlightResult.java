package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyRoundFlightResult {
    @SerializedName("totalTicketCount")
    private int totalTicketCount;

    @SerializedName("timeGapAvg")
    private int timeGapAvg;

    @SerializedName("airLineList")
    private List<DailyRoundAirLine> airLineList;

    public int getTotalTicketCount() {
        return totalTicketCount;
    }

    public int getTimeGapAvg() {
        return timeGapAvg;
    }

    public List<DailyRoundAirLine> getAirLineList() {
        return airLineList;
    }
}
