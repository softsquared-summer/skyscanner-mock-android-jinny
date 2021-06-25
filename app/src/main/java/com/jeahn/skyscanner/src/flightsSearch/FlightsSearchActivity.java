package com.jeahn.skyscanner.src.flightsSearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;

public class FlightsSearchActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    boolean isFirstSearch;

    private Toolbar mToolbar;
    private TabLayout mTabLayout;

    private ViewPager mViewPager;
    private FlightsSearchPagerAdapter mFlightsSearchPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights_search);

        //첫 검색이면 뒤로가기 버튼, 아니면 닫기 버튼
        Intent intent = getIntent();
        isFirstSearch = intent.getBooleanExtra("isFirstSearch", false);
        if (!isFirstSearch) {
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
        }

        mToolbar = findViewById(R.id.flights_search_toolbar);
        setSupportActionBar(mToolbar);
        if (!isFirstSearch) {
            mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_close_white));
        }

        mTabLayout = findViewById(R.id.flights_search_tab);
        mViewPager = findViewById(R.id.flights_search_pager);
        setTabLayout();

    }

    //탭 설정
    private void setTabLayout() {
        //탭 추가
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.flights_search_round_trip)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.flights_search_one_way)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.flights_search_multi_city)));

        //어댑터 세팅
        mFlightsSearchPagerAdapter = new FlightsSearchPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mFlightsSearchPagerAdapter);

        //탭 선택 이벤트
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (isFirstSearch) { //첫 검색이면 검색결과 화면도 닫도록 result 보내기
                    setResult(Activity.RESULT_FIRST_USER);
                }
                finish();
                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //뒤로가기 버튼 가시성 (도시 검색 창이 반투명이어서 뒤에 비치기 때문에 가시성 설정 필요)
    public void setNavigationIconVisibility(boolean visible) {
        if (visible) {
            mToolbar.setVisibility(View.VISIBLE);
        } else {
            mToolbar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition(), false);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onBackPressed() {
        if (isFirstSearch) { //첫 검색이면 검색결과 화면도 닫도록 result 보내기
            setResult(Activity.RESULT_FIRST_USER);
        }
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up);
        super.onBackPressed();
    }
}
