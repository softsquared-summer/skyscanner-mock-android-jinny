package com.jeahn.skyscanner.src.flightsDetail.interfaces;

public interface FlightsDetailActivityView {
    void postAddScheduleSuccess();

    void postAddScheduleFailure(String message);

    void getIsSavedFlightSuccess();

    void getIsSavedFlightFailure(String message);

    void deleteScheduleSuccess();

    void deleteScheduleFailure(String message);
}
