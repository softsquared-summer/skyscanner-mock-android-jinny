package com.jeahn.skyscanner.src.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flights.SearchFlightsActivity;
import com.jeahn.skyscanner.src.flights.SearchFlightsResultActivity;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigation;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private SearchFragment mSearchFragment;
    private LookFragment mLookFragment;

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
        mLookFragment = new LookFragment();

        mFragmentTransaction.replace(R.id.main_frame, mSearchFragment).commitAllowingStateLoss();

        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mFragmentTransaction = mFragmentManager.beginTransaction();

                switch (menuItem.getItemId()){
                    case R.id.action_search:
                        mFragmentTransaction.replace(R.id.main_frame, mSearchFragment).commitAllowingStateLoss();
                        break;
                    case R.id.action_look:
                        mFragmentTransaction.replace(R.id.main_frame, mLookFragment).commitAllowingStateLoss();
                        break;
                    case R.id.action_plan:
                        break;
                    case R.id.action_profile:
                        break;
                }

                return true;
            }
        });
    }
}
