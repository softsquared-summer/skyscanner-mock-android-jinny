package com.jeahn.skyscanner.src.flights;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;

public class SearchFlightsActivity extends BaseActivity {
    private Toolbar mToolbar;
    private TabLayout mTabLayout;

    private ViewPager mViewPager;
    private SearchFlightsPagerAdapter mSearchFlightsPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights);

        mToolbar = findViewById(R.id.search_flights_toolbar);
        setSupportActionBar(mToolbar);

        mTabLayout = findViewById(R.id.search_flights_tab);
        mTabLayout.addTab(mTabLayout.newTab().setText("왕복"));
        mTabLayout.addTab(mTabLayout.newTab().setText("편도"));
        mTabLayout.addTab(mTabLayout.newTab().setText("다구간"));
        mTabLayout.setTabRippleColor(null);

        mViewPager = findViewById(R.id.search_flights_pager);
        mSearchFlightsPagerAdapter = new SearchFlightsPagerAdapter(
                getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mSearchFlightsPagerAdapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult(Activity.RESULT_CANCELED);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
