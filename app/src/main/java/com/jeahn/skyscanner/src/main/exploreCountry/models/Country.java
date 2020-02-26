package com.jeahn.skyscanner.src.main.exploreCountry.models;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("country")
    private String country;

    @SerializedName("minPrice")
    private int minPrice;

    @SerializedName("imgUrl")
    private String imgUrl;

    public String getCountry() {
        return country;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
