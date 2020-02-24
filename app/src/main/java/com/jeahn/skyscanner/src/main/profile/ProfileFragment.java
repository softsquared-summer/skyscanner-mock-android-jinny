package com.jeahn.skyscanner.src.main.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.jeahn.skyscanner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements NestedScrollView.OnScrollChangeListener {
    private Toolbar mToolbar;
    private NestedScrollView mScrollView;
    private View mToolbarDivider;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        mToolbar = v.findViewById(R.id.profile_toolbar);
        mScrollView = v.findViewById(R.id.profile_scroll);
        mToolbarDivider = v.findViewById(R.id.profile_toolbar_divider);

        mScrollView.setOnScrollChangeListener(this);

        return v;
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if(scrollY < 40){
            mToolbar.setTitle("");
            mToolbarDivider.setVisibility(View.INVISIBLE);
        }else{
            mToolbar.setTitle(getString(R.string.profile_title));
            mToolbarDivider.setVisibility(View.VISIBLE);
        }
    }
}
