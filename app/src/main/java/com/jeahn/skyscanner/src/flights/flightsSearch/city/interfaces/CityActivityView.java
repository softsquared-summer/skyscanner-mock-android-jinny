package com.jeahn.skyscanner.src.flights.flightsSearch.city.interfaces;

import com.jeahn.skyscanner.src.flights.flightsSearch.city.models.City;

import java.util.List;

public interface CityActivityView {
    void getCityListSuccess(List<City> result);

    void getCityListFailure(String message);
}
