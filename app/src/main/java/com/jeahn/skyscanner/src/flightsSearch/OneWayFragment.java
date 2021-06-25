package com.jeahn.skyscanner.src.flightsSearch;


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
import com.jeahn.skyscanner.src.city.CityDialog;
import com.jeahn.skyscanner.src.city.models.City;
import com.jeahn.skyscanner.src.selectDate.SelectDateActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneWayFragment extends Fragment implements View.OnClickListener {
    private static int SEARCH_FLIGHTS_ONE_WAY = 100;

    private FlightsSearchActivity mActivity;
    private City mOriginCity, mDestinationCity;
    private int mCabinClass = 0, mAdultCount = 1, mInfantCount = 0, mChildCount = 0;

    private FloatingActionButton mFabSearch;
    private TextView mTvOrigin, mTvDestination, mTvCabinClass, mTvDepartureDate, mTvAdultCount, mTvInfantCount, mTvChildCount;
    private LinearLayout mLinearSeat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = (FlightsSearchActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_one_way, container, false);

        mFabSearch = view.findViewById(R.id.one_way_fab_search);
        mTvOrigin = view.findViewById(R.id.one_way_tv_origin);
        mTvDestination = view.findViewById(R.id.one_way_tv_destination);
        mTvAdultCount = view.findViewById(R.id.one_way_tv_adult_count);
        mTvInfantCount = view.findViewById(R.id.one_way_tv_infant_count);
        mTvChildCount = view.findViewById(R.id.one_way_tv_child_count);
        mTvCabinClass = view.findViewById(R.id.one_way_tv_cabin_class);
        mTvDepartureDate = view.findViewById(R.id.one_way_tv_date_departure);
        mLinearSeat = view.findViewById(R.id.one_way_seat_setting);

        mFabSearch.setOnClickListener(this);
        mTvOrigin.setOnClickListener(this);
        mTvDestination.setOnClickListener(this);
        mTvDepartureDate.setOnClickListener(this);
        mLinearSeat.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one_way_fab_search: //검색 시작
                if (checkInputData()) {
                    Intent intent = new Intent();
                    intent.putExtra("deCity", mOriginCity);
                    intent.putExtra("arCity", mDestinationCity);
                    intent.putExtra("cabinClass", mCabinClass);
                    intent.putExtra("adultCount", mAdultCount);
                    intent.putExtra("infantCount", mInfantCount);
                    intent.putExtra("childCount", mChildCount);
                    getActivity().setResult(SEARCH_FLIGHTS_ONE_WAY, intent);
                    getActivity().finish();
                    getActivity().overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                } else {
                    Toast.makeText(getContext(), "검색 조건을 모두 입력하세요.", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.one_way_tv_origin: //출발지 검색
                showCityDialog(true, mOriginCity);
                break;
            case R.id.one_way_tv_destination: //도착지 검색
                showCityDialog(false, mDestinationCity);
                break;
            case R.id.one_way_tv_date_departure: //날짜 검색
                Intent intent = new Intent(getContext(), SelectDateActivity.class);
                startActivity(intent);
                break;
            case R.id.one_way_seat_setting: //인원 및 좌석 등급 선택
                SeatDialog seatDialog = new SeatDialog(getContext());
                seatDialog.showDialog(mCabinClass, mAdultCount, mInfantCount, mChildCount);
                seatDialog.setDialogListener(new SeatDialog.SeatDialogListener() {
                    @Override
                    public void onApplyButtonClick(int cabinClass, int adultCount, int infantCount, int childCount) {
                        mCabinClass = cabinClass;
                        mAdultCount = adultCount;
                        mInfantCount = infantCount;
                        mChildCount = childCount;

                        switch (cabinClass){
                            case 0:
                                mTvCabinClass.setText(getString(R.string.seat_dialog_economy));
                                break;
                            case 1:
                                mTvCabinClass.setText(getString(R.string.seat_dialog_business));
                                break;
                            case 2:
                                mTvCabinClass.setText(getString(R.string.seat_dialog_premium_economy));
                                break;
                            case 3:
                                mTvCabinClass.setText(getString(R.string.seat_dialog_first_class));
                        }

                        mTvAdultCount.setText(adultCount + "");
                        mTvInfantCount.setText(infantCount + "");
                        mTvChildCount.setText(childCount + "");
                    }
                });
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
