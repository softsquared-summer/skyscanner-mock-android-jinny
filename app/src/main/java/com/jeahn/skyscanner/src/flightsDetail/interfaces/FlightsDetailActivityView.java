package com.jeahn.skyscanner.src.flightsDetail.interfaces;

import com.jeahn.skyscanner.src.flights.models.DailyOneFlightResult;

public interface FlightsDetailActivityView {
    void postAddScheduleSuccess();

    void postAddScheduleFailure(String message);
}
