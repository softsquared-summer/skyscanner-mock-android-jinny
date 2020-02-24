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
import com.jeahn.skyscanner.src.main.models.Product;

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
        List<Product> items = new ArrayList<>();
        items.add(new Product(R.drawable.img_explore_6,getString(R.string.explore_product_popular)));
        items.add(new Product(R.drawable.img_explore_7,getString(R.string.explore_product_quick)));
        items.add(new Product(R.drawable.img_explore_11,getString(R.string.explore_product_long)));
        items.add(new Product(R.drawable.img_explore_12,getString(R.string.explore_product_last_minute)));
        items.add(new Product(R.drawable.img_explore_13,getString(R.string.explore_product_early_bird)));
        mRvRecommend.setAdapter(new ExploreAdapter(items));

        mRvCustom.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        List<Product> customItems = new ArrayList<>();
        customItems.add(new Product(R.drawable.img_explore_9,getString(R.string.explore_product_solo)));
        customItems.add(new Product(R.drawable.img_explore_10,getString(R.string.explore_product_couple)));
        mRvCustom.setAdapter(new ExploreAdapter(customItems));

        mRvWeekend.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        List<Product> weekendItems = new ArrayList<>();
        weekendItems.add(new Product(R.drawable.img_explore_1,getString(R.string.explore_product_this_weekend)));
        weekendItems.add(new Product(R.drawable.img_explore_2,getString(R.string.explore_product_next_weekend)));
        weekendItems.add(new Product(R.drawable.img_explore_14,"3월 6일 - 3월 8일"));
        weekendItems.add(new Product(R.drawable.img_explore_15,"3월 13일 - 3월 15일"));
        weekendItems.add(new Product(R.drawable.img_explore_17,"3월 27일 - 3월 29일"));
        mRvWeekend.setAdapter(new ExploreAdapter(weekendItems));

        mRvMonthly.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        List<Product> monthlyItems = new ArrayList<>();
        monthlyItems.add(new Product(R.drawable.img_explore_3,"2월"));
        monthlyItems.add(new Product(R.drawable.img_explore_4,"3월"));
        monthlyItems.add(new Product(R.drawable.img_explore_5,"4월"));
        mRvMonthly.setAdapter(new ExploreAdapter(monthlyItems));

        return v;
    }

}
