package com.jeahn.skyscanner.src.main;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeahn.skyscanner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LookFragment extends Fragment {


    public LookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_look, container, false);
    }

}
