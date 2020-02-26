package com.jeahn.skyscanner.src.main.exploreCountry.interfaces;

import com.jeahn.skyscanner.src.main.exploreCountry.models.Country;

import java.util.List;

public interface ExploreCountryActivityView {
    void getFlightListSuccess(List<Country> result);

    void getFlightListFailure(String message);
}
