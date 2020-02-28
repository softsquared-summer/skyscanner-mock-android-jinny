package com.jeahn.skyscanner.src.flights.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RoundTicket implements Parcelable {
    @SerializedName("deTicket")
    private Ticket deTicket;

    @SerializedName("reTicket")
    private Ticket reTicket;

    protected RoundTicket(Parcel in) {
        deTicket = in.readParcelable(Ticket.class.getClassLoader());
        reTicket = in.readParcelable(Ticket.class.getClassLoader());
    }

    public static final Creator<RoundTicket> CREATOR = new Creator<RoundTicket>() {
        @Override
        public RoundTicket createFromParcel(Parcel in) {
            return new RoundTicket(in);
        }

        @Override
        public RoundTicket[] newArray(int size) {
            return new RoundTicket[size];
        }
    };

    public Ticket getDeTicket() {
        return deTicket;
    }

    public Ticket getReTicket() {
        return reTicket;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(deTicket, i);
        parcel.writeParcelable(reTicket, i);
    }
}
