package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OneFlightResult {
    @SerializedName("totalTicketCount")
    private int totalTicketCount;

    @SerializedName("sortBy")
    private String sortBy;

    @SerializedName("type")
    private String type;

    @SerializedName("ticketList")
    private ArrayList<Ticket> ticketList = new ArrayList<>();

    public int getTotalTicketCount() {
        return totalTicketCount;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Ticket> getTicketList() {
        return ticketList;
    }
}
