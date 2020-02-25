package com.jeahn.skyscanner.src.flights.flightsSearch;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flights.flightsSearch.city.CityDialog;
import com.jeahn.skyscanner.src.flights.flightsSearch.city.models.City;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoundTripFragment extends Fragment implements View.OnClickListener{
    private static int SEARCH_FLIGHTS_ROUND_TRIP = 200;

    private FlightsSearchActivity mActivity;
    private City mOriginCity, mDestinationCity;
    private int mCabinClass = 0;

    private FloatingActionButton mFabSearch;
    private TextView mTvOrigin, mTvDestination, mTvCabinClass;
    private LinearLayout mLinearSeat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = (FlightsSearchActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_round_trip, container, false);

        mFabSearch = view.findViewById(R.id.round_trip_fab_search);
        mTvOrigin = view.findViewById(R.id.round_trip_tv_origin);
        mTvDestination = view.findViewById(R.id.round_trip_tv_destination);
        mTvCabinClass = view.findViewById(R.id.round_trip_tv_cabin_class);
        mLinearSeat = view.findViewById(R.id.round_trip_seat_setting);

        mFabSearch.setOnClickListener(this);
        mTvOrigin.setOnClickListener(this);
        mTvDestination.setOnClickListener(this);
        mLinearSeat.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.round_trip_fab_search: //검색 시작
                if (checkInputData()) {
                    Intent intent = new Intent();
                    intent.putExtra("deAirPortCode", mOriginCity.getAirPortCode());
                    intent.putExtra("arAirPortCode", mDestinationCity.getAirPortCode());
                    intent.putExtra("cabinClass", mCabinClass);
                    getActivity().setResult(SEARCH_FLIGHTS_ROUND_TRIP, intent);
                    getActivity().finish();
                    getActivity().overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                } else {
                    Toast.makeText(getContext(), "검색 조건을 모두 입력하세요.", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.round_trip_tv_origin: //출발지 검색
                showCityDialog(true, mOriginCity);
                break;
            case R.id.round_trip_tv_destination: //도착지 검색
                showCityDialog(false, mDestinationCity);
                break;
            case R.id.round_trip_seat_setting: //인원 및 좌석 등급 선택
                SeatDialog seatDialog = new SeatDialog(getContext());
                seatDialog.showDialog(mCabinClass, mTvCabinClass);
                seatDialog.setDialogListener(cabinClass -> mCabinClass = cabinClass);
                break;
        }
    }

    //검색 조건 유효성 체크
    private boolean checkInputData() {
        if (mTvOrigin.getText().toString().matches("")
                || mTvDestination.getText().toString().matches("")) {
            return false;
        }
        return true;
    }

    public void showCityDialog(boolean isOrigin, City curCity) {
        CityDialog dialog = new CityDialog(isOrigin, curCity);
        dialog.show(mActivity.getSupportFragmentManager(), "TAG");
        mActivity.getSupportFragmentManager().executePendingTransactions();
        dialog.getDialog().setOnDismissListener(dialogInterface -> mActivity.setNavigationIconVisibility(true));
        dialog.setDialogListener(city -> {
            if (isOrigin) {
                mOriginCity = city;
                mTvOrigin.setText(String.format("%s (%s)", city.getCityNameKr(), city.getAirPortCode()));
            } else {
                mDestinationCity = city;
                mTvDestination.setText(String.format("%s (%s)", city.getCityNameKr(), city.getAirPortCode()));
            }
        });

        mActivity.setNavigationIconVisibility(false);
    }
}
