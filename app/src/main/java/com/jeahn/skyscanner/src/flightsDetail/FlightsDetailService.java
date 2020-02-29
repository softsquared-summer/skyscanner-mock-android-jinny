package com.jeahn.skyscanner.src.flightsDetail;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.flightsDetail.interfaces.FlightsDetailActivityView;
import com.jeahn.skyscanner.src.flightsDetail.interfaces.FlightsDetailRetrofitInterface;
import com.jeahn.skyscanner.src.flightsDetail.models.AddScheduleResponse;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightsDetailService {
    private final FlightsDetailActivityView mFlightsDetailActivityView;

    public FlightsDetailService(FlightsDetailActivityView mFlightsDetailActivityView) {
        this.mFlightsDetailActivityView = mFlightsDetailActivityView;
    }

    public void postAddSchedule(int deFlightId) {
        final FlightsDetailRetrofitInterface flightsDetailRetrofitInterface =
                ApplicationClass.getRetrofit().create(FlightsDetailRetrofitInterface.class);

        String dd = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN, null);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("roomId", 0);
            jsonObject.put("deFlightId", deFlightId);
            jsonObject.put("seatCode", 0);
            jsonObject.put("adultCount", 1);
            jsonObject.put("infantCount", 0);
            jsonObject.put("childCount", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json; charset=utf-8"));
        flightsDetailRetrofitInterface.postAddSchedule(requestBody).enqueue(new Callback<AddScheduleResponse>() {
            @Override
            public void onResponse(Call<AddScheduleResponse> call, Response<AddScheduleResponse> response) {
                AddScheduleResponse addScheduleResponse = response.body();
                if (addScheduleResponse == null) {
                    mFlightsDetailActivityView.postAddScheduleFailure(null);
                    return;
                }
                if(addScheduleResponse.getCode() == 100){
                    mFlightsDetailActivityView.postAddScheduleSuccess();
                }else{
                    mFlightsDetailActivityView.postAddScheduleFailure(null);
                }

            }

            @Override
            public void onFailure(Call<AddScheduleResponse> call, Throwable t) {
                mFlightsDetailActivityView.postAddScheduleFailure(null);
            }
        });
    }
}
