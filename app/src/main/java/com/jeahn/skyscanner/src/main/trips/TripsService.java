package com.jeahn.skyscanner.src.main.trips;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.main.trips.interfaces.TripsActivityView;
import com.jeahn.skyscanner.src.main.trips.interfaces.TripsRetrofitInterface;
import com.jeahn.skyscanner.src.main.trips.models.ScheduleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripsService {
    private final TripsActivityView mTripsActivityView;

    public TripsService(TripsActivityView mTripsActivityView) {
        this.mTripsActivityView = mTripsActivityView;
    }

    public void getSchedule() {
        final TripsRetrofitInterface tripsRetrofitInterface =
                ApplicationClass.getRetrofit().create(TripsRetrofitInterface.class);
        tripsRetrofitInterface.getSchedule().enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                ScheduleResponse scheduleResponse = response.body();
                if (scheduleResponse == null) {
                    mTripsActivityView.getScheduleFailure(null);
                    return;
                }
                if(scheduleResponse.getCode() == 100){
                    mTripsActivityView.getScheduleSuccess(scheduleResponse.getResult());
                }else{
                    mTripsActivityView.getScheduleFailure(null);
                }

            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                mTripsActivityView.getScheduleFailure(null);
            }
        });
    }
}
