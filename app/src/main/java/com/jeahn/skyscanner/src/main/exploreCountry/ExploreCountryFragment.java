package com.jeahn.skyscanner.src.main.exploreCountry;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flightsSearch.NonSwipeViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreCountryFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener, TabLayout.OnTabSelectedListener {
    private AppBarLayout mAppBar;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private View mBlind;
    private NonSwipeViewPager mViewPager;

    private ExploreCountryPagerAdapter mExploreCountryPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explore_country, container, false);

        mAppBar = v.findViewById(R.id.explore_country_appbar);
        mToolbar = v.findViewById(R.id.explore_country_toolbar);
        mTabLayout = v.findViewById(R.id.explore_country_tab);
        mBlind = v.findViewById(R.id.explore_country_blind);
        mViewPager = v.findViewById(R.id.explore_country_pager);

        mAppBar.addOnOffsetChangedListener(this);
        mToolbar.setNavigationOnClickListener(view -> getActivity().onBackPressed());

        setTabLayout();

        return v;
    }

    private void setTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("왕복"));
        mTabLayout.addTab(mTabLayout.newTab().setText("편도"));

        //어댑터 세팅
        mViewPager.setAdapter(mExploreCountryPagerAdapter);

        //탭 선택 이벤트
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //blind 투명도 조절
        int totalScrollRange = mAppBar.getTotalScrollRange();
        float ratio = (float) (totalScrollRange + verticalOffset) / (float) totalScrollRange;
        mBlind.setAlpha(1 - ratio);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        mExploreCountryPagerAdapter = new ExploreCountryPagerAdapter(getChildFragmentManager());
    }
}
