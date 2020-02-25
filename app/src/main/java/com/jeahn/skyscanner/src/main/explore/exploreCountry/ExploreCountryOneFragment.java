package com.jeahn.skyscanner.src.main.explore.exploreCountry;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.main.explore.exploreCountry.interfaces.ExploreCountryActivityView;
import com.jeahn.skyscanner.src.main.explore.exploreCountry.models.Country;

import java.util.List;

public class ExploreCountryOneFragment extends Fragment implements ExploreCountryActivityView {
    private RecyclerView mRecyclerView;

    private ExploreCountryRoundAdapter mExploreCountryRoundAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explore_country_one, container, false);

        mRecyclerView = v.findViewById(R.id.explore_country_one_recycler);
        tryGetFlightList();

        return v;
    }

    private void tryGetFlightList() {
        final ExploreCountryService flightsService = new ExploreCountryService(this);
        flightsService.getFlightList("O", "GMP");
    }

    @Override
    public void getFlightListSuccess(List<Country> result) {
        mExploreCountryRoundAdapter = new ExploreCountryRoundAdapter(result);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mExploreCountryRoundAdapter);
    }

    @Override
    public void getFlightListFailure(String message) {
        Toast.makeText(getContext(), "나라별 편도 최저가 검색 실패", Toast.LENGTH_SHORT).show();
    }
}
