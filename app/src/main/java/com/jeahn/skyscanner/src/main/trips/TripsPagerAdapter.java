package com.jeahn.skyscanner.src.main.trips;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TripsPagerAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;

    public TripsPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new UpcomingFragment();
            case 1:
                return new PastFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
