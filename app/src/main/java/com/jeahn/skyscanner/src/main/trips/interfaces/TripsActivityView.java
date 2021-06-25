package com.jeahn.skyscanner.src.main.trips.interfaces;

import com.jeahn.skyscanner.src.flights.models.DailyOneFlightResult;
import com.jeahn.skyscanner.src.main.trips.models.Schedule;

import java.util.List;

public interface TripsActivityView {
    void getScheduleSuccess(List<Schedule> result);

    void getScheduleFailure(String message);
}
