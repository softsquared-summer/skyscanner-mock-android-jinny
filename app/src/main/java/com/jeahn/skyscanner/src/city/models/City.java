package com.jeahn.skyscanner.src.city.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class City implements Parcelable {
    @SerializedName("airPortCode")
    private String airPortCode;

    @SerializedName("cityNameKr")
    private String cityNameKr;

    @SerializedName("cityNameEn")
    private String cityNameEn;

    @SerializedName("country")
    private String country;

    protected City(Parcel in) {
        airPortCode = in.readString();
        cityNameKr = in.readString();
        cityNameEn = in.readString();
        country = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public String getAirPortCode() {
        return airPortCode;
    }

    public String getCityNameKr() {
        return cityNameKr;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(airPortCode);
        parcel.writeString(cityNameKr);
        parcel.writeString(cityNameEn);
        parcel.writeString(country);
    }
}
