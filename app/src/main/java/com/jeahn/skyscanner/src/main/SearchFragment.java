package com.jeahn.skyscanner.src.main;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.flights.SearchFlightsActivity;
import com.jeahn.skyscanner.src.flights.SearchFlightsResultActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    private AppBarLayout mAppBar;
    private TextView mTvTitle, mTvFlight, mTvHotel, mTvCarRental, mTvExplore;
    private ImageButton mIbtnFlight, mIbtnHotel, mIbtnCarRental;

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

        mIbtnFlight.setOnClickListener(this);
        mAppBar.addOnOffsetChangedListener(this);
        mTvExplore.setOnClickListener(this);

        return view;
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
        int resize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50 + ratio * 10, getResources().getDisplayMetrics());

        mIbtnFlight.getLayoutParams().height = resize;
        mIbtnFlight.getLayoutParams().width = resize ;
        mIbtnFlight.requestLayout();

        mIbtnHotel.getLayoutParams().height = resize;
        mIbtnHotel.getLayoutParams().width = resize;
        mIbtnHotel.requestLayout();

        mIbtnCarRental.getLayoutParams().height = resize;
        mIbtnCarRental.getLayoutParams().width = resize;
        mIbtnCarRental.requestLayout();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_ibtn_flight:
                Intent intent = new Intent(getContext(), SearchFlightsResultActivity.class);
                startActivity(intent);
                break;
            case R.id.search_tv_explore:
                MainActivity activity = (MainActivity)getActivity();
                activity.goExplore();
                break;
        }
    }
}
