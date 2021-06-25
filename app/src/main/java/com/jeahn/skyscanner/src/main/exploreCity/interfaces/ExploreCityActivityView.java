package com.jeahn.skyscanner.src.main.exploreCity.interfaces;

import com.jeahn.skyscanner.src.main.exploreCity.models.City;

import java.util.List;

public interface ExploreCityActivityView {
    void getFlightListSuccess(List<City> result);

    void getFlightListFailure(String message);
}
