package com.jeahn.skyscanner.src.flights.SearchFlightsTab;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flights.FlightsService;
import com.jeahn.skyscanner.src.flights.interfaces.FlightsActivityView;
import com.jeahn.skyscanner.src.flights.models.City;
import com.jeahn.skyscanner.src.flights.models.OneFligthResult;

import java.util.ArrayList;
import java.util.List;

public class InputCityDialog extends DialogFragment implements FlightsActivityView, AdapterView.OnItemClickListener {

    private boolean mIsOrigin;
    private InputCityDialogListener mInputCityDialogListener;

    private List<City> mCityList;
    private City mCurCity;

    private AutoCompleteTextView mAutoCompleteTextView;

    public InputCityDialog(boolean isOrigin, City curCity) {
        mIsOrigin = isOrigin;
        mCurCity = curCity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.FullWidth_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_input_city, null);

        mAutoCompleteTextView = view.findViewById(R.id.input_city_autoCompleteTextView);
        mAutoCompleteTextView.setOnItemClickListener(this);
        if(!mIsOrigin){
            mAutoCompleteTextView.setHint("도착지");
            mAutoCompleteTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_landing, 0, 0, 0);
        }
        if(mCurCity != null){
            mAutoCompleteTextView.setText(mCurCity.getCityNameKr() + " (" + mCurCity.getAirPortCode() + ")");
        }
        mAutoCompleteTextView.setSelectAllOnFocus(true);

        Toolbar toolbar = view.findViewById(R.id.input_city_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        tryGetCityList();

        dialog.setContentView(view);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //도시 리스트 조회 api 통신
    private void tryGetCityList() {
        final FlightsService flightsService = new FlightsService(this);
        flightsService.getCityList();
    }

    @Override
    public void validateSuccess(Object data) {
        List<City> cityList = (List<City>) data;
        mCityList = new ArrayList<>(cityList);
        mAutoCompleteTextView.setAdapter(new CityAdapter(getContext(), mCityList));
    }

    @Override
    public void validateFailure(String message) {
        Toast.makeText(getContext(), "조회 실패", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        City city = (City)adapterView.getItemAtPosition(i);
        mInputCityDialogListener.onItemSelected(city);
        dismiss();
    }

    public void setDialogListener(InputCityDialogListener inputCityDialogListener){
        mInputCityDialogListener = inputCityDialogListener;
    }

    interface InputCityDialogListener{
        void onItemSelected(City city);
    }
}
