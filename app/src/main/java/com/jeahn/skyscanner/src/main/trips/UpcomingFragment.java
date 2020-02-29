package com.jeahn.skyscanner.src.main.trips;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.main.trips.interfaces.TripsActivityView;
import com.jeahn.skyscanner.src.main.trips.models.Schedule;
import com.jeahn.skyscanner.src.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment implements TripsActivityView {
    private RecyclerView mRecycler;
    private LinearLayout mLinearNoSchedule;

    private TripsAdapter mTripsAdapter;
    private List<Schedule> mScheduleList = new ArrayList<>();

    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upcoming, container, false);

        mRecycler = v.findViewById(R.id.trips_upcoming_recycler);
        mLinearNoSchedule = v.findViewById(R.id.trips_upcoming_linear_no_schedule);
        mTripsAdapter = new TripsAdapter(mScheduleList);
        mRecycler.addItemDecoration(new SpacesItemDecoration(15));
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecycler.setAdapter(mTripsAdapter);

        tryGetSchedule();

        return v;
    }

    private void tryGetSchedule() {
        TripsService tripsService = new TripsService(this);
        tripsService.getSchedule();
    }

    @Override
    public void getScheduleSuccess(List<Schedule> result) {
        mScheduleList.clear();
        mScheduleList.addAll(result);
        mTripsAdapter.notifyDataSetChanged();
        mRecycler.setVisibility(View.VISIBLE);
        mLinearNoSchedule.setVisibility(View.GONE);
    }

    @Override
    public void getScheduleFailure(String message) {
        //((BaseActivity)getActivity()).showCustomToast("일정 조회 실패");
        mRecycler.setVisibility(View.GONE);
        mLinearNoSchedule.setVisibility(View.VISIBLE);
    }
}
