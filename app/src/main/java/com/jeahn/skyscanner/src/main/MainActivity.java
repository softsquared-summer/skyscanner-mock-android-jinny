package com.jeahn.skyscanner.src.main;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.main.explore.ExploreFragment;
import com.jeahn.skyscanner.src.main.search.SearchFragment;
import com.jeahn.skyscanner.src.main.trips.TripsFragment;

public class MainActivity extends BaseActivity {
    private BottomNavigationView mBottomNavigation;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private SearchFragment mSearchFragment;
    private ExploreFragment mExploreFragment;
    private TripsFragment mTripsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationSetting();

    }

    private void BottomNavigationSetting() {
        mBottomNavigation = findViewById(R.id.main_navigation);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        mSearchFragment = new SearchFragment();
        mExploreFragment = new ExploreFragment();
        mTripsFragment = new TripsFragment();

        mFragmentTransaction.replace(R.id.main_frame, mSearchFragment).commitAllowingStateLoss();

        mBottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            mFragmentTransaction = mFragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.action_search:
                    mFragmentTransaction.replace(R.id.main_frame, mSearchFragment).commitAllowingStateLoss();
                    break;
                case R.id.action_explore:
                    mFragmentTransaction.replace(R.id.main_frame, mExploreFragment).commitAllowingStateLoss();
                    break;
                case R.id.action_trips:
                    mFragmentTransaction.replace(R.id.main_frame, mTripsFragment).commitAllowingStateLoss();
                    break;
                case R.id.action_profile:
                    break;
            }

            return true;
        });
    }

    public void goExplore() {
        mBottomNavigation.setSelectedItemId(R.id.action_explore);
    }
}
