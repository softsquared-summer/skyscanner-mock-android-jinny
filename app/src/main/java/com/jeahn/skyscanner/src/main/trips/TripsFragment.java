package com.jeahn.skyscanner.src.main.trips;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.jeahn.skyscanner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripsFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private TabLayout mTripsTab;
    private ViewPager mViewPager;

    private TripsPagerAdapter mTripsPagerAdapter;

    public TripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_trips, container, false);

        mTripsTab = v.findViewById(R.id.trips_tab);
        mViewPager = v.findViewById(R.id.trips_pager);

        mTripsTab.addTab(mTripsTab.newTab().setText(getString(R.string.trips_upcoming)));
        mTripsTab.addTab(mTripsTab.newTab().setText(getString(R.string.trips_past)));

        mViewPager.setAdapter(mTripsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTripsTab));

        mTripsTab.addOnTabSelectedListener(this);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mTripsPagerAdapter = new TripsPagerAdapter(getChildFragmentManager());

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
