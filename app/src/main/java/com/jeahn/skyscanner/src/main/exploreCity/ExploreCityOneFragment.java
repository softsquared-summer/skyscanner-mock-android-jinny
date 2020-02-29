package com.jeahn.skyscanner.src.main.exploreCity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.main.exploreCity.interfaces.ExploreCityActivityView;
import com.jeahn.skyscanner.src.main.exploreCity.models.City;
import com.jeahn.skyscanner.src.main.exploreCountry.ExploreCountryAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExploreCityOneFragment extends Fragment implements ExploreCityActivityView {
    private RecyclerView mRecyclerView;

    private ExploreCityAdapter mExploreCityAdapter;
    private List<City> mCityList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explore_city_one, container, false);

        mRecyclerView = v.findViewById(R.id.explore_city_one_recycler);
        mExploreCityAdapter = new ExploreCityAdapter(mCityList);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(8));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mExploreCityAdapter);

        tryGetFlightList();

        return v;
    }

    private void tryGetFlightList() {
        final ExploreCityService flightsService = new ExploreCityService(this);
        flightsService.getFlightList("O", "GMP", "대한민국");
    }

    @Override
    public void getFlightListSuccess(List<City> result) {
        mCityList.addAll(result);
        mExploreCityAdapter.notifyDataSetChanged();
    }

    @Override
    public void getFlightListFailure(String message) {
        ((BaseActivity)getActivity()).showCustomToast("도시별 편도 최저가 조회 실패");
    }
}
