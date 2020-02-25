package com.jeahn.skyscanner.src.main.profile.login.interfaces;

import com.jeahn.skyscanner.src.main.profile.login.models.EmailResponse;

public interface LoginActivityView {
    void postEmailSuccess(int result);

    void postEmailFailure(String message);
}
