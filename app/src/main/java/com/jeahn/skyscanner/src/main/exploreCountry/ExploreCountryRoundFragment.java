package com.jeahn.skyscanner.src.main.exploreCountry;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;
import com.jeahn.skyscanner.src.main.exploreCity.ExploreCityFragment;
import com.jeahn.skyscanner.src.main.exploreCountry.interfaces.ExploreCountryActivityView;
import com.jeahn.skyscanner.src.main.exploreCountry.models.Country;

import java.util.ArrayList;
import java.util.List;

public class ExploreCountryRoundFragment extends Fragment implements ExploreCountryActivityView {
    private RecyclerView mRecyclerView;

    private ExploreCountryAdapter mExploreCountryAdapter;
    private List<Country> mCountryList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explore_country_round, container, false);

        mRecyclerView = v.findViewById(R.id.explore_country_round_recycler);
        mExploreCountryAdapter = new ExploreCountryAdapter(mCountryList, (BaseActivity) getActivity());
        mExploreCountryAdapter.setOnItemClickeListener((v1, pos) -> {
            ExploreCityFragment exploreCityFragment = new ExploreCityFragment();

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main_frame, exploreCityFragment).addToBackStack(null).commit();
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mExploreCountryAdapter);
        tryGetFlightList();

        return v;
    }

    private void tryGetFlightList() {
        final ExploreCountryService flightsService = new ExploreCountryService(this);
        flightsService.getFlightList("R", "GMP");
    }

    @Override
    public void getFlightListSuccess(List<Country> result) {
        mCountryList.addAll(result);
        mExploreCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getFlightListFailure(String message) {
        Toast.makeText(getContext(), "나라별 왕복 최저가 검색 실패", Toast.LENGTH_SHORT).show();
    }
}
