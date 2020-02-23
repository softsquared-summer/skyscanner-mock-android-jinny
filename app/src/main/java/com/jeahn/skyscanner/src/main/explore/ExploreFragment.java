package com.jeahn.skyscanner.src.main.explore;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {
    private Toolbar mToolbar;
    private RecyclerView mRvRecommend, mRvCustom, mRvWeekend, mRvMonthly;

    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_explore, container, false);

        mToolbar = v.findViewById(R.id.explore_toolbar);
        mRvRecommend = v.findViewById(R.id.explore_rv_recommend);
        mRvCustom = v.findViewById(R.id.explore_rv_custom);
        mRvWeekend = v.findViewById(R.id.explore_rv_weekend);
        mRvMonthly = v.findViewById(R.id.explore_rv_monthly);

        ((BaseActivity)getActivity()).setSupportActionBar(mToolbar);

        mRvRecommend.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        List<String> items = new ArrayList<>();
        items.add("인기 있는 목적지");
        items.add("단기 휴가");
        items.add("장기 여행");
        items.add("임박 상품");
        items.add("얼리버드 상품");
        mRvRecommend.setAdapter(new ExploreAdapter(items));

        mRvCustom.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        List<String> customItems = new ArrayList<>();
        customItems.add("나홀로 여행");
        customItems.add("커플/우정 인기 여행지");
        mRvCustom.setAdapter(new ExploreAdapter(customItems));

        mRvWeekend.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        List<String> weekendItems = new ArrayList<>();
        weekendItems.add("다음 주말");
        weekendItems.add("3월 6일 - 3월 8일");
        weekendItems.add("3월 13일 - 3월 15일");
        weekendItems.add("3월 20일 - 3월 22일");
        weekendItems.add("3월 27일 - 3월 29일");
        mRvWeekend.setAdapter(new ExploreAdapter(weekendItems));

        mRvMonthly.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        List<String> monthlyItems = new ArrayList<>();
        monthlyItems.add("2월");
        monthlyItems.add("3월");
        monthlyItems.add("4월");
        mRvMonthly.setAdapter(new ExploreAdapter(monthlyItems));

        return v;
    }

}
