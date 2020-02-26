package com.jeahn.skyscanner.src.main.exploreCountry;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ExploreCountryPagerAdapter extends FragmentPagerAdapter {

    public ExploreCountryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ExploreCountryRoundFragment exploreCountryRoundFragment = new ExploreCountryRoundFragment();
                return exploreCountryRoundFragment;

            case 1:
                ExploreCountryOneFragment exploreCountryOneFragment = new ExploreCountryOneFragment();
                return exploreCountryOneFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
