package com.jeahn.skyscanner.src.main.profile.models;

import com.google.gson.annotations.SerializedName;

public class TokenValidateResponse {
    @SerializedName("result")
    private TokenValidateResult result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public TokenValidateResult getResult() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
