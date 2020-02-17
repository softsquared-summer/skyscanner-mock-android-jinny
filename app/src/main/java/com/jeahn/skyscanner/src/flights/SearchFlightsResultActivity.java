package com.jeahn.skyscanner.src.flights;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.jeahn.skyscanner.R;
import com.jeahn.skyscanner.src.BaseActivity;

public class SearchFlightsResultActivity extends BaseActivity {
    private static int SEARCH_FLIGHTS = 1;

    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights_result);

        mToolbar = findViewById(R.id.search_flights_result_toolbar);
        mToolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        Intent intent = new Intent(SearchFlightsResultActivity.this, SearchFlightsActivity.class);
        startActivityForResult(intent, SEARCH_FLIGHTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == SEARCH_FLIGHTS){
            if(resultCode == Activity.RESULT_CANCELED){
                finish();
            }
        }
    }

    public void searchOnClick(View view){
        Intent intent = new Intent(SearchFlightsResultActivity.this, SearchFlightsActivity.class);
        startActivityForResult(intent, SEARCH_FLIGHTS);
    }
}
