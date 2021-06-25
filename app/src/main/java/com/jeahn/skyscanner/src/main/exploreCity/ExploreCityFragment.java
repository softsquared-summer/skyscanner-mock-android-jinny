package com.jeahn.skyscanner.src.main.exploreCity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flightsSearch.NonSwipeViewPager;
import com.jeahn.skyscanner.src.main.exploreCountry.ExploreCountryPagerAdapter;

public class ExploreCityFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener, TabLayout.OnTabSelectedListener {
    private AppBarLayout mAppBar;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private View mBlind;
    private NonSwipeViewPager mViewPager;

    private ExploreCityPagerAdapter mExploreCountryPagerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explore_city, container, false);

        mAppBar = v.findViewById(R.id.explore_city_appbar);
        mToolbar = v.findViewById(R.id.explore_city_toolbar);
        mTabLayout = v.findViewById(R.id.explore_city_tab);
        mBlind = v.findViewById(R.id.explore_city_blind);
        mViewPager = v.findViewById(R.id.explore_city_pager);

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
        mExploreCountryPagerAdapter = new ExploreCityPagerAdapter(getChildFragmentManager());
    }
}
