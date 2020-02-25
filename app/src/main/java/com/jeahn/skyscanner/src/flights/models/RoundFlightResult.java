package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoundFlightResult {
    @SerializedName("totalTicketCount")
    private int totalTicketCount;

    @SerializedName("sortBy")
    private String sortBy;

    @SerializedName("ticketList")
    private List<RoundTicket> ticketList;

    public int getTotalTicketCount() {
        return totalTicketCount;
    }

    public String getSortBy() {
        return sortBy;
    }

    public List<RoundTicket> getTicketList() {
        return ticketList;
    }
}
