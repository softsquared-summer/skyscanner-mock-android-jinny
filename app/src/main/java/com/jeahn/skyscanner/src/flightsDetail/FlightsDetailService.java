package com.jeahn.skyscanner.src.flightsDetail;

import com.jeahn.skyscanner.src.ApplicationClass;
import com.jeahn.skyscanner.src.flightsDetail.interfaces.FlightsDetailActivityView;
import com.jeahn.skyscanner.src.flightsDetail.interfaces.FlightsDetailRetrofitInterface;
import com.jeahn.skyscanner.src.flightsDetail.models.AddScheduleResponse;
import com.jeahn.skyscanner.src.flightsDetail.models.DeleteScheduleResponse;
import com.jeahn.skyscanner.src.flightsDetail.models.IsSavedFlightResponse;

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

    public void getIsSavedFlight(int deFlightId) {
        final FlightsDetailRetrofitInterface flightsDetailRetrofitInterface =
                ApplicationClass.getRetrofit().create(FlightsDetailRetrofitInterface.class);

        flightsDetailRetrofitInterface.getIsSavedFlight(deFlightId).enqueue(new Callback<IsSavedFlightResponse>() {
            @Override
            public void onResponse(Call<IsSavedFlightResponse> call, Response<IsSavedFlightResponse> response) {
                IsSavedFlightResponse isSavedFlightResponse = response.body();
                if (isSavedFlightResponse == null) {
                    mFlightsDetailActivityView.getIsSavedFlightFailure(null);
                    return;
                }
                if(isSavedFlightResponse.getCode() == 100){
                    mFlightsDetailActivityView.getIsSavedFlightSuccess();
                }else{
                    mFlightsDetailActivityView.getIsSavedFlightFailure(null);
                }

            }

            @Override
            public void onFailure(Call<IsSavedFlightResponse> call, Throwable t) {
                mFlightsDetailActivityView.getIsSavedFlightFailure(null);
            }
        });
    }

    public void deleteSchedule(int deFlightId) {
        final FlightsDetailRetrofitInterface flightsDetailRetrofitInterface =
                ApplicationClass.getRetrofit().create(FlightsDetailRetrofitInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("deFlightId", deFlightId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json; charset=utf-8"));
        flightsDetailRetrofitInterface.deleteSchedule(requestBody).enqueue(new Callback<DeleteScheduleResponse>() {
            @Override
            public void onResponse(Call<DeleteScheduleResponse> call, Response<DeleteScheduleResponse> response) {
                DeleteScheduleResponse deleteScheduleResponse = response.body();
                if (deleteScheduleResponse == null) {
                    mFlightsDetailActivityView.deleteScheduleFailure(null);
                    return;
                }
                if(deleteScheduleResponse.getCode() == 100){
                    mFlightsDetailActivityView.deleteScheduleSuccess();
                }else{
                    mFlightsDetailActivityView.deleteScheduleFailure(null);
                }

            }

            @Override
            public void onFailure(Call<DeleteScheduleResponse> call, Throwable t) {
                mFlightsDetailActivityView.deleteScheduleFailure(null);
            }
        });
    }
}
