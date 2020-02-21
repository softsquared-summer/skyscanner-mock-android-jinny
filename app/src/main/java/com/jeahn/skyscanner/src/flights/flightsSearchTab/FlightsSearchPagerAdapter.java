package com.jeahn.skyscanner.src.flights.flightsSearchTab;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FlightsSearchPagerAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;

    public FlightsSearchPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RoundTripFragment roundTripFragment = new RoundTripFragment();
                return roundTripFragment;

            case 1:
                 OneWayFragment oneWayFragment = new OneWayFragment();
                return oneWayFragment;

            case 2:
                roundTripFragment = new RoundTripFragment();
                return roundTripFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
