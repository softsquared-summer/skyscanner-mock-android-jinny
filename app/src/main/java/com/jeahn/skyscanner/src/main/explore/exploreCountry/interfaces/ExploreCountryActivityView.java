package com.jeahn.skyscanner.src.main.explore.exploreCountry.interfaces;

import com.jeahn.skyscanner.src.main.explore.exploreCountry.models.Country;

import java.util.List;

public interface ExploreCountryActivityView {
    void getFlightListSuccess(List<Country> result);

    void getFlightListFailure(String message);
}
