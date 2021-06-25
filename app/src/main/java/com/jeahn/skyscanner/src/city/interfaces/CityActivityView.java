package com.jeahn.skyscanner.src.city.interfaces;

import com.jeahn.skyscanner.src.city.models.City;

import java.util.List;

public interface CityActivityView {
    void getCityListSuccess(List<City> result);

    void getCityListFailure(String message);
}
