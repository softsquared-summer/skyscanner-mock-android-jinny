package com.jeahn.skyscanner.src.main.profile.interfaces;

public interface ProfileActivityView {
    void getTokenValidateSuccess(String email);

    void getTokenValidateFailure(String message);
}
