package com.jeahn.skyscanner.src.main.trips.models;

import com.google.gson.annotations.SerializedName;

public class Schedule {
    @SerializedName("roomId")
    private int roomId;

    @SerializedName("title")
    private String title;

    @SerializedName("deDate")
    private String deDate;

    @SerializedName("reDate")
    private String reDate;

    @SerializedName("cityImgUrl")
    private String cityImgUrl;

    public int getRoomId() {
        return roomId;
    }

    public String getTitle() {
        return title;
    }

    public String getDeDate() {
        return deDate;
    }

    public String getReDate() {
        return reDate;
    }

    public String getCityImgUrl() {
        return cityImgUrl;
    }
}
