package com.jeahn.skyscanner.src.main.profile.login.interfaces;

public interface LoginActivityView {
    void postEmailSuccess(int result);

    void postEmailFailure(String message);

    void postRegisterSuccess(int result);

    void postRegisterFailure(String message);
}
