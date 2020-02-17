package com.jeahn.skyscanner.src.flights;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;

public class SearchFlightsActivity extends BaseActivity {
    private static int START_SEARCH_FLIGHTS_ONE_WAY = 200;

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
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.search_flights_round_trip)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.search_flights_one_way)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.search_flights_multi_city)));
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

    public void originOnClick(View view){
        showInputCityDialog(true);
    }

    public void destinationOnClick(View view){
        showInputCityDialog(false);
    }

    private void showInputCityDialog(boolean isOrigin){
        InputCityDialog dialog = new InputCityDialog(isOrigin);
        dialog.show(getSupportFragmentManager(), "TAG");
        getSupportFragmentManager().executePendingTransactions();
        dialog.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener(){
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mToolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            }
        });

        int color = getResources().getColor(R.color.flightsMainColor);
        mToolbar.getNavigationIcon().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    public void startSearchFlightsOnClick(View view){
        switch (view.getId())
        {
            case R.id.one_way_floating_search:
                setResult(START_SEARCH_FLIGHTS_ONE_WAY);
                finish();
                break;
        }
    }
}
