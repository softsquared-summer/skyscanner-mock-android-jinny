package com.jeahn.skyscanner.src.flights.interfaces;

import com.jeahn.skyscanner.src.flights.models.City;
import com.jeahn.skyscanner.src.flights.models.OneFligthResult;

import java.util.List;

public interface FlightsActivityView {
    void validateSuccess(List<City> cityList);

    void validateSuccess(OneFligthResult result);

    void validateFailure(String message);
}
