package com.jeahn.skyscanner.src.main.exploreCity.models;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("cityNameKr")
    private String cityNameKr;

    @SerializedName("imgUrl")
    private String imgUrl;

    @SerializedName("deDate")
    private String deDate;

    @SerializedName("arDate")
    private String arDate;

    @SerializedName("minPrice")
    private int minPrice;

    @SerializedName("type")
    private String type;

    public String getCityNameKr() {
        return cityNameKr;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDeDate() {
        return deDate;
    }

    public String getArDate() {
        return arDate;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public String getType() {
        return type;
    }
}
