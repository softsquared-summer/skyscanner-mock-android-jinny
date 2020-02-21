package com.jeahn.skyscanner.src.flights.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Ticket implements Parcelable {
    @SerializedName("flightId")
    private int flightId;

    @SerializedName("airPlaneCode")
    private String airPlaneCode;

    @SerializedName("airLineKr")
    private String airLineKr;

    @SerializedName("airLineEn")
    private String airLineEn;

    @SerializedName("airLineImgUrl")
    private String airLineImgUrl;

    @SerializedName("deTime")
    private String deTime;

    @SerializedName("arTime")
    private String arTime;

    @SerializedName("price")
    private String price;

    @SerializedName("timeGap")
    private String timeGap;

    protected Ticket(Parcel in) {
        flightId = in.readInt();
        airPlaneCode = in.readString();
        airLineKr = in.readString();
        airLineEn = in.readString();
        airLineImgUrl = in.readString();
        deTime = in.readString();
        arTime = in.readString();
        price = in.readString();
        timeGap = in.readString();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

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

    public String getAirLineImgUrl() {
        return airLineImgUrl;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(flightId);
        parcel.writeString(airPlaneCode);
        parcel.writeString(airLineKr);
        parcel.writeString(airLineEn);
        parcel.writeString(airLineImgUrl);
        parcel.writeString(deTime);
        parcel.writeString(arTime);
        parcel.writeString(price);
        parcel.writeString(timeGap);
    }
}
