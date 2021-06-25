package com.jeahn.skyscanner.src.main;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.main.explore.ExploreFragment;
import com.jeahn.skyscanner.src.main.profile.ProfileFragment;
import com.jeahn.skyscanner.src.main.search.SearchFragment;
import com.jeahn.skyscanner.src.main.trips.TripsFragment;

public class MainActivity extends BaseActivity {
    private BottomNavigationView mBottomNavigation;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private SearchFragment mSearchFragment;
    private ExploreFragment mExploreFragment;
    private TripsFragment mTripsFragment;
    private ProfileFragment mProfileFragment;

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
        mProfileFragment = new ProfileFragment();

        mFragmentTransaction.replace(R.id.main_frame, mSearchFragment).commitAllowingStateLoss();

        mBottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            mFragmentTransaction = mFragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.action_search:
                    if(mSearchFragment != null && mSearchFragment.isAdded()){
                        mFragmentTransaction.detach(mSearchFragment);
                        mFragmentTransaction.attach(mSearchFragment).commit();
                    }else{
                        mSearchFragment = new SearchFragment();
                        mFragmentTransaction.add(R.id.main_frame, mSearchFragment).addToBackStack(null).commit();
                    }
                    break;
                case R.id.action_explore:
                    if(mExploreFragment != null && mExploreFragment.isAdded()){
                        mFragmentTransaction.detach(mExploreFragment);
                        mFragmentTransaction.attach(mExploreFragment).commit();
                    }else{
                        mExploreFragment = new ExploreFragment();
                        mFragmentTransaction.add(R.id.main_frame, mExploreFragment).addToBackStack(null).commit();
                    }
                    break;
                case R.id.action_trips:
                    if(mTripsFragment != null && mTripsFragment.isAdded()){
                        mFragmentTransaction.detach(mTripsFragment);
                        mFragmentTransaction.attach(mTripsFragment).commit();
                    }else{
                        mTripsFragment = new TripsFragment();
                        mFragmentTransaction.add(R.id.main_frame, mTripsFragment).addToBackStack(null).commit();
                    }
                    break;
                case R.id.action_profile:
                    if(mProfileFragment != null && mProfileFragment.isAdded()){
                        mFragmentTransaction.detach(mProfileFragment);
                        mFragmentTransaction.attach(mProfileFragment).commit();
                    }else{
                        mProfileFragment = new ProfileFragment();
                        mFragmentTransaction.add(R.id.main_frame, mProfileFragment).addToBackStack(null).commit();
                    }
                    break;
            }

            return true;
        });
    }

    public void goExplore() {
        mBottomNavigation.setSelectedItemId(R.id.action_explore);
    }
}
