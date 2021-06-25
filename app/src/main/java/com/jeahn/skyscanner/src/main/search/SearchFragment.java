package com.jeahn.skyscanner.src.main.search;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flights.FlightsActivity;
import com.jeahn.skyscanner.src.main.MainActivity;
import com.jeahn.skyscanner.src.main.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    private AppBarLayout mAppBar;
    private TextView mTvTitle, mTvFlight, mTvHotel, mTvCarRental, mTvExplore;
    private ImageButton mIbtnFlight, mIbtnHotel, mIbtnCarRental;
    private RecyclerView mRecyclerView;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        mAppBar = view.findViewById(R.id.search_appbar);
        mTvTitle = view.findViewById(R.id.search_tv_title);
        mTvFlight = view.findViewById(R.id.search_tv_flight);
        mTvHotel = view.findViewById(R.id.search_tv_hotel);
        mTvCarRental = view.findViewById(R.id.search_tv_car_rental);
        mIbtnFlight = view.findViewById(R.id.search_ibtn_flight);
        mIbtnHotel = view.findViewById(R.id.search_ibtn_hotel);
        mIbtnCarRental = view.findViewById(R.id.search_ibtn_car_rental);
        mTvExplore = view.findViewById(R.id.search_tv_explore);
        mRecyclerView = view.findViewById(R.id.search_recycler_explore);

        setExploreRecycler();

        mIbtnFlight.setOnClickListener(this);
        mAppBar.addOnOffsetChangedListener(this);
        mTvExplore.setOnClickListener(this);

        return view;
    }

    private void setExploreRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mRecyclerView.setFocusable(false);
        List<Product> items = new ArrayList<>();
        items.add(new Product(R.drawable.img_explore_1, getString(R.string.explore_product_this_weekend)));
        items.add(new Product(R.drawable.img_explore_2, getString(R.string.explore_product_next_weekend)));
        items.add(new Product(R.drawable.img_explore_3, "2월"));
        items.add(new Product(R.drawable.img_explore_4, "3월"));
        items.add(new Product(R.drawable.img_explore_5, "4월"));
        items.add(new Product(R.drawable.img_explore_6, getString(R.string.explore_product_popular)));
        items.add(new Product(R.drawable.img_explore_7, getString(R.string.explore_product_quick)));
        items.add(new Product(R.drawable.img_explore_8, getString(R.string.explore_everywhere)));
        mRecyclerView.setAdapter(new SearchAdapter(items));
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //text 투명도 조절
        int totalScrollRange = mAppBar.getTotalScrollRange();
        float ratio = (float) (totalScrollRange + verticalOffset) / (float) totalScrollRange;
        mTvTitle.setAlpha(ratio);
        mTvFlight.setAlpha(ratio);
        mTvHotel.setAlpha(ratio);
        mTvCarRental.setAlpha(ratio);

        //버튼 크기 조절
        float scale = 1 - (1 - ratio) / 5;

        mIbtnFlight.setScaleX(scale);
        mIbtnFlight.setScaleY(scale);
        mIbtnHotel.setScaleX(scale);
        mIbtnHotel.setScaleY(scale);
        mIbtnCarRental.setScaleX(scale);
        mIbtnCarRental.setScaleY(scale);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_ibtn_flight:
                Intent intent = new Intent(getContext(), FlightsActivity.class);
                startActivity(intent);
                break;
            case R.id.search_tv_explore:
                MainActivity activity = (MainActivity) getActivity();
                activity.goExplore();
                break;
        }
    }
}
