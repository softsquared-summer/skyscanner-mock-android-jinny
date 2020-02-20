package com.jeahn.skyscanner.src.flights.interfaces;

import com.jeahn.skyscanner.src.flights.models.City;
import com.jeahn.skyscanner.src.flights.models.OneFligthResult;

import java.util.List;

public interface FlightsActivityView {
    void validateSuccess(Object data);

    void validateFailure(String message);
}
