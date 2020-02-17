package com.jeahn.skyscanner.src.flights;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeahn.skyscanner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneWayFragment extends Fragment {
    private static int START_SEARCH_FLIGHTS_ONE_WAY = 200;

    public OneWayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_way, container, false);
    }

}