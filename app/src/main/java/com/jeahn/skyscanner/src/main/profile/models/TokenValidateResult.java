package com.jeahn.skyscanner.src.main.profile.models;

import com.google.gson.annotations.SerializedName;

public class TokenValidateResult {
    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
