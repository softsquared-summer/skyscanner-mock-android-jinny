package com.jeahn.skyscanner.src.flights.interfaces;

import com.jeahn.skyscanner.src.flights.models.DailyOneFlightResult;
import com.jeahn.skyscanner.src.flights.models.OneFlightResult;
import com.jeahn.skyscanner.src.flights.models.RoundFlightResult;

public interface FlightsActivityView {
    void getDailyOneFlightSuccess(DailyOneFlightResult result);

    void getDailyOneFlightFailure(String message);

    void getOneFlightSuccess(OneFlightResult result);

    void getOneFlightFailure(String message);

    void getRoundFlightSuccess(RoundFlightResult result);

    void getRoundFlightFailure(String message);
}
