package com.jeahn.skyscanner.src.login.interfaces;

public interface LoginActivityView {
    void postEmailSuccess(int result);

    void postEmailFailure(String message);

    void postRegisterSuccess(int result);

    void postRegisterFailure(String message);

    void postLoginSuccess(String token);

    void postLoginFailure(String message);
}
