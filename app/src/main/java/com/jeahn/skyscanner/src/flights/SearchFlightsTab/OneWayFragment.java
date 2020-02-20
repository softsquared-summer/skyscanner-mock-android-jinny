package com.jeahn.skyscanner.src.flights.SearchFlightsTab;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flights.SearchFlightsActivity;
import com.jeahn.skyscanner.src.flights.models.City;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneWayFragment extends Fragment implements View.OnClickListener {
    private static int START_SEARCH_FLIGHTS_ONE_WAY = 100;
    private SearchFlightsActivity mActivity;
    private FloatingActionButton mFabSearch;
    private TextView mTvOrigin, mTvDestination;
    private City originCity, destinationCity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = (SearchFlightsActivity)getActivity();

        View view = inflater.inflate(R.layout.fragment_one_way, container, false);

        mFabSearch = view.findViewById(R.id.one_way_floating_search);
        mTvOrigin = view.findViewById(R.id.one_way_tv_origin);
        mTvDestination = view.findViewById(R.id.one_way_tv_destination);

        mFabSearch.setOnClickListener(this);
        mTvOrigin.setOnClickListener(this);
        mTvDestination.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.one_way_floating_search: //검색 시작
                Intent intent = new Intent();
                intent.putExtra("deAirPortCode", originCity.getAirPortCode());
                intent.putExtra("arAirPortCode", destinationCity.getAirPortCode());
                getActivity().setResult(START_SEARCH_FLIGHTS_ONE_WAY, intent);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                break;
            case R.id.one_way_tv_origin: //출발지 검색
                showInputCityDialog(true, originCity);
                break;
            case R.id.one_way_tv_destination: //도착지 검색
                showInputCityDialog(false, destinationCity);
                break;
        }
    }

    public void showInputCityDialog(boolean isOrigin, City curCity){
        InputCityDialog dialog = new InputCityDialog(isOrigin, curCity);
        dialog.show(mActivity.getSupportFragmentManager(), "TAG");
        mActivity.getSupportFragmentManager().executePendingTransactions();
        dialog.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener(){
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mActivity.setNavigationIconVisibility(true);
            }
        });
        dialog.setDialogListener(new InputCityDialog.InputCityDialogListener() {
            @Override
            public void onItemSelected(City city) {
                if (isOrigin) {
                    originCity = city;
                    mTvOrigin.setText(city.getCityNameKr() + " (" + city.getAirPortCode() + ")");
                }else{
                    destinationCity = city;
                    mTvDestination.setText(city.getCityNameKr() + " (" + city.getAirPortCode() + ")");
                }
            }
        });

        mActivity.setNavigationIconVisibility(false);
    }
}
