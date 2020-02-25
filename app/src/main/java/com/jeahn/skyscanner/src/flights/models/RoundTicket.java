package com.jeahn.skyscanner.src.flights.models;

import com.google.gson.annotations.SerializedName;

public class RoundTicket {
    @SerializedName("deTicket")
    private Ticket deTicket;

    @SerializedName("reTicket")
    private Ticket reTicket;

    public Ticket getDeTicket() {
        return deTicket;
    }

    public Ticket getReTicket() {
        return reTicket;
    }
}
