package com.jeahn.skyscanner.src.flights.interfaces;

import com.jeahn.skyscanner.src.flights.models.DailyOneFlightResult;
import com.jeahn.skyscanner.src.flights.models.OneFlightResult;

public interface FlightsActivityView {
    void getDailyOneFlightSuccess(DailyOneFlightResult result);

    void getOneFlightSuccess(OneFlightResult result);

    void getDailyOneFlightFailure(String message);

    void getOneFlightFailure(String message);
}
