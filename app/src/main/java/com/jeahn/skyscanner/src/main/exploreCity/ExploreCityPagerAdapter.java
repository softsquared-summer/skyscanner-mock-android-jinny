package com.jeahn.skyscanner.src.main.exploreCity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jeahn.skyscanner.src.main.exploreCountry.ExploreCountryOneFragment;
import com.jeahn.skyscanner.src.main.exploreCountry.ExploreCountryRoundFragment;

public class ExploreCityPagerAdapter extends FragmentPagerAdapter {
    public ExploreCityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ExploreCityRoundFragment exploreCityRoundFragment = new ExploreCityRoundFragment();
                return exploreCityRoundFragment;

            case 1:
                ExploreCityOneFragment exploreCityOneFragment = new ExploreCityOneFragment();
                return exploreCityOneFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
