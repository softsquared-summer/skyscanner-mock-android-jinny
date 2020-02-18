package com.jeahn.skyscanner.src.flights.interfaces;

import com.jeahn.skyscanner.src.flights.models.City;

import java.util.List;

public interface FligthsActivityView {
    void validateSuccess(List<City> cityList);

    void validateFailure(String message);
}
